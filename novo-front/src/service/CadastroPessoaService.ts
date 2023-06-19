import { PessoaServiceException, PessoaServiceMessages } from '@/exception/PessoaServiceException'
import { EntityValidationException } from '@/exception/EntityValidationException'
import type { Contato, InformacoesPessoais, InformacoesInterno } from '@/types/Pessoa'
import {
  isApiError,
  type ApiError,
  type ApiHttpResponse,
  type ValidationError,
  isFieldValidationError
} from '@/service/RequestResponse'
import axios, { Axios, AxiosError } from 'axios'


export enum TipoContato {
  TELEFONE = "TELEFONE",
  EMAIL = "EMAIL",
  LINKEDIN = "LINKEDIN",
  CELULAR = "CELULAR",
  GITHUB = "GITHUB",
  INSTAGRAM = "INSTAGRAM",
  FACEBOOK = "FACEBOOK",
  TWITTER = "TWITTER",
}

export interface PATCHPessoaRequest {
  email?: String,
  nome?: String,
  sobrenome?: String,
  senha?: String,
  dataNascimento?: String,
  contatos?: Contato[],
}

interface InformacoesEgresso {
  matricula: String
}

interface POSTCadastroInterno {}

interface POSTCadastroEgresso {
  email: String
  matricula: String
  nome: String
  sobrenome: String
}

export interface SendEntityInfo {
  pessoais: InformacoesPessoais
  entidade: InformacoesEgresso | InformacoesInterno
}

class EntityService {
  public async sendEntity(info: SendEntityInfo): Promise<Boolean | ApiError | ApiError[]> {
    const validationErrors = new Array<ValidationError>()
      .concat(this.validateEntityInfo(info.pessoais))
      .concat(this.validateEntityInfo(info.entidade))

    if (validationErrors.length > 0) {
      throw new EntityValidationException('Erro ao validar os dados de entidade', validationErrors)
    }

    const endpoint = this.getEndpoint(info.pessoais.tipo)

    const headers = {
      'Content-Type': 'application/json',
      Authorization: ''
    }

    let requestData: POSTCadastroEgresso | POSTCadastroInterno = {
      email: info.pessoais.email,
      nome: info.pessoais.nome,
      sobrenome: info.pessoais.sobrenome
    }

    if (this.isEgresso(info.entidade)) {
      requestData = {
        ...requestData,
        matricula: info.entidade.matricula
      }
    } else if (this.isInterno(info.entidade) == true) {
      requestData = {
        ...requestData
      }
    } else {
      throw new EntityValidationException('Entidade informada não é conhecida', validationErrors)
    }

    try {
      const { status, data } = await axios.post<ApiHttpResponse<{}>>(
        `http://localhost:8080/${endpoint}`,
        JSON.stringify(requestData),
        { headers }
      )

      if (status == 204) return true
      else throw new Error()
    } catch (exception: any) {
      if (exception instanceof AxiosError) {
        const responseError = exception.response?.data.error

        if (isApiError(responseError) || isFieldValidationError(responseError)) return responseError
        else return false
      } else {
        return false
      }
    }
  }

  public async updatePessoa(info: PATCHPessoaRequest) {
    const headers = {
      'Content-Type': 'application/json',
      Authorization: ''
    }

    const endpoint = 'pessoa'

    try {
      const response = await axios.patch<ApiHttpResponse<{}>>(
        `http://localhost:8080/${endpoint}`,
        JSON.stringify(info),
        { headers: headers }
      )

      if(response.status == 204) { return response.data.data }
    } catch(exception: any) {
      if(exception instanceof AxiosError) {
        if(exception?.response?.data) { return exception.response?.data.error }
        switch(exception.status) {
          case 500:
            throw new PessoaServiceException(PessoaServiceMessages.INTERNAL_SERVER_ERROR)

          case 404:
            throw new PessoaServiceException(PessoaServiceMessages.NOT_FOUND)

          default:
            throw new PessoaServiceException(PessoaServiceMessages.UNKNOWN_ERROR)
        }
      }
    }

    return false
  }

  public isEgresso(info: InformacoesEgresso | InformacoesInterno): info is InformacoesEgresso {
    return 'matricula' in info
  }

  public isInterno(info: InformacoesEgresso | InformacoesInterno) {
    return false
  }

  public validateEntityInfo(
    info: InformacoesPessoais | InformacoesEgresso | InformacoesInterno
  ): ValidationError[] {
    const validationErrors: ValidationError[] = new Array<ValidationError>()

    for (let [field, value] of Object.entries(info)) {
      const isError =
        value == null || value == undefined || (typeof value === 'string' && value.length == 0)

      if (isError) {
        validationErrors.push({
          field,
          result: isError
        })
      }
    }

    return validationErrors
  }

  private getEndpoint(entityType: String): String {
    if (entityType == 'EGRESSO') return 'egresso'
    else if (entityType == 'INTERNO') return 'interno'
    else throw new Error()
  }
}

export default new EntityService()
