import { useState } from "react";
import { Modal } from 'react-bootstrap';

interface Props {
    children: JSX.Element
    show: boolean
    fechaModal: () => void
}

export default function ModalDinamico({ children,
    show = false, fechaModal }: Props) {

    const [closeModal, setCloseModal] = useState(false);

    if (closeModal) {
        show = false
        fechaModal()
    }
    return (
        <Modal
            show={show}
            backdrop="static"
            keyboard={false}>
            <Modal.Header>
                <Modal.Title>Café da Manhã</Modal.Title>
                <button type="button"
                    className="close btnPersonal"
                    onClick={() => setCloseModal(true)}
                    aria-label="Close">
                    Cancelar
                </button>
            </Modal.Header>
            <Modal.Body>
                {children}
            </Modal.Body>
        </Modal>
    )
}