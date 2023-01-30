import React from "react";
import './FormQuestionSide.css';

const FormQuestionSide = ({title, values, clearForm, answered}) => {
    return (
        <div id="formNavigationContainer">
            <h1>{title}</h1>
            <div className="navigationContainer">
                <h2>Navegação do Formulário</h2>
                <div className="questionButtonContainer">
                    {
                        Object.entries(values).map(
                            ([chave, value], index) => <a key={index + 1} href={`#${chave}`} 
                                className={`questionButton ${value.length > 0 ? "questionButtonAnswered" : ""}`}>{index + 1}</a>
                        )
                    }
                </div>
                <div>
                    {!answered && <a href="#" id="cleanFormButton" onClick={clearForm}><h2>Limpar Formulário</h2></a>}
                </div>
            </div>
        </div>
    );
};

export default FormQuestionSide;