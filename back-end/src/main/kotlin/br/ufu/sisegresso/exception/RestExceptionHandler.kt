package br.ufu.sisegresso.exception

import br.ufu.sisegresso.dtos.AppError
import br.ufu.sisegresso.dtos.AppHttpResponse
import br.ufu.sisegresso.messages.ApplicationErrorMessages
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException): ResponseEntity<AppHttpResponse> {
        val fieldErrors = exception.bindingResult.getFieldErrors()
        val appErrors: MutableList<AppError> = mutableListOf()

        for(_error in fieldErrors) {
            val error = AppError().apply {
                name = _error.field
                message = _error.defaultMessage ?: ""
            }

            appErrors.add(error)
        }

        val responseBody = AppHttpResponse(
            error = appErrors
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }

    @ExceptionHandler(*arrayOf(
        DataIntegrityViolationException::class,
    ))
    fun dataIntegrityViolationExceptionHandler(exception: DataIntegrityViolationException): ResponseEntity<AppHttpResponse> {
        val regexUniqueConstraint = ".*constraint \\[[^\\]]+_uniq\\]".toRegex()
        val exceptionMessage = exception.message ?: ""

        val erro = AppError().apply {
            name = "dataIntegrityViolation"
            message = with(exceptionMessage) {
                when {
                    regexUniqueConstraint.matches(this) -> ApplicationErrorMessages.RESOURCE_ALREADY_EXISTS.name
                    else -> ApplicationErrorMessages.UNKNOWN_ERROR.name
                }
            }
        }

        val responseBody = AppHttpResponse(
            error = erro
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }
}