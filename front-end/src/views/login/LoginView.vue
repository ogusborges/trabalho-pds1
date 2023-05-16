<script setup>

import { ref } from 'vue';
const emailInstitucional = ref("");
const senha = ref("");

function enviarFormulario(event) {
    console.log(event.target)
    console.log(emailInstitucional)
}


</script>

<template>

<div id="login-container">
    <main>
        <h1>Login</h1>
        <form v-on:submit.prevent="enviarFormulario" method="POST">
            <div class="form-field-group">
                <label for="email_institucional">E-mail</label>
                <input type="text" name="email_institucional" id="email_institucional" v-model="emailInstitucional">
                <span class="form-field-error">É necessário inserir um endereço de e-mail válido.</span>
            </div>
            <div class="form-field-group">
                <label for="senha">Senha</label>
                <input type="password" name="senha" id="senha" v-model="senha">
                <span class="form-field-error">É necessário inserir um endereço de e-mail válido.</span>
            </div>
            <button type="submit" class="button-primary">Login</button>        
            <a id="esqueci-senha" href="#">Esqueci minha senha</a>
        </form>
    </main>
</div>

</template>

<style>
    #login-container {
        background-color: #ededed;
        display: flex;
        position:absolute;
        width: 100%;
        height: 100%;
        flex-wrap:wrap;
        justify-content: center;
        align-content: center;
    }

    main {
        background-color: #ffffff;
        width: 17%;
        justify-self: center;
        border-top: #4B8AF9 solid 0.3em;
        padding: 0.7em 1.5em;
        text-align: center;
        box-shadow: 0px 0px 10px #bebebe;
    }
    main > * {
        margin: 0.6em 0px;
    }
    main > form {
        display: flex;
        justify-content: center;
        flex-direction:column;
    }

    main > h1 {
        color: #636363;
        font-weight: 500;
    }

    .form-field-group {
        display: flex;
        flex-wrap: wrap;  
        margin: 0.8em 0px;
    }

    .form-field-group > label {
        font-size: 0.85em;
        color: #737373;
        margin-bottom: 0.6em;
    }

    .form-field-error {
        font-size: 0.7em;
        color: #c80000;
        display: block;
        margin-top: 0.5em;
        visibility: hidden;
    }

    input[type=text], 
    input[type=text]:focus,
    input[type=text]:focus-visible, 
    input[type=text]:hover,
    input[type=password], 
    input[type=password]:focus, 
    input[type=password]:focus-visible, 
    input[type=password]:hover {
        background-color: #f3f3f3;
        padding: 0.7em;
        color: #737373;
        width: 100%;
        border: #e0e0e0 solid 1px;
        border-radius: 0px;
    }

    #esqueci-senha,
    #esqueci-senha:visited {
        color: #858585;
        font-size: 0.8em;
        margin-top: 1em;
        text-decoration: none;
    }

    #esqueci-senha:hover {
        color: #3766b6;
        font-weight: 600;
    }

    .button-primary {
        background-color:#3c84ff;
        padding: 1em;
        border: 0px;
        color: #fff;
        transition: 0.3s;
    }

    .button-primary:hover {
        background-color: #306dd6;
        transition: 0.3s;
    }
</style>

