package br.ufu.sisegresso.controller

import br.ufu.sisegresso.dtos.AtualizacaoEgressoDTO
import br.ufu.sisegresso.dtos.CadastroEgressoDTO
import br.ufu.sisegresso.service.IEgressoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    path = arrayOf("/egresso"),
    consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE),
    produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
)
class EgressoController(
    private val egressoService: IEgressoService
) {
    @PostMapping("")
    fun cadastrarEgresso(
        @Valid @RequestBody dadosEgresso: CadastroEgressoDTO,
    ): ResponseEntity<Any> {
        egressoService.cadastrarEgresso(dadosEgresso)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @PatchMapping
    fun atualizarEgresso(
        @Valid @RequestBody dadosAtualizacao: AtualizacaoEgressoDTO,
    ): ResponseEntity<Any> {
        egressoService.atualizarEgresso(dadosAtualizacao)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}