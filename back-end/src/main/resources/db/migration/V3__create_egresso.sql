CREATE TABLE ${flyway:defaultSchema}.egresso (
    id SERIAL PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL,
    data_ult_notif TIMESTAMP,
    completou_cadastro BOOLEAN DEFAULT false,
    aceitou_termos BOOLEAN DEFAULT false,
    pessoa_id INTEGER,

    CONSTRAINT pessoa_fk
    FOREIGN KEY (pessoa_id)
    REFERENCES ${flyway:defaultSchema}.pessoa(id),

    CONSTRAINT matricula_uniq
    UNIQUE (matricula)
);