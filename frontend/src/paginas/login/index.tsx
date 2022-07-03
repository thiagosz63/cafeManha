import "./style.css"
import { faUserCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Modal } from 'react-bootstrap';
import { useState } from "react";
import { ErrorMessage, Field, Form, Formik, FormikValues } from "formik";
import * as Yup from 'yup';
import Cadastrar from "componentes/cadastrar";
import { pt } from "yup-locale-pt";

interface props {
    id?: string
    cpf?: string
    password?: string
}

function Login(props: props) {
    const {
        cpf, password
    } = props;

    const [show, setShow] = useState(false);
    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);

    Yup.setLocale(pt);

    const handleSubmit = (values: FormikValues) => {
    }


    function validarCPF(cpf: string) {
        cpf = cpf.replace(/[^\d]+/g, '');
        if (cpf === '') return false;
        // Elimina CPFs invalidos conhecidos	
        if (cpf.length !== 11 ||
            cpf === "00000000000" ||
            cpf === "11111111111" ||
            cpf === "22222222222" ||
            cpf === "33333333333" ||
            cpf === "44444444444" ||
            cpf === "55555555555" ||
            cpf === "66666666666" ||
            cpf === "77777777777" ||
            cpf === "88888888888" ||
            cpf === "99999999999")
            return false;
        // Valida 1o digito	
        let add = 0;
        for (let i = 0; i < 9; i++) {
            add += parseInt(cpf.charAt(i)) * (10 - i);
        }
        let rev = 11 - (add % 11);
        if (rev === 10 || rev === 11) {
            rev = 0;
        }
        if (rev !== parseInt(cpf.charAt(9))) {
            return false;
        }
        // Valida 2o digito	
        add = 0;
        for (let i = 0; i < 10; i++) {
            add += parseInt(cpf.charAt(i)) * (11 - i);
        }
        rev = 11 - (add % 11);
        if (rev === 10 || rev === 11) {
            rev = 0;
        }
        if (rev !== parseInt(cpf.charAt(10))) {
            return false;
        }
        return true;
    }

    const validations = Yup.object().shape({
        cpf: Yup.string().test("", " CPF Não Valido",
            (value) => validarCPF(value + '')).required(),
        password: Yup.string().min(6).required()
    })

    return (
        <div>
            <Formik initialValues={{ cpf: cpf, password: password }}
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
                                        id='cpf'
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

                            <input type="submit" name="" value="Login" />

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
                    <Cadastrar />
                </Modal.Body>
            </Modal>

        </div>
    )
}
export default Login;