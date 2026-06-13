package com.ecommerce.backend.service;

import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.OrderItem;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.model.dto.OrderItemRequest;
import com.ecommerce.backend.model.dto.OrderItemResponse;
import com.ecommerce.backend.model.dto.OrderRequest;
import com.ecommerce.backend.model.dto.OrderResponse;
import com.ecommerce.backend.repo.OrderRepo;
import com.ecommerce.backend.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderRepo orderRepo;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        // Create a new Order entity to persist order-level data
        Order order = new Order();

        String orderId = "ORD" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        order.setOrderId(orderId);

        // Copy customer details from incoming DTO to the entity
        order.setCustomerName(orderRequest.customerName());
        order.setEmail(orderRequest.email());
        order.setOrderDate(LocalDate.now());
        order.setStatus("PLACED");

        // Prepare containers for JPA entities and DTO responses
        List<OrderItem> orderItems = new ArrayList<>(); // will be attached to Order entity
        List<OrderItemResponse> itemResponses = new ArrayList<>(); // will be returned inside OrderResponse DTO

        // Iterate over each requested item to validate product, adjust stock and build OrderItem
        for (OrderItemRequest itemReq : orderRequest.items()) {
            Product product = productRepo.findById(itemReq.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < itemReq.quantity()) { // check stock availability
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            product.setQuantity(product.getQuantity() - itemReq.quantity());
            productRepo.save(product); // saving updated stock level

            // Build an OrderItem entity linking the product and the parent order
            OrderItem orderItem = OrderItem.builder()
                    .product(product) 
                    .quantity(itemReq.quantity())
                    .price(product.getPrice() * itemReq.quantity())
                    .order(order) 
                    .build();

            // Add the entity to the list that will be attached to the Order
            orderItems.add(orderItem); 

            // Prepare the corresponding DTO item to include in the response
            itemResponses.add(
                    new OrderItemResponse(product.getName(), orderItem.getQuantity(), orderItem.getPrice())
            );
        }

        // Attach all built OrderItem entities to the Order entity before persisting
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepo.save(order); 

        // Build and return an OrderResponse DTO summarizing the newly created order
        return new OrderResponse(
                orderId, // external order id
                savedOrder.getCustomerName(), 
                savedOrder.getEmail(), 
                savedOrder.getStatus(), 
                savedOrder.getOrderDate(), 
                itemResponses 
        );
    }

    public List<OrderResponse> getAllOrderResponses() {
        // Fetch all Order entities from the database. This returns a list of persisted Order objects.
        List<Order> orders = orderRepo.findAll();

        // Prepare the list that will hold the DTO representations of orders to return to callers.
        List<OrderResponse> orderResponses = new ArrayList<>();

        // Iterate over each Order entity and transform it into an OrderResponse DTO.
        for (Order order : orders) {
            // For each order, prepare a list of OrderItemResponse DTOs that describe the line items.
            List<OrderItemResponse> itemResponses = new ArrayList<>();

            // We rely on the OrderItem -> Product relationship to read the product name and price.
            for (OrderItem item : order.getOrderItems()) {
                itemResponses.add(new OrderItemResponse(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ));
            }

            // Build the OrderResponse DTO with order-level metadata plus the transformed items list.
            orderResponses.add(new OrderResponse(
                    order.getOrderId(),
                    order.getCustomerName(),
                    order.getEmail(),
                    order.getStatus(),
                    order.getOrderDate(),
                    itemResponses
            ));
        }
        return orderResponses;
    }
}
