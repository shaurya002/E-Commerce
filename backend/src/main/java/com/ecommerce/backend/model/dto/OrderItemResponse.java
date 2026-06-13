package com.ecommerce.backend.model.dto;

public record OrderItemResponse(
        String productName,
        int quantity,
        double totalPrice
) {

}
