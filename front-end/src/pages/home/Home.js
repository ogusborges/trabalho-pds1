import React, {useEffect, useState} from "react";
import "./Home.css";
import UserInfo from "../../components/userInfo/UserInfo";
import Navbar from "../../components/navbar/Navbar";
import axios from "axios";
import { Link } from "react-router-dom";

const Home = () => {
    const nome = "Silva Emiliano";
    const telefones = ["34 99144-0000", "34 90000-9144", "55 90000-9144"];
    let [answers, setAnswers] = useState(false);

    useEffect( () => { getAnswers() }, [] );

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
    }



    return (
        <div id="pageContainer">
            <Navbar active={0}/>
            <div id="contentContainer">
                <aside>
                    <UserInfo nome={nome} telefones={telefones}/>
                </aside>
                <main>{
                    answers &&
                    <Link to={"/formulario"}>
                        <div className={`formListContainer formListItem ${answers.length > 0 ? "formListItemAnswered" : "formListItemNotAnswered"}`}>
                            <p className="formListItemIndex">Formulário de Egressos 2023</p>
                            <p className="formListItemValue">{answers.length > 0 ? "Respondido": "Não Respondido"}</p>
                        </div>
                    </Link>
                }
                </main>
            </div>
        </div>
    );
};

export default Home;