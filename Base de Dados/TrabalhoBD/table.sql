DROP TABLE IF EXISTS pessoa CASCADE;
DROP TABLE IF EXISTS carro CASCADE;
DROP TABLE IF EXISTS acidente CASCADE;
DROP TABLE IF EXISTS tem CASCADE;
DROP TABLE IF EXISTS participação CASCADE;


create table pessoa ( 
    id_condutor	varchar(10),
    nome	char(10),
    endereço	varchar(60),
    PRIMARY KEY (id_condutor)
);

create table carro(
    niv	varchar(8),
    modelo	char(10),
    ano	integer,
    PRIMARY KEY (niv)
);

create table acidente(
    numero_relatorio	integer,
    data varchar(20),
    local	varchar(20),
    PRIMARY KEY (numero_relatorio)
);

create table tem(
    id_condutor	varchar(10),
    niv	varchar(8),
    FOREIGN KEY (id_condutor) REFERENCES pessoa ON DELETE RESTRICT
    
);

create table participação(
	numero_relatorio	integer,
	niv	varchar(8),
	id_condutor	varchar(10),
	valor_danos	integer,
    FOREIGN KEY (numero_relatorio) REFERENCES acidente ON DELETE RESTRICT
);