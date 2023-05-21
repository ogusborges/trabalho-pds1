CREATE TABLE system.pessoa (
    id SERIAL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(40),
    sobrenome VARCHAR(100),
    senha VARCHAR(60) NOT NULL,
    data_nasc TIMESTAMP,
    aceitou_termos BOOLEAN NOT NULL DEFAULT FALSE,
    completou_cadastro BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT pk_pessoa
    PRIMARY KEY(id)
)