package br.ufu.sisegresso.exception

import br.ufu.sisegresso.dtos.AppError
import br.ufu.sisegresso.dtos.AppHttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MailExceptionHandler {
    @ExceptionHandler(*arrayOf(
        InternalEmailException::class
    ))
    fun handleInternalEmailException(exception: InternalEmailException): ResponseEntity<AppHttpResponse> {
        val error = AppError(
            name = exception.name,
            message = exception.message ?: ""
        )

        val responseBody = AppHttpResponse(
            error = error
        )

        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(responseBody)
    }
}