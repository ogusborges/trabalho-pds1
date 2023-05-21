package br.ufu.sisegresso.controller

import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.service.IPessoaService
import br.ufu.sisegresso.service.PessoaService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = arrayOf("/pessoa"),
    consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE),
    produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
)
class PessoaController(
    private val pessoaService: IPessoaService
) {

    @PostMapping("")
    @Transactional
    fun cadastrarPessoa(
        @Valid @RequestBody dadosPessoa: RegistroPessoaDTO
    ): ResponseEntity<Any> {

        pessoaService.cadastrar(dadosPessoa)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}