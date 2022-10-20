import Cadastrar from "componentes/Cadastrar";
import { useState } from "react";
import { Modal } from 'react-bootstrap';

export default function ModalDinamico() {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

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