package br.ufu.sisegresso.exception;

import br.ufu.sisegresso.dtos.AppError
import br.ufu.sisegresso.dtos.AppHttpResponse
import br.ufu.sisegresso.messages.AppMessages
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<AppHttpResponse> {
        val appError = AppError(
            name = exception.name,
            message = exception.message ?: AppMessages.UNKNOWN_ERROR.message
        )

        val responseBody = AppHttpResponse(
            error = appError
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun resourceAlreadyExistsHandler(exception: ResourceAlreadyExistsException): ResponseEntity<AppHttpResponse> {
        val appError = AppError(
            name = exception.name,
            message = exception.message ?: AppMessages.UNKNOWN_ERROR.message
        )

        val responseBody = AppHttpResponse(
            error = appError
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }
}
