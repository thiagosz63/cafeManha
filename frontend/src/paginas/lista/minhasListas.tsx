import { ColaboradorPorPaginas } from "../../tipos/types"

interface props {
    estadoBotao: boolean,
    colaboradorPorPaginas: ColaboradorPorPaginas
}

export default function MinhasListas({
    estadoBotao = true, colaboradorPorPaginas }: props) {
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
                <tbody>
                    {
                        colaboradorPorPaginas.content?.map(item => (
                            <tr>
                                <th scope="row">
                                    <button hidden={estadoBotao}
                                        type="button"
                                        className="btnPersonal">
                                        Apagar
                                    </button>
                                </th>
                                <td>{item.nome} </td>
                                <td>{item.cpf}</td>
                                <td>Queijo Mussarela</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}