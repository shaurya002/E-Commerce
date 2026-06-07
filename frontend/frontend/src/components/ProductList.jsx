import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import API from "../services/api";

function ProductList() {

    const [products, setProducts] = useState([]);

    const fetchProducts = async () => {
        const response = await API.get("/products");
        setProducts(response.data);
    };

    const deleteProduct = async (id) => {
        await API.delete(`/products/${id}`);
        fetchProducts();
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    return (
        <div className="page-shell">
            <div className="page-header">
                <h2 className="page-title">Products</h2>
                <Link className="button button-primary" to="/add">
                    Add Product
                </Link>
            </div>

            <div className="product-list">
                {products.map((product) => (
                    <article className="product-card" key={product.id}>
                        <div className="product-card-header">
                            <h3>{product.name}</h3>
                            <span className={`product-badge ${product.available ? "available" : "soldout"}`}>
                                {product.available ? "Available" : "Out of stock"}
                            </span>
                        </div>

                        <div className="product-meta">
                            <p>{product.description}</p>
                            <p>Price: ₹ {product.price}</p>
                            <p>Brand: {product.brand}</p>
                            <p>Category: {product.category}</p>
                            <p>Quantity: {product.quantity}</p>
                        </div>

                        <div className="card-actions">
                            <Link className="button button-secondary" to={`/update/${product.id}`}>
                                Edit
                            </Link>
                            <button className="button button-danger" type="button" onClick={() => deleteProduct(product.id)}>
                                Delete
                            </button>
                        </div>
                    </article>
                ))}
            </div>
        </div>
    );
}

export default ProductList;