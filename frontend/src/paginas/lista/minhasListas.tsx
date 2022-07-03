import ListCard from "./listCard";

interface props{
    estadoBotao : boolean
}

export default function MinhasListas({estadoBotao}: props) {
    return (
        <div className="table-responsive"> 
        <table className="table table-striped">
            <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Colaboradores</th>
                    <th scope="col">CPF</th>
                    <th scope="col">Item</th>
                </tr>
            </thead>
            <ListCard estadoBotao={estadoBotao}/>
            <ListCard estadoBotao={estadoBotao}/>
            <ListCard estadoBotao={estadoBotao}/>
            <ListCard estadoBotao={estadoBotao}/>
            <ListCard estadoBotao={estadoBotao}/>
        </table>
    </div>
    )
}