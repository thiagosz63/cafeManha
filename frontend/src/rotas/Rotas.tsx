import Lista from "paginas/Lista";
import Login from "paginas/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";

export default function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />} />
                <Route path="/lista" element={<Lista />} />
            </Routes>
        </BrowserRouter>

    )
}