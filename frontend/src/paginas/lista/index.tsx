import { axiosGet } from "Api";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import MinhasListas from "./minhasListas";
import './Style.css';
import { ColaboradorPorPaginas } from "../../tipos/types";

export default function Lista() {
    const [colaboradorPaginas, setColaboradorPaginas] = useState<ColaboradorPorPaginas>({
        last: true,
        totalPages: 0,
        totalElements: 0,
        number: 0,
        first: true,
    });
    useEffect(() => {
        axiosGet(`/colaborador/page`)
            .then((response) => {
                setColaboradorPaginas(response.data)
            })
            .catch(() => {
                toast.error("Error ao listar Colaboradores")

            })
    }, [])


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

                <MinhasListas estadoBotao={false}
                    colaboradorPorPaginas={colaboradorPaginas} />

                <h2 className="centralizar_h2">
                    Itens Escolhidos <br />
                </h2>

                <MinhasListas estadoBotao={true}
                    colaboradorPorPaginas={colaboradorPaginas} />
            </div>
        </div>
    )
}