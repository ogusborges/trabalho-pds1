import { type Contato } from './Pessoa'

export interface ExperienciaProfissional {
  id?: Number,
  cargo?: String,
  empresa?: String,
  salario?: String,
  dataInicio?: String,
  dataFim?: String,
  tecnologias?: String,
  informarSalario?: Boolean,
  empregoAtual?: Boolean,
  index?: Number,
}

export interface ExperienciaAcademica {
  id?: Number,
  instituicao?: String,
  tipo?: String,
  descricao?: String,
  dataInicio?: String,
  dataFim?: String,
  index?: Number,
}

export enum TipoBuscaEgresso {
  TOKEN = "TOKEN",
  EMAIL = "EMAIL",
  MATRICULA = "MATRICULA",
}

export interface Egresso {
  email: String,
  nome: String,
  matricula: String,
  sobrenome: String,
  dataNascimento: String,
  expProfissionais: ExperienciaProfissional[],
  infoAcademicas: ExperienciaAcademica[],
  contatos: Contato[],
}