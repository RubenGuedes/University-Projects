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
    "Ano_Aquisi��o" NUMBER(4),
    "Nome_Ciclista" VARCHAR2(29) NOT NULL,
    "Ident_Bicicleta" VARCHAR2(20),
    PRIMARY KEY ("Ident_Bicicleta")
);
CREATE TABLE "Ciclista"(
    "Cart�o_Cidad�o_Ciclista" VARCHAR2(12),
    "Nome" VARCHAR2(29) NOT NULL,
    "Faixa_Et�ria_Ciclista" VARCHAR2(15) NOT NULL,
    "Idade_Ciclista" NUMBER(3) NOT NULL,
    "Ciclista_Alcoolizado" VARCHAR2(26),
    "Usava_Prote��o" CHAR(4),
    "Ra�a_Ciclista" VARCHAR2(25),
    "G�nero_Ciclista" VARCHAR2(15),
    "N�mero_Ident_Bicicleta" VARCHAR2(20) NOT NULL,
    PRIMARY KEY ("Nome","Cart�o_Cidad�o_Ciclista"),
    CONSTRAINT fk_id_bicicleta FOREIGN KEY ("N�mero_Ident_Bicicleta") REFERENCES "Bicicleta"("Ident_Bicicleta")
);
CREATE TABLE "Condutor"
(    
    "Carta_Condu��o" VARCHAR(12) NOT NULL,
    "Cart�o_Cidad�o_Condutor" VARCHAR2(12) NOT NULL,
    "Nome_Condutor" VARCHAR2(29) NOT NULL,
    "Idade_Condutor" Varchar2(15),
    "Faixa_Et�ria_Condutor" VARCHAR(12),
    "Ra�a_Condutor" VARCHAR2(25),
    "G�nero_Condutor" VARCHAR2(15),
    "Condutor_Alcoolizado" VARCHAR2(26),
    "Ferimentos_Condutor" VARCHAR2(25),
    "Veiculo_Condutor"  VARCHAR2(40) NOT NULL,
    "Excesso_Velocidade"  VARCHAR2(30) NOT NULL,
    "Usava_Cinto_Seguran�a" CHAR(4),
    CONSTRaint cp PRIMARY KEY ("Cart�o_Cidad�o_Condutor", "Nome_Condutor")
);
CREATE TABLE "Ve�culo"
(
    "Matr�cula" VARCHAR2(8) NOT NULL,
    "Marca" VARCHAR2(15) NOT NULL,
    "Seguro" VARCHAR2(14) NOT NULL,
    "Ano_Aquisi��o" Number(4),
    "Modelo" VARCHAR2(10) NOT NULL,
    "Nome" VARCHAR2(29) NOT NULL,
    "Cart�o_Cidad�o" VARCHAR2(12) NOT NULL,
    CONSTRAINT cp_matricula PRIMARY KEY ("Matr�cula"),
    CONSTRAINT fk_nome FOREIGN KEY ("Cart�o_Cidad�o","Nome") REFERENCES "Condutor"("Cart�o_Cidad�o_Condutor", "Nome_Condutor")
);

CREATE TABLE "Condi��es_Local"
(
    "Condi��es_Luminosidade" VARCHAR2(45) NOT NULL,
    "Localidade" VARCHAR2(50) NOT NULL,
    "N�mero_Vias" VARCHAR2(20),
    "Caracterisiticas_Via" VARCHAR2(30),
    "Classifica��o_Via" VARCHAR2(80),
    "Condi��o_Via" VARCHAR2(30) NOT NULL,
    "Configura��o_Via"  VARCHAR2(50),
    "Rua_Defeitos" VARCHAR2(20) NOT NULL,
    "Mais_Detalhes_Via" VARCHAR2(45) NOT NULL,
    "Superf�cie_Via" VARCHAR2(20) NOT NULL,
    "Rural_Urbano" VARCHAR2(7) NOT NULL,
    "Velocidade_Limite" VARCHAR2(15) NOT NULL,
    "Controlo_Tr�fego" VARCHAR2(80) NOT NULL,
    "Zona_Trabalho" VARCHAR2(25) NOT NULL,
    "Clima"  VARCHAR2(20) NOT NULL,
    "Rua" VARCHAR2(80),
    "Velo_Est_do_Condutor" VARCHAR2(15) NOT NULL,
    "Longitude" NUMBER NOT NULL,
    "Latitude" NUMBER NOT NULL,
    "Cidade" VARCHAR2(50) NOT NULL,
    "Pa�s" VARCHAR2(20) NOT NULL,
    PRIMARY KEY ("Condi��es_Luminosidade","Condi��o_Via","Longitude","Latitude")
);
CREATE TABLE "Acidente"
(
    "Local_Acidente" VARCHAR2(29) NOT NULL,
    "Longitude" NUMBER NOT NULL,
    "Latitude" NUMBER NOT NULL,
    "Ambul�ncia" VARCHAR2(26),
    "Nome_Condutor" VARCHAR2(29) NOT NULL,
    "Cart�o_Condutor" VARCHAR2(12) NOT NULL,
    "Nome_Ciclista" VARCHAR2(29) NOT NULL,
    "Cart�o_Cidad�o_Ciclista" VARCHAR2(12) NOT NULL,
    "Acidente_Devido_�lcool"  CHAR(4),
    "Dia_Semana_Acidente" VARCHAR2(15) NOT NULL,
    "Envolvidos_Acidente" VARCHAR2(80),
    "Hora_Acidente" NUMBER(2)  NOT NULL,
    "M�s_Acidente" VARCHAR2(20) NOT NULL,
    "Hora_Espec�fica_Acidente"  VARCHAR2(8) NOT NULL,
    "Tipo_acidente" VARCHAR2(100) NOT NULL,
    "Ano_Acidente" NUMBER(4) NOT NULL,
    "Zona_Acidente" VARCHAR2(25) NOT NULL,
    "Bateu_Fugiu" VARCHAR2(10),
    "Rua" VARCHAR2(80) ,
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