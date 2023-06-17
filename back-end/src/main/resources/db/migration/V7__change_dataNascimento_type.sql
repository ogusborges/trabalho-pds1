BEGIN TRANSACTION;

ALTER TABLE ${flyway:defaultSchema}.pessoa
ADD COLUMN data_nasc_dt DATE;

UPDATE ${flyway:defaultSchema}.pessoa
SET data_nasc_dt = DATE(data_nasc)
WHERE 1 = 1;

ALTER TABLE ${flyway:defaultSchema}.pessoa
DROP COLUMN data_nasc;

ALTER TABLE ${flyway:defaultSchema}.pessoa
RENAME COLUMN data_nasc_dt TO data_nasc;

COMMIT TRANSACTION;