CREATE TABLE system.funcao (
    id SERIAL PRIMARY KEY,
    descricao CHAR(7) NOT NULL,
    pessoa_id INTEGER,

    CONSTRAINT fk_pessoa
    FOREIGN KEY (pessoa_id)
    REFERENCES system.pessoa(id) ON DELETE CASCADE
)