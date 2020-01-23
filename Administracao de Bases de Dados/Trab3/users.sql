-- ROLE
DROP ROLE "Preven��o_P�blica" CASCADE;
CREATE ROLE "Preven��o_P�blica";
GRANT SELECT, UPDATE ON "Acidente" TO "Preven��o_P�blica";

-- USER
DROP USER "Divis�o de Preven��o" CASCADE;

CREATE USER "Divis�o de Preven��o" 
    IDENTIFIED BY a
    DEFAULT TABLESPACE fase2
    TEMPORARY TABLESPACE temp;
GRANT "Preven��o_P�blica" TO "Divis�o de Preven��o";

DROP VIEW "view_ciclista";
DROP VIEW "view_condutor";
DROP VIEW "view_local";

-- VIEW 1
CREATE VIEW "view_ciclista" AS
    SELECT "Nome_Ciclista", "Cart�o_Cidad�o_Ciclista"
    FROM "Acidente"
    WHERE "Acidente_Devido_�lcool" = 'Sim';

-- VIEW 2
CREATE VIEW "view_condutor" AS
    SELECT "Nome_Condutor", "Cart�o_Condutor"
    FROM "Acidente"
    WHERE "Acidente_Devido_�lcool" = 'Sim';

-- VIEW 3
CREATE VIEW "view_local" AS
    SELECT "Latitude", "Longitude", "M�s_Acidente", "Ano_Acidente"
    FROM "Acidente"
    Where "Bateu_Fugiu" = 'Sim' AND "Acidente_Devido_�lcool" = 'Sim';