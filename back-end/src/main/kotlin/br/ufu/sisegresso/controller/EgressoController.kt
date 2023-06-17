package br.ufu.sisegresso.controller

import br.ufu.sisegresso.dtos.AppHttpResponse
import br.ufu.sisegresso.dtos.AtualizacaoEgressoDTO
import br.ufu.sisegresso.dtos.CadastroEgressoDTO
import br.ufu.sisegresso.dtos.RecuperarEgressoDTO
import br.ufu.sisegresso.service.IEgressoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
    path = arrayOf("/egresso"),
    consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE),
    produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
)
class EgressoController(
    private val egressoService: IEgressoService
) {
    @GetMapping
    fun recuperarEgresso(
        @Valid dadosBusca: RecuperarEgressoDTO,
    ): ResponseEntity<Any> {
        val egresso = egressoService.recuperar(dadosBusca)

        val responseBody = AppHttpResponse(
            data = egresso
        )

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(responseBody)
    }

    @PostMapping("")
    fun cadastrarEgresso(
        @Valid @RequestBody dadosEgresso: CadastroEgressoDTO,
    ): ResponseEntity<Any> {
        egressoService.cadastrar(dadosEgresso)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @PatchMapping
    fun atualizarEgresso(
        @Valid @RequestBody dadosAtualizacao: AtualizacaoEgressoDTO,
    ): ResponseEntity<Any> {
        egressoService.atualizar(dadosAtualizacao)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}