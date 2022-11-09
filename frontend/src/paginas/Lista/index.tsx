import ListaItens from "componentes/ListaItens";
import { useNavigate } from "react-router-dom";
import './Style.css';

export default function Lista() {

    const historys = useNavigate();
    const idColaborador = localStorage.getItem('CafeManha')

    function sair() {
        localStorage.removeItem('CafeManha');
        localStorage.removeItem('CafeManhaAcesso');
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

                <ListaItens estadoBotao={false}
                    urlDoBanco={`/colaborador?id=${idColaborador}`} />

                <h2 className="centralizar_h2">
                    Itens Escolhidos <br />
                </h2>

                <ListaItens estadoBotao={true}
                    urlDoBanco="/page?&orderBy=colaborador.nome" />
            </div>
        </div>
    )
}