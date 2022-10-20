import Cadastrar from "componentes/Cadastrar";
import { useState } from "react";
import { Modal } from 'react-bootstrap';

interface Props{
    handleShow: () => void
}

export default function ModalDinamico({handleShow } : Props) {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    handleShow = () => setShow(true);

    return (
        <Modal
            show={show}
            onHide={handleClose}
            backdrop="static"
            keyboard={false}>
            <Modal.Header>
                <Modal.Title></Modal.Title>
                <button type="button"
                    className="close btnPersonal"
                    onClick={handleClose}
                    aria-label="Close">
                    Cancelar
                </button>
            </Modal.Header>
            <Modal.Body>
                <Cadastrar fechaModal={handleClose} />
            </Modal.Body>
        </Modal>

    )
}