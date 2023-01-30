import React, { useState } from "react";
import "./Login.css";
import { Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

const Login = () => {
    let [email, setEmail] = useState('');
    let [senha, setSenha] = useState('');
    let navigate = useNavigate();

    return (
        <div id="pageContainer" style={{justifyItems: "center", alignItems: "center"}}>
            <div id="loginContainer">
                <img src="/Logo01.png" alt="Logotipo da UFU - by Paulo Diego" />
                <div id="loginFormContainer">
                    <Form.Label htmlFor={"email"}><h4>{"E-mail"}</h4></Form.Label>
                    <Form.Control 
                        type="text" 
                        name={"email"}
                        value={email}
                        onChange={(e) => setEmail(e.target.value)} 
                    />
                    
                    <Form.Label htmlFor={"senha"}><h4>{"Senha"}</h4></Form.Label>
                    <Form.Control 
                        type="password"
                        name={"senha"} 
                        value={senha}
                        onChange={(e) => setSenha(e.target.value)}
                    />

                    <button type="button" onClick={() => navigate("/home", {replace: true})}>Logar</button>
                </div>

            </div>
        </div>
    )
}

export default Login;