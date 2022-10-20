import "./style.css"
import { faUserCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Modal } from 'react-bootstrap';
import { useState } from "react";
import { ErrorMessage, Field, Form, Formik, FormikValues } from "formik";
import * as Yup from 'yup';
import Cadastrar from "componentes/Cadastrar";
import { pt } from "yup-locale-pt";
import { validarCPF } from "../../utils/validation";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { BASE_URL } from "utils/requests";

export default function Login() {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const historys = useNavigate();

    Yup.setLocale(pt);

    const handleSubmit = (values: FormikValues) => {
        axios.post(`${BASE_URL}/login`, values)
            .then((res) => {
                localStorage.setItem('CafeManha', res.data.cpf);
                localStorage.setItem('CafeManhaAcesso', res.headers.authorization!);
                historys('/lista');
            })
            .catch((res) => {
                toast.warning(res.response.data.message)
            })
    }

    const validations = Yup.object().shape({
        cpf: Yup.string().test("", " CPF Não Valido",
            (value) => validarCPF(value + '')).required(),
        senha: Yup.string().min(6).required()
    })

    return (
        <div>
            <Formik initialValues={{ cpf: "", senha: "" }}
                onSubmit={handleSubmit}
                validationSchema={validations}>
                <Form>
                    <div className="loginBox">
                        <FontAwesomeIcon className="user"
                            icon={faUserCircle}
                            style={{ fontSize: 100, color: "orange" }}
                        />
                        <h3>Seja Bem-Vindo</h3>
                        <div>
                            <label htmlFor='cpf'>CPF*</label>
                            <Field id='cpf' placeholder='DIGITE SEU CPF'
                                name='cpf' />
                            <ErrorMessage component='span' name='cpf' />
                        </div>

                        <div>
                            <label htmlFor='senha'>SENHA*</label>
                            <Field id='senha' placeholder='DIGITE SUA SENHA'
                                name='senha' type='password' />
                            <ErrorMessage component='span' name='senha' />
                        </div>

                        <input type="submit" className="btnPersonal" value="Login" />

                        <div>
                            <button className="btnPersonal"
                                onClick={handleShow}>
                                Cadastra-se
                            </button>
                        </div>
                    </div>
                </Form>
            </Formik>

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

        </div>
    )
}