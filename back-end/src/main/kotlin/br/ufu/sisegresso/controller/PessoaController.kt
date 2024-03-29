package br.ufu.sisegresso.controller

import br.ufu.sisegresso.dtos.AtualizacaoPessoaDTO
import br.ufu.sisegresso.service.IPessoaService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
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
    @PatchMapping("")
    fun atualizarPessoa(
        @Valid @RequestBody dadosAtualizacao: AtualizacaoPessoaDTO
    ): ResponseEntity<Any> {
        pessoaService.atualizar(dadosAtualizacao)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}