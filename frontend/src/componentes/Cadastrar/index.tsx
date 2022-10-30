import { ErrorMessage, Field, Form, Formik, FormikValues } from 'formik'
import * as Yup from 'yup';
import './style.css'
import { pt } from 'yup-locale-pt';
import { toast } from 'react-toastify';
import { validarCPF } from 'utils/validation';
import { Colaborador } from 'tipos/colaborado';
import axios from 'axios';
import { BASE_URL } from 'utils/requests';

interface props {
    colaborador?: Colaborador
    titulo?: string
    senha?: string
    fechaModal: () => void
}

export default function Cadastrar({
    titulo = 'Cadastro', colaborador, senha, fechaModal }: props) {

    Yup.setLocale(pt);

    const handleSubmit = (Values: FormikValues) => {
        if (colaborador?.id === undefined) {
            axios.post(`${BASE_URL}/colaborador`, Values)
                .then((res) => {
                    toast.success(res.data)
                      fechaModal();
                })
                .catch((res) => {
                    toast.error(res.response.data.errors[0].message)
                });
        } else {
            axios.put(`/colaborador/${colaborador.id}`, Values)
                .then(() => {
                    toast.success('Dados Atualizados com sucesso')
                    fechaModal();
                })
                .catch((res) => {
                    toast.error(res.response.data.errors[0].message)
                });
        }
    }

    const validations = Yup.object().shape({
        nome: Yup.string().required(' Nome é Obrigatório'),
        cpf: Yup.string().test("", " CPF Não Valido",
            (value) => validarCPF(value + '')).required(),
        senha: Yup.string().min(6).required(' Senha é Obrigatório'),
        ConfirmaSenha: Yup.string().oneOf([Yup.ref('senha'),
            null], 'Senhas são diferentes').required('Confirmação é obrigatória')

    })

    return (
        <div className='container homeCad'>
            <div className='row'>
                <div className='col text-center'>
                    <h5>
                        {titulo}
                    </h5>
                </div>
            </div>

            <Formik initialValues={{
                nome: colaborador?.nome, cpf: colaborador?.cpf,
                senha: colaborador?.senha, ConfirmaSenha: senha
            }}
                onSubmit={handleSubmit} validationSchema={validations}>
                <Form>
                    <div className="row mt-2 ml-2 mr-2">
                        <div className="col-md-12">
                            <label>Nome*
                                <Field type="text" name='nome'
                                    placeholder="Nome" />
                            </label>
                            <ErrorMessage component='span' name='nome' />
                        </div>
                    </div>

                    <div className="row mt-2 ml-2 mr-2">
                        <div className="col-md-12">
                            <label>CPF*
                                <Field type="text" name='cpf'
                                    placeholder="CPF (Apenas número)" />
                            </label>
                            <ErrorMessage component='span' name='cpf' />
                        </div>
                    </div>

                    <div className="row mt-2 ml-2 mr-2">
                        <div className="col-md-12">
                            <label>Senha*
                                <Field type="password" name="senha"
                                    placeholder="********" />
                            </label>
                            <ErrorMessage component='span' name='senha' />
                        </div>
                    </div>

                    <div className="row mt-2 ml-2 mr-2">
                        <div className="col-md-12">
                            <label>Digite sua Senha Novamente*
                                <Field type="password"
                                    placeholder="Confirmar Senha" name='ConfirmaSenha' />
                            </label>
                            <ErrorMessage component='span' name='ConfirmaSenha' />
                        </div>
                    </div>

                    <div className="row mt-2 mr-2">
                        <div className="col text-center">
                            <button type='submit'
                                className="btnPersonal">
                                Salvar
                            </button>
                        </div>
                    </div>
                </Form>
            </Formik>
        </div >
    );
}
