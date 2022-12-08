import axios from "axios";
import { toast } from "react-toastify";
import { Itens } from "tipos/itens";
import { BASE_URL } from "utils/requests";

interface Props {
    fechaModal: () => void
    itens: Itens
}
export default function Alerta({ fechaModal, itens }: Props) {

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
        <div>
            <h3 className="centralizar_h2">
                {`Deseja Remover o Item "${itens.nome}", de sua lista?`}
            </h3>
            <button type="button"
                className="btnPersonalLista"
                onClick={() => apagar(itens.id)}>

                Sim
            </button>

            <button type="button"
                className="btnPersonalLista"
                onClick={() => fechaModal()}>
                Não
            </button>
        </div>
    )
}