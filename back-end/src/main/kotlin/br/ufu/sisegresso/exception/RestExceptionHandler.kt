package br.ufu.sisegresso.exception

import br.ufu.sisegresso.dtos.Erro
import br.ufu.sisegresso.dtos.RespostaHttp
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException


@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException::class)
    fun resourceAlreadyExistsHandler(exception: ResourceAlreadyExistsException): ResponseEntity<RespostaHttp> {
        val error: Erro = when(exception.message) {
            null -> Erro(nome = "resourceAlreadyExists", mensagem = "")
            else -> Erro(nome = "resourceAlreadyExists", mensagem = exception.message!!)
        }

        val responseBody = RespostaHttp(
            status = HttpStatus.BAD_REQUEST.value(),
            errorCount = 1,
            errors = listOf(error)
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }

    @ExceptionHandler(*arrayOf(
        ResourceAttributeInvalidException::class,
        HttpMessageNotReadableException::class
    ))
    fun resourceAttributeInvalidHandler(exception: Exception): ResponseEntity<RespostaHttp> {
        val errorName = when(exception) {
            is HttpMessageNotReadableException -> "httpMessageInvalid"
            is ResourceAttributeInvalidException -> "resourceAttributeInvalid"
            else -> "unknownError"
        }

        val error: Erro = Erro(
            nome = errorName,
            mensagem = exception.message ?: ""
        )

        val responseBody = RespostaHttp(
            status = HttpStatus.BAD_REQUEST.value(),
            errorCount = 1,
            errors = listOf(error)
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }

    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(exception: ResourceNotFoundException): ResponseEntity<RespostaHttp> {
        val errorName = "resourceNotFoundException"

        val error: Erro = Erro(
            nome = errorName,
            mensagem = exception.message ?: ""
        )

        val responseBody = RespostaHttp(
            status = HttpStatus.BAD_REQUEST.value(),
            errorCount = 1,
            errors = listOf(error)
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidExceptionHandler(exception: MethodArgumentNotValidException): ResponseEntity<RespostaHttp> {
        val fieldErrors = exception.bindingResult.getFieldErrors()
        val errors: MutableList<Erro> = mutableListOf()

        for(error in fieldErrors) {
            val erro = Erro().apply {
                nome = error.field
                mensagem = error.defaultMessage ?: ""
            }

            errors.add(erro)
        }

        val responseBody: RespostaHttp = RespostaHttp(
            status = HttpStatus.BAD_REQUEST.value(),
            errorCount = errors.size,
            errors = errors
        )

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(responseBody)
    }
}