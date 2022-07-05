import { axiosGet } from "Api";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { ColaboradorPorPaginas } from "../../tipos/types"

interface props {
    estadoBotao: boolean,
    urlDoBanco: string
}

export default function MinhasListas({
    estadoBotao = true, urlDoBanco}: props) {

     const [pagina,setPagina] = useState(0); 
    const [colaboradorPorPaginas, setcolaboradorPorPaginas] = useState<ColaboradorPorPaginas>({
        last: true,
        totalPages: 0,
        totalElements: 0,
        number: 0,
        first: true,
    });
    function mudaPagina(index:number) {
        setPagina(index);
    }

    
    useEffect(() => {
        axiosGet(`/colaborador${urlDoBanco}&linesPage=3&page=${pagina}`)
            .then((response) => {
                setcolaboradorPorPaginas(response.data)
            })
            .catch(() => {
                toast.error("Error ao listar Colaboradores")
            })
    },[pagina])

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

                            <tr key={item.id}>
                                <th scope="row">
                                    <button hidden={estadoBotao}
                                        type="button"
                                        className="btnPersonal"
                                        onClick={()=>mudaPagina(2)}>
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