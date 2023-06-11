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



    @ExceptionHandler(*arrayOf(
        ResourceAttributeInvalidException::class,
        HttpMessageNotReadableException::class
    ))
    fun resourceAttributeInvalidHandler(exception: Exception): ResponseEntity<AppHttpResponse> {
        val errorName = when(exception) {
            is HttpMessageNotReadableException -> "httpMessageInvalid"
            is ResourceAttributeInvalidException -> "resourceAttributeInvalid"
            else -> "unknownError"
        }

        val appError = AppError(
            name = errorName,
            message = exception.message ?: ""
        )

        val responseBody = AppHttpResponse(
            error = listOf(appError)
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }



    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException): ResponseEntity<AppHttpResponse> {
        val fieldErrors = exception.bindingResult.getFieldErrors()
        val appErrors: MutableList<AppError> = mutableListOf()

        for(error in fieldErrors) {
            val erro = AppError().apply {
                name = error.field
                message = error.defaultMessage ?: ""
            }

            appErrors.add(erro)
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