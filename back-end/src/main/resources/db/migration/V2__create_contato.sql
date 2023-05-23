CREATE TABLE system.contato (
    id SERIAL PRIMARY KEY,
    pessoa_id integer,
    tipo varchar(20) NOT NULL,
    valor varchar(254) NOT NULL,

    CONSTRAINT fk_pessoa
    FOREIGN KEY (pessoa_id)
    REFERENCES system.pessoa (id) ON DELETE CASCADE
)