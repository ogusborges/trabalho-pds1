import type { ApiHttpResponse } from '@/service/RequestResponse'
import axios, { AxiosError } from 'axios'
import type { ExperienciaProfissional, TipoBuscaEgresso , Egresso, ExperienciaAcademica} from '@/types/Egresso'
import { EgressoServiceException, EgressoServiceMessages } from '@/exception/EgressoServiceException'

export interface Token {
  token: String,
  dataExpiracao: Date,
  expirado: Boolean,
  egresso: String,
}


export interface PATCHEgressoRequest {
  email?: String,
  aceitouTermos?: Boolean,
  completouCadastro?: Boolean,
  escolaridades?: ExperienciaAcademica[],
  expProfissionais?: ExperienciaProfissional[],
}



export interface GETEgressoParams {
  email?: String,
  matricula?: String,
  token?: String,
  tipoBusca: TipoBuscaEgresso,
}



class CadastroEgressoService {
  private endpoint: String = 'egresso'

  public async getToken(token: String) {
    if (token == null || (token instanceof String && token.length <= 0)) {
      throw new EgressoServiceException(EgressoServiceMessages.MALFORMED_TOKEN)
    }

    try {
      const queryParams = {
        token
      }

      const response = await axios.get<ApiHttpResponse<Token>>(
        `http://localhost:8080/${this.endpoint}/token`,
        {
          params: queryParams
        }
      )

      if (response.status == 200) {
        return response.data.data
      }
    } catch (exception: any) {
      if (exception instanceof AxiosError) {
        switch(exception.status) {
          case 500:
            throw new EgressoServiceException(EgressoServiceMessages.INTERNAL_SERVER_ERROR)

          case 404:
            throw new EgressoServiceException(EgressoServiceMessages.NOT_FOUND)

          case 400:
            throw new EgressoServiceException(EgressoServiceMessages.BAD_REQUEST)

          default:
            throw new EgressoServiceException(EgressoServiceMessages.UNKNOWN_ERROR)
        }
      }
    }

    return false
  }

  public async getEgresso(info: GETEgressoParams): Promise<Boolean | Egresso> {
    if(info.tipoBusca == null || info.tipoBusca == undefined) {
      throw new EgressoServiceException(EgressoServiceMessages.INVALID_QUERY_TYPE)
    }

    try {
      const headers = {
        'Content-Type': 'application/json'
      }

      const response = await axios.get<ApiHttpResponse<Egresso>>(
        `http://localhost:8080/${this.endpoint}`,
        { params: info, headers: { ...headers }, data: null }
      )

      if(response.status == 200) return response.data.data
    } catch(exception: any) {
      if(exception instanceof AxiosError) {
        if(exception.response?.data) { return exception.response?.data.error }
      
        switch(exception.status) {
          case 500:
            throw new EgressoServiceException(EgressoServiceMessages.INTERNAL_SERVER_ERROR)

          case 404:
            throw new EgressoServiceException(EgressoServiceMessages.NOT_FOUND)

          case 400:
            throw new EgressoServiceException(EgressoServiceMessages.BAD_REQUEST)

          default:
            throw new EgressoServiceException(EgressoServiceMessages.UNKNOWN_ERROR)
        }
      }      
    }

    return false
  }

  public async updateEgresso(info: PATCHEgressoRequest): Promise<Boolean | {}> {
    try {
      const headers = {
        'Content-Type': 'application/json'
      }

      const response = await axios.patch<ApiHttpResponse<{}>>(
        `http://localhost:8080/${this.endpoint}`,
        JSON.stringify(info),
        { headers: { ...headers } },
      );

      if(response.status == 204) { return response.data.data }
    } catch (exception: any) {
      if(exception instanceof AxiosError) {
        if(exception.response?.data) { return exception.response?.data.error }
      
        switch(exception.status) {
          case 500:
            throw new EgressoServiceException(EgressoServiceMessages.INTERNAL_SERVER_ERROR)

          case 404:
            throw new EgressoServiceException(EgressoServiceMessages.NOT_FOUND)

          case 400:
            throw new EgressoServiceException(EgressoServiceMessages.BAD_REQUEST)

          default:
            throw new EgressoServiceException(EgressoServiceMessages.UNKNOWN_ERROR)
        }
      }
    }

    return false
  }

  public isValid(token: Token) {
    return token.expirado == false
  }
}

export default new CadastroEgressoService()
