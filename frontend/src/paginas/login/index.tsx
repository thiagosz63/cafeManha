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
import { axiosGet } from "api";
import { toast } from "react-toastify";
import { AxiosError, AxiosResponse } from 'axios';

function Login() {

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    Yup.setLocale(pt);
    const vccpf = "802.295.340-74"

    const handleSubmit = (values: FormikValues) => {
        axiosGet(`/colaborador?valor=${vccpf}`)
            .then(function (response: AxiosResponse) {
                console.log(values.cpf)
            })
            .catch(function (error: AxiosError) {
                toast.error("Cliente não Cadastrado")

            })
    }

    const validations = Yup.object().shape({
        cpf: Yup.string().test("", " CPF Não Valido",
            (value) => validarCPF(value + '')).required(),
        password: Yup.string().min(6).required()
    })

    return (
        <div>
            <Formik initialValues={{}}
                onSubmit={handleSubmit}
                validationSchema={validations}>
                <Form>
                    <div className="loginBox">
                        <FontAwesomeIcon className="user"
                            icon={faUserCircle}
                            style={{ fontSize: 100, color: "orange" }}
                        />
                        <h3>Seja Bem-Vindo</h3>
                        <form>
                            <div className="inputBox">
                                <div className='login-caixa'>
                                    <Field type="text"
                                        name='cpf'
                                        placeholder="CPF (Apenas número)" />
                                    <div>
                                        <ErrorMessage component='span' name='cpf' />
                                    </div>
                                </div>

                                <div className='login-caixa'>
                                    <Field type='password'
                                        name='password'
                                        id='password'
                                        placeholder='Digite sua Senha'
                                    />
                                    <div>
                                        <ErrorMessage component='span' name='password' />
                                    </div>
                                </div>
                            </div>

                            <button type="button"
                                className="btnPersonal"
                                onClick={handleSubmit}>
                                Login
                            </button>

                        </form>
                        <button type="button" className="btnPersonal"
                            onClick={handleShow}>
                            Cadastra-se
                        </button>
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
export default Login;