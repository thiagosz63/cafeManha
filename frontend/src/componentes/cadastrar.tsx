import { ErrorMessage, Field, Form, Formik, FormikValues } from 'formik'
import * as Yup from 'yup';
import './style.css'
import { pt } from 'yup-locale-pt';

interface props {
    id?: string
    titulo?: string
    nome?: string
    cpf?: string
    senha?: string
}

function Cadastrar(props: props) {

    const {
        titulo = 'Cadastro', nome, cpf,
         senha
    } = props;

       
    Yup.setLocale(pt);

    const handleSubmit = (Values: FormikValues) => {
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
                nome: nome, cpf: cpf, senha: senha,ConfirmaSenha:senha
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
                                <Field type="text"name='cpf'
                                placeholder="CPF (Apenas número)"/>
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

export default Cadastrar;
