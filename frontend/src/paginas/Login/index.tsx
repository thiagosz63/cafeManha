import "./style.css"
import { faUserCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
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
import ModalDinamico from "componentes/Modal";

export default function Login() {

    const historys = useNavigate();

    const [chamarModal, setChamarModal] = useState(false);
    const fecharModal = () => setChamarModal(false);

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
        <div className="loginBox">
            <Formik initialValues={{ cpf: "", senha: "" }}
                onSubmit={handleSubmit}
                validationSchema={validations}>
                <Form>
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

                    <input type="submit" className="btnPersonal" value="Login"/>
                </Form>
            </Formik>
            <div>
                <button className="btnPersonal"
                    onClick={() => setChamarModal(true)}>
                    Cadastra-se
                </button>
                {chamarModal ? <ModalDinamico show={chamarModal} fechaModal={fecharModal}
                    children={
                        <Cadastrar fechaModal={fecharModal} />} /> : null
                }
            </div>
        </div>
    )
}