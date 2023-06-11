package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.AtualizacaoEgressoDTO
import br.ufu.sisegresso.dtos.CadastroEgressoDTO
import br.ufu.sisegresso.dtos.CriarTokenDTO
import br.ufu.sisegresso.dtos.RegistroPessoaDTO
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.messages.EgressoMessage
import br.ufu.sisegresso.model.Egresso
import br.ufu.sisegresso.model.Escolaridade
import br.ufu.sisegresso.model.ExperienciaProfissional
import br.ufu.sisegresso.model.Pessoa
import br.ufu.sisegresso.repository.EgressoRepository
import br.ufu.sisegresso.repository.PessoaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


interface IEgressoService {
    fun cadastrarEgresso(dadosEgresso: CadastroEgressoDTO): Egresso
    fun atualizarEgresso(dadosAtualizacao: AtualizacaoEgressoDTO)
}

@Service
class EgressoService(
    private val pessoaRepo: PessoaRepository,
    private val pessoaService: IPessoaService,
    private val egressoRepo: EgressoRepository,
    private val tokenService: ITokenService,
): IEgressoService{

    @Transactional
    override fun cadastrarEgresso(dadosEgresso: CadastroEgressoDTO): Egresso {
        if (egressoRepo.existsEgressoByMatricula(dadosEgresso.matricula!!)) {
            throw ResourceAlreadyExistsException(
                EgressoMessage.EGRESSO_ALREADY_EXISTS.name,
                EgressoMessage.EGRESSO_ALREADY_EXISTS.message
            )
        }

        var pessoa: Pessoa? = pessoaRepo.findByEmail(dadosEgresso.email!!)

        if (pessoa == null) {
            val dadosPessoa = RegistroPessoaDTO(
                email = dadosEgresso.email,
                nome = dadosEgresso.nome!!,
                sobrenome = dadosEgresso.sobrenome!!
            )

            pessoa = pessoaService.cadastrar(dadosPessoa)
        }

        var egresso = Egresso(
            matricula = dadosEgresso.matricula,
            pessoa = pessoa
        )

        egresso = egressoRepo.save(egresso)

        tokenService.criarToken(CriarTokenDTO(egresso))

        return egresso
    }

    override fun atualizarEgresso(dadosAtualizacao: AtualizacaoEgressoDTO) {
        val egresso: Egresso? = egressoRepo.findByPessoaEmail(dadosAtualizacao.email!!)

        if(egresso == null) {
            throw ResourceNotFoundException(
                EgressoMessage.EGRESSO_NOT_FOUND.name,
                EgressoMessage.EGRESSO_NOT_FOUND.message
            )
        }

        egresso.apply {
            aceitouTermos = dadosAtualizacao.aceitouTermos ?: aceitouTermos
            completouCadastro = dadosAtualizacao.completouCadastro ?: completouCadastro
        }

        if(dadosAtualizacao.escolaridades != null) {
            val escolaridades: MutableList<Escolaridade> = egresso.escolaridades

            for(escolaridadeInfo in dadosAtualizacao.escolaridades) {
                if(escolaridadeInfo.id == null) {
                    val escolaridade = Escolaridade(
                        instituicao = escolaridadeInfo.instituicao ?: "",
                        tipo = escolaridadeInfo.tipo ?: "",
                        descricao = escolaridadeInfo.descricao ?: "",
                        dataInicio = escolaridadeInfo.dataInicio,
                        dataFim = escolaridadeInfo.dataFim,
                        egresso = egresso
                    )

                    escolaridades.add(escolaridade)
                } else {
                    val escolaridade: Escolaridade? = escolaridades.find { it.id == escolaridadeInfo.id }

                    if(escolaridade == null) {
                        throw ResourceNotFoundException(
                            EgressoMessage.ESCOLARIDADE_NOT_FOUND.name,
                            EgressoMessage.ESCOLARIDADE_NOT_FOUND.message
                        )
                    }

                    if (escolaridadeInfo.instituicao == null &&
                        escolaridadeInfo.tipo == null &&
                        escolaridadeInfo.descricao == null &&
                        escolaridadeInfo.dataInicio == null &&
                        escolaridadeInfo.dataFim == null
                    ) {
                        escolaridades.remove(escolaridade)
                    } else {
                        escolaridade.apply {
                            instituicao = escolaridadeInfo.instituicao ?: instituicao
                            tipo = escolaridadeInfo.tipo ?: tipo
                            descricao = escolaridadeInfo.descricao ?: descricao
                            dataInicio = escolaridadeInfo.dataInicio ?: dataInicio
                            dataFim = escolaridadeInfo.dataFim ?: dataFim
                        }
                    }
                }
            }
            egresso.escolaridades = escolaridades
        }

        if(dadosAtualizacao.expProfissionais != null) {
            val expProfissionais = egresso.experienciaProf

            for(expProfissionalInfo in dadosAtualizacao.expProfissionais) {
                if(expProfissionalInfo.id == null) {
                    val expProfissional = ExperienciaProfissional(
                        cargo = expProfissionalInfo.cargo ?: "",
                        empresa = expProfissionalInfo.empresa ?: "",
                        salario = expProfissionalInfo.salario,
                        dataInicio = expProfissionalInfo.dataInicio,
                        dataFim = expProfissionalInfo.dataFim
                    )

                    expProfissionais.add(expProfissional)
                } else {
                    val expProfissional = expProfissionais.find { it.id == expProfissionalInfo.id }

                    if(expProfissional == null) {
                        throw ResourceNotFoundException(
                            EgressoMessage.EXP_PROFISSIONAL_NOT_FOUND.name,
                            EgressoMessage.EXP_PROFISSIONAL_NOT_FOUND.message
                        )
                    }

                    if(expProfissionalInfo.cargo == null &&
                        expProfissionalInfo.empresa == null &&
                        expProfissionalInfo.salario == null &&
                        expProfissionalInfo.dataInicio == null &&
                        expProfissionalInfo.dataFim == null)
                    {
                        expProfissionais.remove(expProfissional)
                    } else {
                        expProfissional.apply {
                            cargo = expProfissionalInfo.cargo ?: cargo
                            empresa = expProfissionalInfo.empresa ?: empresa
                            salario = expProfissionalInfo.salario ?: salario
                            dataInicio = expProfissionalInfo.dataInicio ?: dataInicio
                            dataFim = expProfissionalInfo.dataFim ?: dataFim
                        }
                    }
                }
            }

            egresso.experienciaProf = expProfissionais
        }

        egressoRepo.save(egresso)
    }
}