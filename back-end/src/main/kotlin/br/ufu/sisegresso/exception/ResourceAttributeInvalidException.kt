package br.ufu.sisegresso.exception

class ResourceAttributeInvalidException(
    val name: String,
    message: String,
): RuntimeException(message)