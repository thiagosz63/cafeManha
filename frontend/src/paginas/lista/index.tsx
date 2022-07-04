import MinhasListas from "./minhasListas";
import './Style.css';

export default function Lista() {
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

                <MinhasListas estadoBotao={false} />
                <h2 className="centralizar_h2">
                    Itens Escolhidos <br />
                </h2>
                <MinhasListas estadoBotao={true} />
            </div>
        </div>
    )
}