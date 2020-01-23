DROP TABLE "Acidente" CASCADE CONSTRAINTS;
DROP TABLE "Ciclista" CASCADE CONSTRAINTS;

CREATE TABLE "Ciclista"(
    "Cartão cidadão do ciclista" VARCHAR2(100)  NOT NULL,
    "Nome do ciclista"           VARCHAR2(100)  NOT NULL,
    "Faixa etária do ciclista"   VARCHAR2(15)   NOT NULL,
    "Idade do ciclista"          NUMBER(3)      NOT NULL,
    "Ciclista alcoolizado"       VARCHAR2(26),
    "Usava proteção"             CHAR(4),
    "Raça do ciclista"           VARCHAR2(25),
    "Género do ciclista"         VARCHAR2(15),
    CONSTRAINT ciclista_pk PRIMARY KEY ("Cartão cidadão do ciclista")
) tablespace fase5;

CREATE TABLE "Acidente"
(
    "Object id"                 NUMBER(5)       NOT NULL,
    "Local_Acidente"            VARCHAR2(29)    NOT NULL,
    "Longitude"                 NUMBER          NOT NULL,
    "Latitude"                  NUMBER          NOT NULL,
    "Ambulância"                VARCHAR2(26),
    "Cartão_Cidadão_Ciclista"   VARCHAR2(12)    NOT NULL,
    "Acidente_Devido_Álcool"    CHAR(4),
    "Dia_Semana_Acidente"       VARCHAR2(15)    NOT NULL,
    "Envolvidos_Acidente"       VARCHAR2(80),
    "Hora_Acidente"             NUMBER(2)       NOT NULL,
    "Mês_Acidente"              VARCHAR2(20)    NOT NULL,
    "Hora_Específica_Acidente"  VARCHAR2(30)    NOT NULL,
    "Tipo_acidente"             VARCHAR2(100)   NOT NULL,
    "Ano_Acidente"              NUMBER(4)       NOT NULL,
    "Zona_Acidente"             VARCHAR2(25)    NOT NULL,
    "Bateu_Fugiu"               VARCHAR2(25),
    "Rua"                       VARCHAR2(80) ,
    "Número_Envolvidos"         NUMBER(2),
    CONSTRAINT pk_acidente PRIMARY KEY("Object id"),
    CONSTRAINT ciclistakey
        FOREIGN KEY ("Cartão_Cidadão_Ciclista")
        REFERENCES "Ciclista"("Cartão cidadão do ciclista"),
    CONSTRAINT ambulanciacheck
      CHECK ("Ambulância" = 'Ambulância Desnecessária' or "Ambulância" = 'Ambulância Necessária')
) tablespace fase5;