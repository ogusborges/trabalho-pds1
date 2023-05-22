CREATE TABLE contato (
    id SERIAL PRIMARY KEY,
    pessoa_id integer NOT NULL,
    tipo varchar(20) NOT NULL,
    valor varchar(254) NOT NULL,

    CONSTRAINT fk_pessoa
    FOREIGN KEY (pessoa_id)
    REFERENCES pessoa (id)
)