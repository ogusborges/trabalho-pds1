CREATE TABLE ${flyway:defaultSchema}.token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(32) NOT NULL,
    data_expiracao TIMESTAMP NOT NULL,
    egresso_id INTEGER,

    CONSTRAINT egresso_fk
    FOREIGN KEY (egresso_id)
    REFERENCES ${flyway:defaultSchema}.egresso(id)
)