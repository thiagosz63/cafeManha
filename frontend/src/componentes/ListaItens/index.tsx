import axios from "axios";
import Alerta from "componentes/Alerta";
import ModalDinamico from "componentes/Modal";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { Itens, ItensPaginado } from "tipos/itens";
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

    const [chamarModal, setChamarModal] = useState(false);
    const fecharModal = () => setChamarModal(false);
    const [itens, setItens] = useState<Itens>();

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

    function apagar(itens: Itens) {
        setItens(itens)
        setChamarModal(true)
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
                                        onClick={() => apagar(item)}>
                                        Apagar
                                    </button>
                                    {chamarModal ? <ModalDinamico show={chamarModal}
                                        fechaModal={fecharModal}
                                        children={
                                            <Alerta fechaModal={fecharModal}
                                                itens={itens!} />} /> : null
                                    }
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