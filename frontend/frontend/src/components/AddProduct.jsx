import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";

function AddProduct() {

    const navigate = useNavigate();

    const [product, setProduct] = useState({
        name: "",
        price: "",
        description: "",
        category: "",
        brand: "",
        available: true,
        quantity: ""
    });

    const handleChange = (e) => {

        const { name, value } = e.target;

        setProduct({
            ...product,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await API.post("/product", product);
        navigate("/");
    };

    return (
        <div className="page-shell">
            <div className="page-card">
                <div className="page-header">
                    <h2 className="page-title">Add Product</h2>
                </div>

                <form className="form-grid" onSubmit={handleSubmit}>
                    <div className="form-field">
                        <label htmlFor="name">Product Name</label>
                        <input
                            id="name"
                            className="form-input"
                            name="name"
                            placeholder="Enter product name"
                            value={product.name}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="price">Price</label>
                        <input
                            id="price"
                            className="form-input"
                            name="price"
                            placeholder="Enter price"
                            value={product.price}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="description">Description</label>
                        <textarea
                            id="description"
                            className="form-textarea"
                            name="description"
                            placeholder="Enter product description"
                            value={product.description}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="brand">Brand</label>
                        <input
                            id="brand"
                            className="form-input"
                            name="brand"
                            placeholder="Enter brand"
                            value={product.brand}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="category">Category</label>
                        <input
                            id="category"
                            className="form-input"
                            name="category"
                            placeholder="Enter category"
                            value={product.category}
                            onChange={handleChange}
                        />
                    </div>

                    <div className="form-field">
                        <label htmlFor="quantity">Quantity</label>
                        <input
                            id="quantity"
                            className="form-input"
                            name="quantity"
                            placeholder="Enter quantity"
                            value={product.quantity}
                            onChange={handleChange}
                        />
                    </div>

                    <button className="button button-primary" type="submit">
                        Save Product
                    </button>
                </form>
            </div>
        </div>
    );
}

export default AddProduct;