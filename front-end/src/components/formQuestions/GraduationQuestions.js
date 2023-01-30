import React from "react";
import {Form} from "react-bootstrap";
import "./Questions.css";

const GraduationQuestions = ({handleChange, values}) => {
    return (
        <>
        <div className="questionBox">
            <div id={"possuiOutraGrad"} className={`questionDescContainer ${values.possuiOutraGrad.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 1</h3>
                <p>{values.possuiOutraGrad.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group> 
                    <Form.Label  htmlFor={"possuiOutraGrad"}><h4 className="questionTitle">{"Possui outra Graduação, mesmo incompleta?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"possuiOutraGrad"}
                        label={"Sim"}
                        value={"Sim"}
                        checked={values.possuiOutraGrad === "Sim"? "checked": false}
                        onChange={handleChange("possuiOutraGrad")}
                    />
                    <Form.Check
                        type="radio"
                        name={"possuiOutraGrad"}
                        value={"Não"}
                        label={"Não"}
                        checked={values.possuiOutraGrad === "Não"? "checked" : false}
                        onChange={handleChange("possuiOutraGrad")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"cursaOuCursouGrad"} className={`questionDescContainer ${values.cursaOuCursouGrad.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 2</h3>
                <p>{values.cursaOuCursouGrad.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"cursaOuCursouGrad"}><h4 className="questionTitle">{"Cursa ou cursou Pós-Graduação stricto sensu?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouGrad"}
                        label={"Sim, estou cursando"}
                        value={"Sim, estou cursando"}
                        checked={values.cursaOuCursouGrad === "Sim, estou cursando"? "checked": false}
                        onChange={handleChange("cursaOuCursouGrad")}
                    />
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouGrad"}
                        value={"Sim, já cursei"}
                        label={"Sim, já cursei"}
                        checked={values.cursaOuCursouGrad === "Sim, já cursei"? "checked" : false}
                        onChange={handleChange("cursaOuCursouGrad")}
                    />
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouGrad"}
                        value={"Não, mas pretendo cursar"}
                        label={"Não, mas pretendo cursar"}
                        checked={values.cursaOuCursouGrad === "Não, mas pretendo cursar"? "checked" : false}
                        onChange={handleChange("cursaOuCursouGrad")}
                    />
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouGrad"}
                        value={"Não, e não pretendo cursar"}
                        label={"Não, e não pretendo cursar"}
                        checked={values.cursaOuCursouGrad === "Não, e não pretendo cursar"? "checked" : false}
                        onChange={handleChange("cursaOuCursouGrad")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"cursaOuCursouEspec"} className={`questionDescContainer ${values.cursaOuCursouEspec.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 3</h3>
                <p>{values.cursaOuCursouEspec.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"cursaOuCursouEspec"}><h4 className="questionTitle">{"Cursa ou cursou Especialização?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouEspec"}
                        label={"Sim, estou cursando"}
                        value={"Sim, estou cursando"}
                        checked={values.cursaOuCursouEspec === "Sim, estou cursando"? "checked": false}
                        onChange={handleChange("cursaOuCursouEspec")}
                    />
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouEspec"}
                        value={"Sim, já cursei"}
                        label={"Sim, já cursei"}
                        checked={values.cursaOuCursouEspec === "Sim, já cursei"? "checked" : false}
                        onChange={handleChange("cursaOuCursouEspec")}
                    />
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouEspec"}
                        value={"Não, mas pretendo cursar"}
                        label={"Não, mas pretendo cursar"}
                        checked={values.cursaOuCursouEspec === "Não, mas pretendo cursar"? "checked" : false}
                        onChange={handleChange("cursaOuCursouEspec")}
                    />
                    <Form.Check
                        type="radio"
                        name={"cursaOuCursouEspec"}
                        value={"Não, e não pretendo cursar"}
                        label={"Não, e não pretendo cursar"}
                        checked={values.cursaOuCursouEspec === "Não, e não pretendo cursar"? "checked" : false}
                        onChange={handleChange("cursaOuCursouEspec")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"formacaoTeorica"} className={`questionDescContainer ${values.formacaoTeorica.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 4</h3>
                <p>{values.formacaoTeorica.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"formacaoTeorica"}><h4 className="questionTitle">{"Acerca do curso oferecido, como você avalia a formação teórica?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"formacaoTeorica"}
                        label={"Ótima"}
                        value={"Ótima"}
                        checked={values.formacaoTeorica === "Ótima"? "checked": false}
                        onChange={handleChange("formacaoTeorica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoTeorica"}
                        value={"Boa"}
                        label={"Boa"}
                        checked={values.formacaoTeorica === "Boa"? "checked" : false}
                        onChange={handleChange("formacaoTeorica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoTeorica"}
                        value={"Regular"}
                        label={"Regular"}
                        checked={values.formacaoTeorica === "Regular"? "checked" : false}
                        onChange={handleChange("formacaoTeorica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoTeorica"}
                        value={"Insuficiente"}
                        label={"Insuficiente"}
                        checked={values.formacaoTeorica === "Insuficiente"? "checked" : false}
                        onChange={handleChange("formacaoTeorica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoTeorica"}
                        value={"Péssima"}
                        label={"Péssima"}
                        checked={values.formacaoTeorica === "Péssima"? "checked" : false}
                        onChange={handleChange("formacaoTeorica")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"formacaoPratica"} className={`questionDescContainer ${values.formacaoPratica.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 5</h3>
                <p>{values.formacaoPratica.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"formacaoPratica"}><h4 className="questionTitle">{"Como você avalia a formação prática?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"formacaoPratica"}
                        label={"Sim, estou cursando"}
                        value={"Sim, estou cursando"}
                        checked={values.formacaoPratica === "Sim, estou cursando"? "checked": false}
                        onChange={handleChange("formacaoPratica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoPratica"}
                        value={"Sim, já cursei"}
                        label={"Sim, já cursei"}
                        checked={values.formacaoPratica === "Sim, já cursei"? "checked" : false}
                        onChange={handleChange("formacaoPratica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoPratica"}
                        value={"Não, mas pretendo cursar"}
                        label={"Não, mas pretendo cursar"}
                        checked={values.formacaoPratica === "Não, mas pretendo cursar"? "checked" : false}
                        onChange={handleChange("formacaoPratica")}
                    />
                    <Form.Check
                        type="radio"
                        name={"formacaoPratica"}
                        value={"Não, e não pretendo cursar"}
                        label={"Não, e não pretendo cursar"}
                        checked={values.formacaoPratica === "Não, e não pretendo cursar"? "checked" : false}
                        onChange={handleChange("formacaoPratica")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"duracaoDoCurso"} className={`questionDescContainer ${values.duracaoDoCurso.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 6</h3>
                <p>{values.duracaoDoCurso.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"duracaoDoCurso"}><h4 className="questionTitle">{"Como você avalia a duração do curso?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"duracaoDoCurso"}
                        label={"Sim, estou cursando"}
                        value={"Sim, estou cursando"}
                        checked={values.duracaoDoCurso === "Sim, estou cursando"? "checked": false}
                        onChange={handleChange("duracaoDoCurso")}
                    />
                    <Form.Check
                        type="radio"
                        name={"duracaoDoCurso"}
                        value={"Sim, já cursei"}
                        label={"Sim, já cursei"}
                        checked={values.duracaoDoCurso === "Sim, já cursei"? "checked" : false}
                        onChange={handleChange("duracaoDoCurso")}
                    />
                    <Form.Check
                        type="radio"
                        name={"duracaoDoCurso"}
                        value={"Não, mas pretendo cursar"}
                        label={"Não, mas pretendo cursar"}
                        checked={values.duracaoDoCurso === "Não, mas pretendo cursar"? "checked" : false}
                        onChange={handleChange("duracaoDoCurso")}
                    />
                    <Form.Check
                        type="radio"
                        name={"duracaoDoCurso"}
                        value={"Não, e não pretendo cursar"}
                        label={"Não, e não pretendo cursar"}
                        checked={values.duracaoDoCurso === "Não, e não pretendo cursar"? "checked" : false}
                        onChange={handleChange("duracaoDoCurso")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"setorExercicio"} className={`questionDescContainer ${values.setorExercicio.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 7</h3>
                <p>{values.setorExercicio.length > 0 ?"Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"setorExercicio"}><h4 className="questionTitle">{"Se você trabalha atualmente, qual o setor de exercício?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"setorExercicio"}
                        label={"Setor público"}
                        value={"Setor público"}
                        checked={values.setorExercicio === "Setor público"? "checked": false}
                        onChange={handleChange("setorExercicio")}
                    />
                    <Form.Check
                        type="radio"
                        name={"setorExercicio"}
                        value={"Setor privado"}
                        label={"Setor privado"}
                        checked={values.setorExercicio === "Setor privado"? "checked" : false}
                        onChange={handleChange("setorExercicio")}
                    />
                    <Form.Check
                        type="radio"
                        name={"setorExercicio"}
                        value={"Terceiro setor (Ongs, Associações)"}
                        label={"Terceiro setor (Ongs, Associações)"}
                        checked={values.setorExercicio === "Terceiro setor (Ongs, Associações)"? "checked" : false}
                        onChange={handleChange("setorExercicio")}
                    />
                    <Form.Check
                        type="radio"
                        name={"setorExercicio"}
                        value={"Autônomo"}
                        label={"Autônomo"}
                        checked={values.setorExercicio === "Autônomo"? "checked" : false}
                        onChange={handleChange("setorExercicio")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"exerceQuantoTempo"} className={`questionDescContainer ${values.exerceQuantoTempo.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 8</h3>
                <p>{values.exerceQuantoTempo.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"exerceQuantoTempo"}><h4 className="questionTitle">{"A quanto tempo você trabalha?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"exerceQuantoTempo"}
                        label={"Menos de 1 ano"}
                        value={"Menos de 1 ano"}
                        checked={values.exerceQuantoTempo === "Menos de 1 ano"? "checked": false}
                        onChange={handleChange("exerceQuantoTempo")}
                    />
                    <Form.Check
                        type="radio"
                        name={"exerceQuantoTempo"}
                        value={"Entre 1 e 3 anos"}
                        label={"Entre 1 e 3 anos"}
                        checked={values.exerceQuantoTempo === "Entre 1 e 3 anos"? "checked" : false}
                        onChange={handleChange("exerceQuantoTempo")}
                    />
                    <Form.Check
                        type="radio"
                        name={"exerceQuantoTempo"}
                        value={"Entre 3 e 5 anos"}
                        label={"Entre 3 e 5 anos"}
                        checked={values.exerceQuantoTempo === "Entre 3 e 5 anos"? "checked" : false}
                        onChange={handleChange("exerceQuantoTempo")}
                    />
                    <Form.Check
                        type="radio"
                        name={"exerceQuantoTempo"}
                        value={"Mais de 5 anos"}
                        label={"Mais de 5 anos"}
                        checked={values.exerceQuantoTempo === "Mais de 5 anos"? "checked" : false}
                        onChange={handleChange("exerceQuantoTempo")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"vinculoTrabalho"} className={`questionDescContainer ${values.vinculoTrabalho.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 9</h3>
                <p>{values.vinculoTrabalho.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"vinculoTrabalho"}><h4 className="questionTitle">{"Qual o seu vínculo de trabalho?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"vinculoTrabalho"}
                        label={"CLT"}
                        value={"CLT"}
                        checked={values.vinculoTrabalho === "CLT"? "checked": false}
                        onChange={handleChange("vinculoTrabalho")}
                    />
                    <Form.Check
                        type="radio"
                        name={"vinculoTrabalho"}
                        value={"Pessoa Jurídica"}
                        label={"Pessoa Jurídica"}
                        checked={values.vinculoTrabalho === "Pessoa Jurídica"? "checked" : false}
                        onChange={handleChange("vinculoTrabalho")}
                    />
                    <Form.Check
                        type="radio"
                        name={"vinculoTrabalho"}
                        value={"Informal"}
                        label={"Informal"}
                        checked={values.vinculoTrabalho === "Informal"? "checked" : false}
                        onChange={handleChange("vinculoTrabalho")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id="funcaoCargo" className={`questionDescContainer ${values.funcaoCargo.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 10</h3>
                <p>{values.funcaoCargo.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"funcaoCargo"}><h4 className="questionTitle">{"Você exerce qual função ou cargo?"}</h4></Form.Label>
                    <Form.Control 
                        type="text" 
                        name={"funcaoCargo"} 
                        value={values.funcaoCargo} 
                        onChange={handleChange("funcaoCargo")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id={"relacaoFuncao"} className={`questionDescContainer ${values.relacaoFuncao.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 11</h3>
                <p>{values.relacaoFuncao.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                <Form.Group>
                    <Form.Label htmlFor={"relacaoFuncao"}><h4 className="questionTitle">{"Qual a relação da função ou cargo com a formação realizada?"}</h4></Form.Label>
                    <Form.Check
                        type="radio"
                        name={"relacaoFuncao"}
                        label={"Menos de 1 ano"}
                        value={"Menos de 1 ano"}
                        checked={values.relacaoFuncao === "Menos de 1 ano"? "checked": false}
                        onChange={handleChange("relacaoFuncao")}
                    />
                    <Form.Check
                        type="radio"
                        name={"relacaoFuncao"}
                        value={"Entre 1 e 3 anos"}
                        label={"Entre 1 e 3 anos"}
                        checked={values.relacaoFuncao === "Entre 1 e 3 anos"? "checked" : false}
                        onChange={handleChange("relacaoFuncao")}
                    />
                    <Form.Check
                        type="radio"
                        name={"relacaoFuncao"}
                        value={"Entre 3 e 5 anos"}
                        label={"Entre 3 e 5 anos"}
                        checked={values.relacaoFuncao === "Entre 3 e 5 anos"? "checked" : false}
                        onChange={handleChange("relacaoFuncao")}
                    />
                    <Form.Check
                        type="radio"
                        name={"relacaoFuncao"}
                        value={"Mais de 5 anos"}
                        label={"Mais de 5 anos"}
                        checked={values.relacaoFuncao === "Mais de 5 anos"? "checked" : false}
                        onChange={handleChange("relacaoFuncao")}
                    />
                </Form.Group>
            </div>
        </div>
        <div className="questionBox">
            <div id="renda" className={`questionDescContainer ${values.renda.length > 0 ? 'questionDescContainerAnswered' : ''}`}>
                <h3>Questão 12</h3>
                <p>{values.renda.length > 0? "Respondida" : "Não Respondida"}</p>
                <p>Marcar uma opção</p>
            </div>
            <div className="questionInfoContainer">
                
                <Form.Group>
                    <Form.Label htmlFor={"renda"}><h4 className="questionTitle">{"Qual a sua renda ou salário mensal?"}</h4></Form.Label>
                    <Form.Control 
                        type="number" 
                        name={"renda"} 
                        value={values.renda} 
                        onChange={handleChange("renda")}
                    />
                </Form.Group>
            </div>
        </div>
        </>
    );
};

export default GraduationQuestions;