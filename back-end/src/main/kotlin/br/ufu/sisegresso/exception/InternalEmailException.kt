package br.ufu.sisegresso.exception

class InternalEmailException(
    val name: String,
    message: String,
): RuntimeException(message)