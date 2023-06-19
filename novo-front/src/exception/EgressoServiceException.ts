export class EgressoServiceException extends Error {
  constructor(message: string) {
    super(message)
  }
}

export enum EgressoServiceMessages {
  MALFORMED_TOKEN = 'Não foi possível realizar a validação com o token especificado',
  INTERNAL_SERVER_ERROR = 'Não foi possível realizar a verificação com o token especificado',
  NOT_FOUND = 'Não foi possível encontrar um token com os dados especificados',
  BAD_REQUEST = 'Não foi possível realizar a verificação com o token especificado',
  UNKNOWN_ERROR = 'Um erro não conhecido ocorreu. Por favor, tente novamente',
  INVALID_QUERY_TYPE = 'O parâmetro tipoBusca é inválido'
}
