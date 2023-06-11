CREATE TABLE ${flyway:defaultSchema}.escolaridade (
    id SERIAL PRIMARY KEY,
    instituicao VARCHAR(100) NOT NULL,
    tipo VARCHAR(15) NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    data_ini DATE NOT NULL,
    data_fim DATE,
    egresso_id INT,

    CONSTRAINT egresso_fk
    FOREIGN KEY (egresso_id)
    REFERENCES ${flyway:defaultSchema}.egresso(id)
);