CREATE SCHEMA trabalho AUTHORIZATION postgres;

CREATE TABLE trabalho.formulario (
	cod_formulario serial4 NOT NULL,
	titulo varchar(50) NOT NULL,
	data_ini timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	data_fim timestamp NULL,
	descricao varchar(100) NULL,
	CONSTRAINT formulario_pkey PRIMARY KEY (cod_formulario)
);

CREATE TABLE trabalho.campos (
	cod_campo varchar(30) NOT NULL,
	titulo varchar(100) NOT NULL,
	descricao varchar(50) NULL,
	tipo varchar(15) NOT NULL,
	obrigatorio bool NOT NULL DEFAULT false,
	ordem int4 NOT NULL,
	cod_formulario int4 NOT NULL,
	CONSTRAINT campos_pkey PRIMARY KEY (cod_campo)
);

ALTER TABLE trabalho.campos ADD CONSTRAINT campos_cod_formulario_fkey FOREIGN KEY (cod_formulario) REFERENCES trabalho.formulario(cod_formulario) ON DELETE CASCADE;

CREATE TABLE trabalho.respostas (
	cod_formulario int4 NOT NULL,
	cod_campo varchar(30) NOT NULL,
	resposta varchar(50) NULL
);

INSERT INTO trabalho.formulario(titulo, data_ini, data_fim, descricao)
VALUES
	('Formul�rio de Egressos 2023', '2023-01-01', '2023-12-31 23:59:59', 'Formul�rio para coleta de dados de egressos em 2023');
	
INSERT INTO trabalho.campos(cod_campo, titulo, descricao, tipo, obrigatorio, ordem, cod_formulario)
VALUES
	('possuiOutraGrad', 'Possui outra Gradua��o, mesmo incompleta?', null, 'combobox', true, 1, 2)
	('cursaOuCursouGrad', 'Cursa ou cursou P�s-Gradua��o stricto sensu?', null, 'combobox', true, 2, 2)
	('cursaOuCursouEspec', 'Cursa ou cursou Especializa��o?', null, 'combobox', true, 3, 2)
	('formacaoTeorica', 'Acerca do curso oferecido, como voc� avalia a forma��o te�rica?', null, 'combobox', true, 4, 2)
	('formacaoPratica', 'Como voc� avalia a forma��o pr�tica?', null, 'combobox', true, 5, 2)
	('duracaoDoCurso', 'Como voc� avalia a dura��o do curso?', null, 'combobox', true, 6, 2)
	('setorExercicio', 'Se voc� trabalha atualmente, qual o setor de exerc�cio?', null, 'combobox', true, 7, 2)
	('exerceQuantoTempo', 'A quanto tempo voc� trabalha?', null, 'combobox', true, 8, 2)
	('vinculoTrabalho', 'Qual o seu v�nculo de trabalho?', null, 'combobox', true, 9, 2)
	('funcaoCargo', 'Voc� exerce qual fun��o ou cargo?', null, 'combobox', true, 10, 2)
	('relacaoFuncao', 'Qual a rela��o da fun��o ou cargo com a forma��o realizada?', null, 'combobox', true, 11, 2)
	('renda', 'Qual a sua renda ou sal�rio mensal?', null, 'combobox', true, 12, 2);