-- ROLE
DROP ROLE "Prevenção_Pública" CASCADE;
CREATE ROLE "Prevenção_Pública";
GRANT SELECT, UPDATE ON "Acidente" TO "Prevenção_Pública";

-- USER
DROP USER "Divisão de Prevenção" CASCADE;

CREATE USER "Divisão de Prevenção" 
    IDENTIFIED BY a
    DEFAULT TABLESPACE fase2
    TEMPORARY TABLESPACE temp;
GRANT "Prevenção_Pública" TO "Divisão de Prevenção";

DROP VIEW "view_ciclista";
DROP VIEW "view_condutor";
DROP VIEW "view_local";

-- VIEW 1
CREATE VIEW "view_ciclista" AS
    SELECT "Nome_Ciclista", "Cartão_Cidadão_Ciclista"
    FROM "Acidente"
    WHERE "Acidente_Devido_Álcool" = 'Sim';

-- VIEW 2
CREATE VIEW "view_condutor" AS
    SELECT "Nome_Condutor", "Cartão_Condutor"
    FROM "Acidente"
    WHERE "Acidente_Devido_Álcool" = 'Sim';

-- VIEW 3
CREATE VIEW "view_local" AS
    SELECT "Latitude", "Longitude", "Mês_Acidente", "Ano_Acidente"
    FROM "Acidente"
    Where "Bateu_Fugiu" = 'Sim' AND "Acidente_Devido_Álcool" = 'Sim';