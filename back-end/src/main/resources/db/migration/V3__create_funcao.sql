CREATE TABLE funcao (
    id SERIAL PRIMARY KEY,
    descricao CHAR(7) NOT NULL,
    pessoa_id INTEGER NOT NULL,

    CONSTRAINT fk_pessoa
    FOREIGN KEY (pessoa_id)
    REFERENCES pessoa(id)
)