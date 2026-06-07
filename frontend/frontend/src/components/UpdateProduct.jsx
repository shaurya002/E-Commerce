import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import API from "../services/api";

function UpdateProduct() {

    const { id } = useParams();

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

    useEffect(() => {

        API.get(`/products/${id}`)
            .then(response => {
                setProduct(response.data);
            });

    }, [id]);

    const handleChange = (e) => {

        const { name, value } = e.target;

        setProduct({
            ...product,
            [name]: value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await API.put(`/products/${id}`, product);
        navigate("/");
    };

    return (
        <div className="page-shell">
            <div className="page-card">
                <div className="page-header">
                    <h2 className="page-title">Update Product</h2>
                </div>

                <form className="form-grid" onSubmit={handleSubmit}>
                    <div className="form-field">
                        <label htmlFor="name">Product Name</label>
                        <input
                            id="name"
                            className="form-input"
                            name="name"
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
                            value={product.quantity}
                            onChange={handleChange}
                        />
                    </div>

                    <button className="button button-primary" type="submit">
                        Update Product
                    </button>
                </form>
            </div>
        </div>
    );
}

export default UpdateProduct;