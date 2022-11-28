import axios from "axios";
import ListaItens from "componentes/ListaItens";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { ItensPaginado } from "tipos/itens";
import { BASE_URL } from "utils/requests";
import './Style.css';

export default function Lista() {

    const historys = useNavigate();
    const idColaborador = localStorage.getItem('CafeManha')
    const [valorSelect, setValorselect] = useState<string>()

    function sair() {
        localStorage.removeItem('CafeManha');
        localStorage.removeItem('CafeManhaAcesso');
        historys('/');
    }
    const [itemPaginado, setItemPaginado] = useState<ItensPaginado>({
        last: true,
        totalPages: 0,
        totalElements: 0,
        number: 0,
        first: true,
    });

    useEffect(() => {
        axios.get(`${BASE_URL}/itens/page?&linesPage=10&page=0`, {
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

    }, [])

    function iserir(id: string) {
        const values = {
            "status": "1",
            "colaborador": {
                "id": idColaborador
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
                toast.warning("Selecione um item para iserir")
            });
    }

    return (
        <div className="container">
            <div className="containerPersonal">
                <h2 className="centralizar_h2">
                    Meus Itens <br />
                </h2>

                <select onChange={(valor) => setValorselect(valor.target.value)}>
                    <option>Selecione</option>
                    {
                        itemPaginado.content?.map(item => (
                            <option key={item.id} value={item.id}> {item.nome}</option>
                        ))
                    }
                </select>

                <button type="button"
                    className="btnPersonalLista"
                    onClick={() => iserir(valorSelect!)}>
                    Inserir
                </button>
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
                    urlDoBanco="/page?status=2&" />
            </div>
        </div>
    )
}