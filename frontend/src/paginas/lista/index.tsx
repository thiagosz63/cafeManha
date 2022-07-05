import { useNavigate } from "react-router-dom";
import MinhasListas from "./minhasListas";
import './Style.css';

export default function Lista() {

    const historys = useNavigate();
    const cpfLogado = localStorage.getItem('CafeManha')

    function sair() {
        localStorage.removeItem('CafeManha');
        historys('/');
    }

    return (
        <div className="container">
            <div className="containerPersonal">
                <h2 className="centralizar_h2">
                    Meus Itens <br />
                </h2>

                <select>
                    <option value="queijo"> Queijo prato</option>
                    <option value="queijo"> Queijo Coalho</option>
                </select>

                <button type="button" className="btnPersonalLista"> Inserir</button>
                <button type="button" className="btnPersonalLista"> Atualizar</button>
                <button type="button"
                    className="btnPersonalLista"
                    onClick={sair}>
                    Sair
                </button>

                <MinhasListas estadoBotao={false}
                    urlDoBanco={`/pageCpf?cpf=${cpfLogado}`} />

                <h2 className="centralizar_h2">
                    Itens Escolhidos <br />
                </h2>

                <MinhasListas estadoBotao={true}
                    urlDoBanco="/page?" />
            </div>
        </div>
    )
}