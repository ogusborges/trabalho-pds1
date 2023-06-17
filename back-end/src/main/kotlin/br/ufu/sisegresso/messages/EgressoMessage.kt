package br.ufu.sisegresso.messages

enum class EgressoMessage(val message: String) {
    EGRESSO_NOT_FOUND ("Não foi possível encontrar egresso com os dados específicados"),
    EGRESSO_ALREADY_EXISTS ("Já existe um egresso com os dados especificados"),
    ESCOLARIDADE_NOT_FOUND ("Não foi possível encontrar um registro de escolaridade com os dados especificados"),
    EXP_PROFISSIONAL_NOT_FOUND ("Não foi possível encontrar um registro de experiência profissional com os dados especificados"),
    INVALID_QUERY_TYPE ("O parâmetro tipoBusca é invalido"),
}