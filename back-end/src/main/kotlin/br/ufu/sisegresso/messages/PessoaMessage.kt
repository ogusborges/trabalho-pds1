package br.ufu.sisegresso.messages

enum class PessoaMessage(val message: String) {
    PESSOA_NOT_FOUND("Não foi possível encontrar uma pessoa com os dados especificados"),
    PESSOA_ALREADY_EXISTS("Já existe uma pessoa com os dados especificados"),

    CONTACT_NOT_FOUND("Não foi possível encontrar uma forma de contato com os dados especificados"),
    CONTACT_TYPE_REQUIRED("É necessário informar o tipo de contato"),
    CONTACT_VALUE_REQUIRED("É necessário informar a forma de contato"),

    ROLE_NOT_FOUND("Não foi possível encontrar uma função com os dados especificados"),
}