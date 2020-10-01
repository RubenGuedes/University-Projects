drop table if exists autocarro cascade;

create table autocarro(
    matricula	text,
    MaxPass	int,
    ano	int,
    nome	text,
    modelo	text,
    Primary key (matricula)    
);

insert into autocarro values('12-34',10,2015,'Audi','A3');
