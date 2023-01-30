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
	('Formulário de Egressos 2022', '2022-01-01', '2022-12-31 23:59:59', 'Formulário para coleta de dados de egressos em 2022'),
	('Formulário de Egressos 2023', '2023-01-01', '2023-12-31 23:59:59', 'Formulário para coleta de dados de egressos em 2023');
	
INSERT INTO trabalho.campos(cod_campo, titulo, descricao, tipo, obrigatorio, ordem, cod_formulario)
VALUES
	('possuiOutraGrad', 'Possui outra Graduação, mesmo incompleta?', null, 'combobox', true, 1, 2),
	('cursaOuCursouGrad', 'Cursa ou cursou Pós-Graduação stricto sensu?', null, 'combobox', true, 2, 2),
	('cursaOuCursouEspec', 'Cursa ou cursou Especialização?', null, 'combobox', true, 3, 2),
	('formacaoTeorica', 'Acerca do curso oferecido, como você avalia a formação teórica?', null, 'combobox', true, 4, 2),
	('formacaoPratica', 'Como você avalia a formação prática?', null, 'combobox', true, 5, 2),
	('duracaoDoCurso', 'Como você avalia a duração do curso?', null, 'combobox', true, 6, 2),
	('setorExercicio', 'Se você trabalha atualmente, qual o setor de exercício?', null, 'combobox', true, 7, 2),
	('exerceQuantoTempo', 'A quanto tempo você trabalha?', null, 'combobox', true, 8, 2),
	('vinculoTrabalho', 'Qual o seu vínculo de trabalho?', null, 'combobox', true, 9, 2),
	('funcaoCargo', 'Você exerce qual função ou cargo?', null, 'combobox', true, 10, 2),
	('relacaoFuncao', 'Qual a relação da função ou cargo com a formação realizada?', null, 'combobox', true, 11, 2),
	('renda', 'Qual a sua renda ou salário mensal?', null, 'combobox', true, 12, 2);
