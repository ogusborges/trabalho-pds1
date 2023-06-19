export interface FormaContato {
  id?: Number,
  tipo?: String,
  valor?: String,
}
  
export interface Contato {
  id?: Number,
  tipo?: String,
  valor?: String,
  index?: Number,
}

export interface InformacoesPessoais {
  nome: String
  sobrenome: String
  email: String
  tipo: String
}

export interface InformacoesInterno {}