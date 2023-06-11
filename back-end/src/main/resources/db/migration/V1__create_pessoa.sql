CREATE TABLE ${flyway:defaultSchema}.pessoa (
    id SERIAL,
    email VARCHAR(255) UNIQUE NOT NULL,
    nome VARCHAR(40),
    sobrenome VARCHAR(100),
    senha VARCHAR(60) NOT NULL,
    data_nasc TIMESTAMP,
    role INTEGER NOT NULL,

    CONSTRAINT pk_pessoa
    PRIMARY KEY(id)
)