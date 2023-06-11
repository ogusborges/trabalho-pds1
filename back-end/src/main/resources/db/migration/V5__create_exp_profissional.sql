CREATE TABLE ${flyway:defaultSchema}.exp_profissional(
    id SERIAL PRIMARY KEY,
    cargo VARCHAR(50) NOT NULL,
    empresa VARCHAR(30) NOT NULL,
    salario DOUBLE PRECISION,
    data_ini DATE,
    data_fim DATE,
    egresso_id INT,
    CONSTRAINT egresso_fk
    FOREIGN KEY(egresso_id)
    REFERENCES ${flyway:defaultSchema}.egresso(id)
)