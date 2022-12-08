import Lista from "paginas/Lista";
import Login from "paginas/Login";
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";

export default function Rotas() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Login />} />
                
                <Route path="/lista" 
                element={
                    <RequireAuth redirectTo="/">
                        <Lista/>
                    </RequireAuth>
                }/>
                    
              
            </Routes>
        </BrowserRouter>

    )
    interface Props{
        children: JSX.Element
        redirectTo: string
    }
    function RequireAuth({children, redirectTo}: Props){
        let isAuthenticated = !!localStorage.getItem("CafeManha")
        return isAuthenticated ? children : <Navigate to={redirectTo}/>
    }
}