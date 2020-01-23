DROP TABLESPACE fase2 INCLUDING CONTENTS AND DATAFILES;

CREATE TABLESPACE fase2
    DATAFILE 'c:\oraclexe\tbs_fase2.dbf' 
    SIZE 300M 
    AUTOEXTEND ON;   

DROP TABLE "Condutor" CASCADE CONSTRAINTS;
DROP TABLE "Ciclista" CASCADE CONSTRAINTS;
DROP TABLE "Bicicleta" CASCADE CONSTRAINTS;
DROP TABLE "Condi��es_Local" CASCADE CONSTRAINTS;
DROP TABLE "Ve�culo" CASCADE CONSTRAINTS;
DROP TABLE "Acidente" CASCADE CONSTRAINTS;

CREATE TABLE "Bicicleta" 
(
    "Marca_Bicicleta" VARCHAR2( 20),
    "Modelo_Bicicleta" VARCHAR2( 20),
    "Ano_Aquisi��o" DATE,
    "Nome_Ciclista" VARCHAR2(29) NOT NULL,
    "Ident_Bicicleta" VARCHAR2(20),
    PRIMARY KEY ("Ident_Bicicleta")
);
CREATE TABLE "Ciclista" (
    "Cart�o_Cidad�o_Ciclista" VARCHAR2(12),
    "Nome" VARCHAR2(29) NOT NULL,
    "Faixa_Et�ria_Ciclista" VARCHAR2(6) NOT NULL,
    "Idade_Ciclista" NUMBER(3) NOT NULL,
    "Ciclista_Alcoolizado" VARCHAR2(26),
    "Usava_Prote��o" CHAR(3),
    "Ra�a_Ciclista" VARCHAR2(20),
    "G�nero_Ciclista" VARCHAR2(9),
    "N�mero_Ident_Bicicleta" VARCHAR2(20) NOT NULL,
    PRIMARY KEY ("Nome","Cart�o_Cidad�o_Ciclista"),
    CONSTRAINT fk_id_bicicleta FOREIGN KEY ("N�mero_Ident_Bicicleta") REFERENCES "Bicicleta"("Ident_Bicicleta")
);
CREATE TABLE "Condutor"
(    
    "Carta_Condu��o" VARCHAR(12) NOT NULL,
    "Cart�o_Cidad�o_Condutor" VARCHAR2(12) NOT NULL,
    "Nome_Condutor" VARCHAR2(29) NOT NULL,
    "Idade_Condutor" NUMBER(3),
    "Faixa_Et�ria_Condutor" VARCHAR(12),
    "Ra�a_Condutor" VARCHAR2(20),
    "G�nero_Condutor" VARCHAR2(9),
    "Condutor_Alcoolizado" VARCHAR2(26),
    "Ferimentos_Condutor" VARCHAR2(16),
    "Veiculo_Condutor"  VARCHAR2(8) NOT NULL,
    "Excesso_Velocidade"  VARCHAR2(8) NOT NULL,
    "Usava_Cinto_Seguran�a" CHAR(3),
    CONSTRaint cp PRIMARY KEY ("Cart�o_Cidad�o_Condutor", "Nome_Condutor")
);
CREATE TABLE "Ve�culo"
(
    "Matr�cula" VARCHAR2(8) NOT NULL,
    "Marca" VARCHAR2(15) NOT NULL,
    "Seguro" VARCHAR2(14) NOT NULL,
    "Ano_Aquisi��o" Number(1),
    "Modelo" VARCHAR2(10) NOT NULL,
    "Nome" VARCHAR2(29) NOT NULL,
    "Cart�o_Cidad�o" VARCHAR2(12) NOT NULL,
    CONSTRAINT cp_matricula PRIMARY KEY ("Matr�cula"),
    CONSTRAINT fk_nome FOREIGN KEY ("Cart�o_Cidad�o","Nome") REFERENCES "Condutor"("Cart�o_Cidad�o_Condutor", "Nome_Condutor")
);
CREATE TABLE "Condi��es_Local"
(
    "Condi��es_Luminosidade" VARCHAR2(14) NOT NULL,
    "Localidade" VARCHAR2(12) NOT NULL,
    "N�mero_Vias" NUMBER(1),
    "Caracterisiticas_Via" NUMBER(30),
    "Classifica��o_Via" NUMBER(2),
    "Condi��o_Via" NUMBER(1) NOT NULL,
    "Configura��o_Via"  VARCHAR2(12),
    "Rua_Defeitos" VARCHAR2(20) NOT NULL,
    "Mais_Detalhes_Via" VARCHAR2(20) NOT NULL,
    "Superf�cie_Via" VARCHAR2(20) NOT NULL,
    "Rural_Urbano" VARCHAR2(7) NOT NULL,
    "Velocidade_Limite" VARCHAR2(7) NOT NULL,
    "Controlo_Tr�fego" VARCHAR2(20) NOT NULL,
    "Zona_Trabalho" VARCHAR2(20) NOT NULL,
    "Clima"  VARCHAR2(20) NOT NULL,
    "Rua" VARCHAR2(20) NOT NULL,
    "Velo_Est_do_Condutor" VARCHAR2(10) NOT NULL,
    "Longitude" NUMBER(2,9) NOT NULL,
    "Latitude" NUMBER(3,9) NOT NULL,
    "Cidade" VARCHAR2(20) NOT NULL,
    "Pa�s" VARCHAR2(20) NOT NULL,
    PRIMARY KEY ("Condi��es_Luminosidade","Condi��o_Via","Longitude","Latitude")
);
CREATE TABLE "Acidente"
(
    "Local_Acidente" VARCHAR2(29) NOT NULL,
    "Longitude" NUMBER(2,9) NOT NULL,
    "Latitude" NUMBER(3,9) NOT NULL,
    "Ambul�ncia" VARCHAR2(25),
    "Nome_Condutor" VARCHAR2(29) NOT NULL,
    "Cart�o_Condutor" VARCHAR2(12) NOT NULL,
    "Nome_Ciclista" VARCHAR2(29) NOT NULL,
    "Cart�o_Cidad�o_Ciclista" VARCHAR2(12) NOT NULL,
    "Acidente_Devido_�lcool"  CHAR(3),
    "Dia_Semana_Acidente" VARCHAR2(7) NOT NULL,
    "Envolvidos_Acidente" NUMBER(2),
    "Hora_Acidente" NUMBER(2)  NOT NULL,
    "M�s_Acidente" VARCHAR2(20) NOT NULL,
    "Hora_Espec�fica_Acidente"  DATE NOT NULL,
    "Tipo_acidente" VARCHAR2(30) NOT NULL,
    "Ano_Acidente" NUMBER(4) NOT NULL,
    "Zona_Acidente" VARCHAR2(10) NOT NULL,
    "Bateu_Fugiu" VARCHAR2(9),
    "Rua" VARCHAR2(40) NOT NULL,
    "N�mero_Envolvidos" NUMBER(2),
    CONSTRAINT pk_acidente PRIMARY KEY("Hora_Espec�fica_Acidente", "Longitude", "Latitude", "Cart�o_Condutor", "Cart�o_Cidad�o_Ciclista"),
    CONSTRAINT condutorkey 
        FOREIGN KEY ("Cart�o_Condutor", "Nome_Condutor")
        REFERENCES "Condutor" ("Cart�o_Cidad�o_Condutor", "Nome_Condutor"),
    CONSTRAINT ciclistakey
        FOREIGN KEY ("Nome_Ciclista","Cart�o_Cidad�o_Ciclista")
        REFERENCES "Ciclista"("Nome","Cart�o_Cidad�o_Ciclista"),
    CONSTRAINT ambulanciacheck
      CHECK ("Ambul�ncia" = 'Ambul�ncia Desnecess�ria' or "Ambul�ncia" = 'Ambul�ncia Necess�ria')
);