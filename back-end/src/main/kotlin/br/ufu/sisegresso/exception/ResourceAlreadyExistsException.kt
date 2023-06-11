package br.ufu.sisegresso.exception

class ResourceAlreadyExistsException(
    val name: String,
    message: String,
) : RuntimeException(message)