import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";

import ProductList from "./components/ProductList";
import AddProduct from "./components/AddProduct";
import UpdateProduct from "./components/UpdateProduct";

function App() {
    return (
        <BrowserRouter>
            <Routes>

                <Route
                    path="/"
                    element={<ProductList />}
                />

                <Route
                    path="/add"
                    element={<AddProduct />}
                />

                <Route
                    path="/update/:id"
                    element={<UpdateProduct />}
                />

            </Routes>
        </BrowserRouter>
    );
}

export default App;