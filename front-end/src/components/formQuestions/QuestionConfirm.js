import React from "react";
import './QuestionConfirm.css'

const QuestionConfirm = ({index, question, value}) => {
    return (
        <>
            <div id={`${question}`}className={`questionConfirm ${value.length > 0 ? "questionConfirmAnswered" : "questionConfirmNotAnswered"}`}>
                <p className="questionConfirmIndex">{index}</p>
                <p className="questionConfirmValue">{value.length > 0 ? "Respondido": "Não Respondido"}</p>
            </div>
        </>
    );
};

export default QuestionConfirm;