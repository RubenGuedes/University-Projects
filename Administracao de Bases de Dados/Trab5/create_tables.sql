DROP TABLE "Acidente" CASCADE CONSTRAINTS;
DROP TABLE "Ciclista" CASCADE CONSTRAINTS;

CREATE TABLE "Ciclista"(
    "Cart�o cidad�o do ciclista" VARCHAR2(100)  NOT NULL,
    "Nome do ciclista"           VARCHAR2(100)  NOT NULL,
    "Faixa et�ria do ciclista"   VARCHAR2(15)   NOT NULL,
    "Idade do ciclista"          NUMBER(3)      NOT NULL,
    "Ciclista alcoolizado"       VARCHAR2(26),
    "Usava prote��o"             CHAR(4),
    "Ra�a do ciclista"           VARCHAR2(25),
    "G�nero do ciclista"         VARCHAR2(15),
    CONSTRAINT ciclista_pk PRIMARY KEY ("Cart�o cidad�o do ciclista")
) tablespace fase5;

CREATE TABLE "Acidente"
(
    "Object id"                 NUMBER(5)       NOT NULL,
    "Local_Acidente"            VARCHAR2(29)    NOT NULL,
    "Longitude"                 NUMBER          NOT NULL,
    "Latitude"                  NUMBER          NOT NULL,
    "Ambul�ncia"                VARCHAR2(26),
    "Cart�o_Cidad�o_Ciclista"   VARCHAR2(12)    NOT NULL,
    "Acidente_Devido_�lcool"    CHAR(4),
    "Dia_Semana_Acidente"       VARCHAR2(15)    NOT NULL,
    "Envolvidos_Acidente"       VARCHAR2(80),
    "Hora_Acidente"             NUMBER(2)       NOT NULL,
    "M�s_Acidente"              VARCHAR2(20)    NOT NULL,
    "Hora_Espec�fica_Acidente"  VARCHAR2(30)    NOT NULL,
    "Tipo_acidente"             VARCHAR2(100)   NOT NULL,
    "Ano_Acidente"              NUMBER(4)       NOT NULL,
    "Zona_Acidente"             VARCHAR2(25)    NOT NULL,
    "Bateu_Fugiu"               VARCHAR2(25),
    "Rua"                       VARCHAR2(80) ,
    "N�mero_Envolvidos"         NUMBER(2),
    CONSTRAINT pk_acidente PRIMARY KEY("Object id"),
    CONSTRAINT ciclistakey
        FOREIGN KEY ("Cart�o_Cidad�o_Ciclista")
        REFERENCES "Ciclista"("Cart�o cidad�o do ciclista"),
    CONSTRAINT ambulanciacheck
      CHECK ("Ambul�ncia" = 'Ambul�ncia Desnecess�ria' or "Ambul�ncia" = 'Ambul�ncia Necess�ria')
) tablespace fase5;