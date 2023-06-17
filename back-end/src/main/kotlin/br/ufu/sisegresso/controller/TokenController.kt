package br.ufu.sisegresso.controller

import br.ufu.sisegresso.dtos.AppHttpResponse
import br.ufu.sisegresso.dtos.RegenerarTokenDTO
import br.ufu.sisegresso.dtos.TokenDTO
import br.ufu.sisegresso.service.ITokenService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/egresso/token")
class TokenController(
    private val tokenService: ITokenService
) {

    @GetMapping("")
    fun recuperarToken(
        @RequestParam token: String,
    ): ResponseEntity<AppHttpResponse> {
        val responseBody = AppHttpResponse(
            data = tokenService.recuperar(token)
        )

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(responseBody)
    }

    @PostMapping
    fun regenerarToken(
        @Valid @RequestBody dadosToken: RegenerarTokenDTO,
    ): ResponseEntity<AppHttpResponse> {
        tokenService.regenerar(dadosToken)

        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}