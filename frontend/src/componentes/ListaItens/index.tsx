import axios from "axios";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { ItensPaginado } from "tipos/itens";
import { BASE_URL } from "utils/requests";

interface props {
    estadoBotao: boolean,
    urlDoBanco: string
}

export default function ListaItens({
    estadoBotao = true, urlDoBanco }: props) {

    const [itemPaginado, setItemPaginado] = useState<ItensPaginado>({
        last: true,
        totalPages: 0,
        totalElements: 0,
        number: 0,
        first: true,
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/itens${urlDoBanco}&linesPage=10&page=0`, {
            headers: {
                Authorization: localStorage.getItem('CafeManhaAcesso')
            }
        })
            .then((res) => {
                setItemPaginado(res.data)
            })
            .catch((res) => {
                toast.error(res.response.data.message)
            });

    }, [urlDoBanco])

    function apagar(id: number) {
        const values = {
            "status": "0",
            "colaborador": {
                "id": 2
            }
        }

        axios.put(`${BASE_URL}/itens/${id}`, values, {
            headers: {
                Authorization: localStorage.getItem('CafeManhaAcesso')
            }
        })
            .then(() => {
                window.location.reload();
            })
            .catch(() => {
                toast.error("Error ao apagar Item!")
            });
    }

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
                        itemPaginado.content?.map(item => (

                            <tr key={item.id}>
                                <th scope="row">
                                    <button hidden={estadoBotao}
                                        type="button"
                                        className="btnPersonal"
                                        onClick={() => apagar(item.id)}>
                                        Apagar
                                    </button>
                                </th>
                                <td>{item.colaborador.nome} </td>
                                <td>{item.colaborador.cpf}</td>
                                <td>{item.nome}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}