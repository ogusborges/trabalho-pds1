package br.ufu.sisegresso.messages

enum class AppMessages(val message: String) {
    UNKNOWN_ERROR("Um erro desconhecido aconteceu"),
    LOCAL_DATE_TIME_ERROR("Um erro desconhecido aconteceu ao converter datas"),
    INCONSISTENT_RECORD("Um erro desconhecido aconteceu ao manipular os dados da entidade"),
    DFT_PASSWORD_GENERATION_ERROR("Um erro desconhecido aconteceu"),

    EMAIL_ERROR("Não foi possível gerar o e-mail"),
    EMAIL_AUTHENTICATION_ERROR("Não foi possível autenticar ao servidor de e-mail"),
    EMAIL_SEND_ERROR("Não foi possível enviar o e-mail com os dados especificados")
}