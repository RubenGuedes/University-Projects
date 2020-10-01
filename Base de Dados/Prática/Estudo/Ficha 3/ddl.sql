drop table if exists biblioteca cascade;

drop table if exists autor cascade;

drop table if exists livro cascade;

drop table if exists socio cascade;

drop table if exists exemplar cascade;

drop table if exists autoria cascade;

drop table if exists requisitou cascade;

drop table if exists entregou cascade;


create table biblioteca ( 
    IdBib char(3) primary key, 
    NomeB varchar(15), 
    Cidade varchar(10)
);

create table autor(
    CodA  char(3) primary key, 
    NomeA varchar(25),  
    NBiA integer check(NBiA > 0),
    PaisA  varchar(15),
    CidadeA varchar(15)
);

create table livro(
    ISBN char(9) primary key,
    Titulo varchar(55)
);

create table socio(
    NumS  char(3)  primary key,
    NomeS  varchar(25),  
    NBIS integer check(NBiS > 0),
    CidadeS varchar(10)
);


create table exemplar(
    IdLivro char(9) primary key,
    ISBN  char(9),
    IdBib char(3),
    foreign key (ISBN) references livro on  delete restrict,
    foreign key (IdBib) references biblioteca on  delete restrict
);

create table Autoria(
    ISBN char(9),
    CodA char(3),
    primary key (ISBN,CodA),
    foreign key (ISBN) references livro on  delete restrict,
    foreign key (CodA) references autor on  delete restrict
);



create table requisitou(
    NumS char(3),
    IdLivro char(9),
    Data integer,
    primary key (NumS,IdLivro, Data),
    foreign key (NumS) references socio on  delete restrict,
    foreign key (IdLivro) references exemplar on  delete restrict
);

create table entregou(
    NumS char(3),
    IdLivro char(9),
    Data integer,
    primary key (NumS,IdLivro, Data),
    foreign key (NumS) references socio on  delete restrict,
    foreign key (IdLivro) references exemplar on  delete restrict
);