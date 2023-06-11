package br.ufu.sisegresso.messages

enum class TokenMessage(val message: String) {
    TOKEN_NOT_FOUND("Não foi possível encontrar um token com os dados especificados"),
    INVALID_QUERY_TYPE("O parâmetro tipoBusca é inválido"),
    TOKEN_ALREADY_EXISTS("Já existe um token com os dados especificados")
}