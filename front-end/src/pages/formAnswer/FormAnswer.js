import React, { useState, useEffect } from 'react';
import FormQuestionSide from '../../components/formQuestions/FormQuestionSide';
import './formAnswer.css'
import GraduationQuestions from '../../components/formQuestions/GraduationQuestions';
import {Form} from 'react-bootstrap';
import Navbar from '../../components/navbar/Navbar';
import QuestionConfirm from '../../components/formQuestions/QuestionConfirm';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const FormAnswer = () => {
    let [possuiOutraGrad, setPossuiOutraGrad] = useState('');
    let [cursaOuCursouGrad, setCursaOuCursouGrad] = useState('');
    let [cursaOuCursouEspec, setCursaOuCursouEspec] = useState('');
    let [formacaoTeorica, setFormacaoTeorica] = useState('');
    let [formacaoPratica, setFormacaoPratica] = useState('');
    let [duracaoDoCurso, setDuracaoDoCurso] = useState('');
    let [setorExercicio, setSetorExercicio] = useState('');
    let [exerceQuantoTempo, setExerceQuantoTempo] = useState('');
    let [vinculoTrabalho, setVinculoTrabalho] = useState('');
    let [funcaoCargo, setFuncaoCargo] = useState('');
    let [relacaoFuncao, setRelacaoFuncao] = useState('');
    let [renda, setRenda] = useState('');
    let [step, setStep] = useState(1);
    let [answers, setAnswers] = useState();
    let [answered, setAnswered] = useState(false);

    let navigate = useNavigate();

    let functions = {
        'possuiOutraGrad': setPossuiOutraGrad,
        'cursaOuCursouGrad': setCursaOuCursouGrad,
        'cursaOuCursouEspec': setCursaOuCursouEspec,
        'formacaoTeorica': setFormacaoTeorica,
        'formacaoPratica': setFormacaoPratica,
        'duracaoDoCurso': setDuracaoDoCurso,
        'setorExercicio': setSetorExercicio,
        'exerceQuantoTempo': setExerceQuantoTempo,
        'vinculoTrabalho': setVinculoTrabalho,
        'funcaoCargo': setFuncaoCargo,
        'relacaoFuncao': setRelacaoFuncao,
        'renda': setRenda
    };

    const getAnswers = async () => {
        let config = {
            headers: {
                'Content-Type': 'application/json'
            },
            params: {
                'formId': 2
            }
        };

        let resposta = await axios.get(
            'http://localhost:3333/respostaform',
            config
        );

        setAnswers(resposta.data);
        setAnswered(resposta.data.length > 0);

        for(let _resp of resposta.data) {
            functions[_resp.cod_campo](_resp.resposta);
        }
    }

    useEffect( () => { getAnswers() }, [] );

    let values = {
        possuiOutraGrad,
        cursaOuCursouGrad,
        cursaOuCursouEspec,
        formacaoTeorica,
        formacaoPratica,
        duracaoDoCurso,
        setorExercicio,
        exerceQuantoTempo,
        vinculoTrabalho,
        funcaoCargo,
        relacaoFuncao,
        renda
    };
    
    const handleChange = (field) => (event) => {
        if (answered == false) {
            functions[field](event.target.value);
        }
    };

    const clearForm = () => {
        if (answered == false) {
            for(let _function of Object.values(functions)) {
                _function('');
            }
        }
    };

    const sendForm = () => {
        let config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        let data = {
            formId: 2,
            values
        };

        console.log(data);

        axios.post('http://localhost:3333/respostaform', data, config)
            .then((res) => { 
                alert('Formulário Respondido com Sucesso'); 
                setTimeout(()=> navigate('/home', {replace: true}), 5000);
            })
            .catch((err) => console.error(err));
    }

    return (
        <div id="pageContainer">
            <Navbar active={2}/>
            <div id="contentContainer">
                { step === 1 ? (
                    <>
                        <aside>
                            <FormQuestionSide 
                                title="Formulário de Egressos 2023" 
                                values={values} 
                                clearForm={clearForm}
                                answered={answered}
                            />
                        </aside>

                        <main className="formContentContainer">
                            <Form>
                                <GraduationQuestions 
                                    handleChange={handleChange}
                                    values={values}
                                />
                            </Form>
                            {!answered &&
                                <div id="formQuestionButtonContainer">
                                    <button type="button" onClick={() => setStep(step + 1)}>Próximo</button>
                                </div>
                            }
                        </main>
                    </>
                    ): (
                        <>
                        <aside>
                            <FormQuestionSide 
                                title="Formulário de Egressos 2023" 
                                values={values} 
                                clearForm={clearForm}
                                answered={answered}
                            />
                        </aside>
                        
                        <main className="formQuestionConfirm">
                            {
                                Object.entries(values).map(
                                    ([question, value], index) => <QuestionConfirm key={index} index={index + 1} question={question} value={value}/>
                                )
                            }
                            <div id="formQuestionButtonContainer">
                                <button onClick={()=> setStep(step - 1)}>Voltar</button>
                                <button onClick={sendForm}>Enviar</button>
                            </div>
                        </main>
                        </>
                    )
                }
            </div>
        </div>
    );
};

export default FormAnswer;