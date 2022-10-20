import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { BASE_URL } from "utils/requests";
import { ColaboradorPorPaginas } from "../../tipos/types"

interface props {
    estadoBotao: boolean,
    urlDoBanco: string
}

export default function MinhasListas({
    estadoBotao = true, urlDoBanco }: props) {

    const [colaboradorPorPaginas, setcolaboradorPorPaginas] = useState<ColaboradorPorPaginas>({
        last: true,
        totalPages: 0,
        totalElements: 0,
        number: 0,
        first: true,
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/colaborador${urlDoBanco}&linesPage=3&page=0`, {
            headers: {
                Authorization: localStorage.getItem('CafeManhaAcesso')
            }
        })
            .then((res) => {
                setcolaboradorPorPaginas(res.data)
            })
            .catch((res) => {
                toast.error(res.response.data.message)
            });

    }, [urlDoBanco])

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
                                        className="btnPersonal" >
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