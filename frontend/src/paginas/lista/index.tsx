import MinhasListas from "./minhasListas";
import './Style.css';

export default function Lista() {
    return (
        <div>
            <h2 className="centralizar_h2">
                Meus Itens <br/>
            </h2>
            
            <select>
                <option value="queijo"> Queijo prato</option>
                <option value="queijo"> Queijo Coalho</option>
            </select>
            
            <button type="button" className="btnPersonal"> Inserir</button>
            <button type="button" className="btnPersonal"> Atualizar</button>
            
            <MinhasListas estadoBotao={false}/>
            <h2 className="centralizar_h2">
                Itens Escolhidos <br/>
            </h2>
            <MinhasListas estadoBotao={true}/>
        </div>

    )
}