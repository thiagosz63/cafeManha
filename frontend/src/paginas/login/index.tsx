import "./style.css"
import { faUserCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Modal } from 'react-bootstrap';
import { useState } from "react";
import { ErrorMessage, Field, Form, Formik, FormikValues } from "formik";
import * as Yup from 'yup';
import Cadastrar from "componentes/cadastrar";
import { pt } from "yup-locale-pt";
import { validarCPF } from "../../utils/validation";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { axiosGet } from "api";

export default function Login() {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    const historys = useNavigate();

    Yup.setLocale(pt);    
    

    const handleSubmit = (values: FormikValues) => {
        axiosGet(`/colaborador?valor=${values.cpf}`)
            .then((response) => {
                if (response.data.senha === values.password) {
                    localStorage.setItem('CafeManha', response.data.cpf);
                    historys('/lista');
                } else{
                    toast.warning("Usuario ou senha Invalidos")
                }    
            })
            .catch(() => {
                toast.warning("Error no banco de dados")

            })
    }

    const validations = Yup.object().shape({
        cpf: Yup.string().test("", " CPF Não Valido",
            (value) => validarCPF(value + '')).required(),
        password: Yup.string().min(6).required()
    })

    return (
        <div>
            <Formik initialValues={{ cpf: "", password: "" }}
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
                            <label htmlFor='password'>SENHA*</label>
                            <Field id='password' placeholder='DIGITE SUA SENHA'
                                name='password' type='password' />
                            <ErrorMessage component='span' name='password' />
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