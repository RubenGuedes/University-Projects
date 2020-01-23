DROP TABLESPACE fase2 INCLUDING CONTENTS AND DATAFILES;

CREATE TABLESPACE fase2
    DATAFILE 'c:\oraclexe\tbs_fase2.dbf' 
    SIZE 300M 
    AUTOEXTEND ON;   

DROP TABLE "Condutor" CASCADE CONSTRAINTS;
DROP TABLE "Ciclista" CASCADE CONSTRAINTS;
DROP TABLE "Bicicleta" CASCADE CONSTRAINTS;
DROP TABLE "Condições_Local" CASCADE CONSTRAINTS;
DROP TABLE "Veículo" CASCADE CONSTRAINTS;
DROP TABLE "Acidente" CASCADE CONSTRAINTS;

CREATE TABLE "Bicicleta" 
(
    "Marca_Bicicleta" VARCHAR2( 20),
    "Modelo_Bicicleta" VARCHAR2( 20),
    "Ano_Aquisição" NUMBER(4),
    "Nome_Ciclista" VARCHAR2(29) NOT NULL,
    "Ident_Bicicleta" VARCHAR2(20),
    PRIMARY KEY ("Ident_Bicicleta")
);
CREATE TABLE "Ciclista"(
    "Cartão_Cidadão_Ciclista" VARCHAR2(12),
    "Nome" VARCHAR2(29) NOT NULL,
    "Faixa_Etária_Ciclista" VARCHAR2(15) NOT NULL,
    "Idade_Ciclista" NUMBER(3) NOT NULL,
    "Ciclista_Alcoolizado" VARCHAR2(26),
    "Usava_Proteção" CHAR(4),
    "Raça_Ciclista" VARCHAR2(25),
    "Género_Ciclista" VARCHAR2(15),
    "Número_Ident_Bicicleta" VARCHAR2(20) NOT NULL,
    PRIMARY KEY ("Nome","Cartão_Cidadão_Ciclista"),
    CONSTRAINT fk_id_bicicleta FOREIGN KEY ("Número_Ident_Bicicleta") REFERENCES "Bicicleta"("Ident_Bicicleta")
);
CREATE TABLE "Condutor"
(    
    "Carta_Condução" VARCHAR(12) NOT NULL,
    "Cartão_Cidadão_Condutor" VARCHAR2(12) NOT NULL,
    "Nome_Condutor" VARCHAR2(29) NOT NULL,
    "Idade_Condutor" Varchar2(15),
    "Faixa_Etária_Condutor" VARCHAR(12),
    "Raça_Condutor" VARCHAR2(25),
    "Género_Condutor" VARCHAR2(15),
    "Condutor_Alcoolizado" VARCHAR2(26),
    "Ferimentos_Condutor" VARCHAR2(25),
    "Veiculo_Condutor"  VARCHAR2(40) NOT NULL,
    "Excesso_Velocidade"  VARCHAR2(30) NOT NULL,
    "Usava_Cinto_Segurança" CHAR(4),
    CONSTRaint cp PRIMARY KEY ("Cartão_Cidadão_Condutor", "Nome_Condutor")
);
CREATE TABLE "Veículo"
(
    "Matrícula" VARCHAR2(8) NOT NULL,
    "Marca" VARCHAR2(15) NOT NULL,
    "Seguro" VARCHAR2(14) NOT NULL,
    "Ano_Aquisição" Number(4),
    "Modelo" VARCHAR2(10) NOT NULL,
    "Nome" VARCHAR2(29) NOT NULL,
    "Cartão_Cidadão" VARCHAR2(12) NOT NULL,
    CONSTRAINT cp_matricula PRIMARY KEY ("Matrícula"),
    CONSTRAINT fk_nome FOREIGN KEY ("Cartão_Cidadão","Nome") REFERENCES "Condutor"("Cartão_Cidadão_Condutor", "Nome_Condutor")
);

CREATE TABLE "Condições_Local"
(
    "Condições_Luminosidade" VARCHAR2(45) NOT NULL,
    "Localidade" VARCHAR2(50) NOT NULL,
    "Número_Vias" VARCHAR2(20),
    "Caracterisiticas_Via" VARCHAR2(30),
    "Classificação_Via" VARCHAR2(80),
    "Condição_Via" VARCHAR2(30) NOT NULL,
    "Configuração_Via"  VARCHAR2(50),
    "Rua_Defeitos" VARCHAR2(20) NOT NULL,
    "Mais_Detalhes_Via" VARCHAR2(45) NOT NULL,
    "Superfície_Via" VARCHAR2(20) NOT NULL,
    "Rural_Urbano" VARCHAR2(7) NOT NULL,
    "Velocidade_Limite" VARCHAR2(15) NOT NULL,
    "Controlo_Tráfego" VARCHAR2(80) NOT NULL,
    "Zona_Trabalho" VARCHAR2(25) NOT NULL,
    "Clima"  VARCHAR2(20) NOT NULL,
    "Rua" VARCHAR2(80),
    "Velo_Est_do_Condutor" VARCHAR2(15) NOT NULL,
    "Longitude" NUMBER NOT NULL,
    "Latitude" NUMBER NOT NULL,
    "Cidade" VARCHAR2(50) NOT NULL,
    "País" VARCHAR2(20) NOT NULL,
    PRIMARY KEY ("Condições_Luminosidade","Condição_Via","Longitude","Latitude")
);
CREATE TABLE "Acidente"
(
    "Local_Acidente" VARCHAR2(29) NOT NULL,
    "Longitude" NUMBER NOT NULL,
    "Latitude" NUMBER NOT NULL,
    "Ambulância" VARCHAR2(26),
    "Nome_Condutor" VARCHAR2(29) NOT NULL,
    "Cartão_Condutor" VARCHAR2(12) NOT NULL,
    "Nome_Ciclista" VARCHAR2(29) NOT NULL,
    "Cartão_Cidadão_Ciclista" VARCHAR2(12) NOT NULL,
    "Acidente_Devido_Álcool"  CHAR(4),
    "Dia_Semana_Acidente" VARCHAR2(15) NOT NULL,
    "Envolvidos_Acidente" VARCHAR2(80),
    "Hora_Acidente" NUMBER(2)  NOT NULL,
    "Mês_Acidente" VARCHAR2(20) NOT NULL,
    "Hora_Específica_Acidente"  VARCHAR2(8) NOT NULL,
    "Tipo_acidente" VARCHAR2(100) NOT NULL,
    "Ano_Acidente" NUMBER(4) NOT NULL,
    "Zona_Acidente" VARCHAR2(25) NOT NULL,
    "Bateu_Fugiu" VARCHAR2(10),
    "Rua" VARCHAR2(80) ,
    "Número_Envolvidos" NUMBER(2),
    CONSTRAINT pk_acidente PRIMARY KEY("Hora_Específica_Acidente", "Longitude", "Latitude", "Cartão_Condutor", "Cartão_Cidadão_Ciclista"),
    CONSTRAINT condutorkey 
        FOREIGN KEY ("Cartão_Condutor", "Nome_Condutor")
        REFERENCES "Condutor" ("Cartão_Cidadão_Condutor", "Nome_Condutor"),
    CONSTRAINT ciclistakey
        FOREIGN KEY ("Nome_Ciclista","Cartão_Cidadão_Ciclista")
        REFERENCES "Ciclista"("Nome","Cartão_Cidadão_Ciclista"),
    CONSTRAINT ambulanciacheck
      CHECK ("Ambulância" = 'Ambulância Desnecessária' or "Ambulância" = 'Ambulância Necessária')
);