package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.*
import br.ufu.sisegresso.exception.ResourceAlreadyExistsException
import br.ufu.sisegresso.exception.ResourceAttributeInvalidException
import br.ufu.sisegresso.exception.ResourceNotFoundException
import br.ufu.sisegresso.messages.EgressoMessage
import br.ufu.sisegresso.model.*
import br.ufu.sisegresso.repository.EgressoRepository
import br.ufu.sisegresso.repository.PessoaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDate


interface IEgressoService {
    fun cadastrar(dadosEgresso: CadastroEgressoDTO): Egresso
    fun atualizar(dadosAtualizacao: AtualizacaoEgressoDTO)
    fun recuperar(dadosBusca: RecuperarEgressoDTO): EgressoDTO
}

@Service
class EgressoService(
    private val pessoaRepo: PessoaRepository,
    private val pessoaService: IPessoaService,
    private val egressoRepo: EgressoRepository,
    private val tokenService: ITokenService,
    private val emailService: EmailService,
): IEgressoService{

    @Transactional
    override fun cadastrar(dadosEgresso: CadastroEgressoDTO): Egresso {
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
                sobrenome = dadosEgresso.sobrenome!!,
                role = Role.EGRESSO
            )

            pessoa = pessoaService.cadastrar(dadosPessoa)
        }

        var egresso = Egresso(
            matricula = dadosEgresso.matricula,
            pessoa = pessoa
        )

        egresso = egressoRepo.save(egresso)

        val token = tokenService.criar(CriarTokenDTO(egresso))

        emailService.sendEmail(
            EmailDTO(
                subject = "Cadastro - Sistema de Monitoramento de Egressos",
                messageBody = "Esse é seu token: ${token.token} e expira em ${token.dataExpiracao}",
                recipient = pessoa.email
            )
        )

        return egresso
    }

    override fun atualizar(dadosAtualizacao: AtualizacaoEgressoDTO) {
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
                        dataInicio = escolaridadeInfo.dataInicio ?: LocalDate.now(),
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
                        dataInicio = expProfissionalInfo.dataInicio ?: LocalDate.now(),
                        dataFim = expProfissionalInfo.dataFim,
                        tecnologias = expProfissionalInfo.tecnologias,
                        egresso = egresso
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
                        expProfissionalInfo.dataFim == null &&
                        expProfissionalInfo.tecnologias == null)
                    {
                        expProfissionais.remove(expProfissional)
                    } else {
                        expProfissional.apply {
                            cargo = expProfissionalInfo.cargo ?: cargo
                            empresa = expProfissionalInfo.empresa ?: empresa
                            salario = expProfissionalInfo.salario ?: salario
                            dataInicio = expProfissionalInfo.dataInicio ?: dataInicio
                            dataFim = expProfissionalInfo.dataFim ?: dataFim
                            tecnologias = expProfissionalInfo.tecnologias
                        }
                    }
                }
            }

            egresso.experienciaProf = expProfissionais
        }

        egressoRepo.save(egresso)
    }

    override fun recuperar(dadosBusca: RecuperarEgressoDTO): EgressoDTO {
        val egresso: Egresso? = when (dadosBusca.tipoBusca) {
            TipoBuscaEgresso.TOKEN -> dadosBusca.token?.let {
                val token = tokenService.recuperar(it)
                token.egresso?.let { egressoRepo.findByMatricula(it) }
            }

            TipoBuscaEgresso.EMAIL -> dadosBusca.email?.let { egressoRepo.findByPessoaEmail(it) }

            TipoBuscaEgresso.MATRICULA -> dadosBusca.matricula?.let { egressoRepo.findByMatricula(it) }

            else -> throw ResourceAttributeInvalidException(
                EgressoMessage.INVALID_QUERY_TYPE.name,
                EgressoMessage.INVALID_QUERY_TYPE.message,
            )
        }

        if (egresso == null) {
            throw ResourceNotFoundException(
                EgressoMessage.EGRESSO_NOT_FOUND.name,
                EgressoMessage.EGRESSO_NOT_FOUND.message,
            )
        }

        val listaContatos = egresso.pessoa?.contatos
            ?.map { ContatoDTO(id = it.id, tipo = it.tipo, valor = it.valor) }

        val listaExpAcademica = egresso.escolaridades
            .map {
                EscolaridadeDTO(
                    id = it.id,
                    instituicao = it.instituicao,
                    tipo = it.tipo,
                    descricao = it.descricao,
                    dataInicio = it.dataInicio,
                    dataFim = it.dataFim,
                )
            }

        val listaExpProfissionais = egresso.experienciaProf
            .map {
                ExperienciaProfissionalDTO(
                    id = it.id,
                    cargo = it.cargo,
                    empresa = it.empresa,
                    salario = it.salario,
                    dataInicio = it.dataInicio,
                    dataFim = it.dataFim,
                    tecnologias = it.tecnologias
                )

            }

        return EgressoDTO(
            email = egresso.pessoa?.email,
            nome = egresso.pessoa?.nome,
            sobrenome = egresso.pessoa?.sobrenome,
            matricula = egresso.matricula,
            contatos = listaContatos,
            expProfissionais = listaExpProfissionais,
            infoAcademicas = listaExpAcademica,
            dataNascimento = egresso.pessoa?.dataNascimento,
        )
    }
}