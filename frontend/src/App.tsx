import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Rotas from "rotas/Rotas"
import "./App.css"
import "bootstrap/dist/css/bootstrap.css"
import "bootstrap/dist/js/bootstrap.js"
export default function App() {
    
    return (
        <div>
            <Rotas/>
            <ToastContainer theme='colored' />
        </div>

    )
}