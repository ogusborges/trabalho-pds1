package br.ufu.sisegresso.exception

class ResourceNotFoundException(
    val name: String,
    message: String
): RuntimeException(message) {
}