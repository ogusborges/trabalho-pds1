export class PessoaServiceException extends Error {
    constructor(message: string) {
      super(message)
    }
  }
  
  export enum PessoaServiceMessages {
    INTERNAL_SERVER_ERROR = 'Não foi possível realizar completar a requisição com os dados especificados',
    NOT_FOUND = 'Não foi possível encontrar uma pessoa com os dados especificados',
    UNKNOWN_ERROR = 'Um erro não conhecido ocorreu. Por favor, tente novamente'
  }
  