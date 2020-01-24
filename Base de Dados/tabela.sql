drop table if exists funcionarios cascade;

drop table if exists caixa cascade;

drop table if exists reposicao cascade;

drop table if exists cliente cascade;

drop table if exists stock cascade;

drop table if exists fornecedor cascade;

drop table if exists compra cascade;

drop table if exists repoe cascade;

drop table if exists faz cascade;

drop table if exists utiliza cascade;

drop table if exists pedido cascade;

drop table if exists falta cascade;

create table funcionarios(
	nif varchar(55) primary key,
	bi varchar(55) check (bi != null),
	nome varchar(25) check (nome != null),
	salario integer check (salario >= 0),
	morada varchar(25) check (morada != null)
);

create table caixa(
	numF varchar(55) primary key,
	nif varchar(9),
    foreign key (nif) references funcionarios on  delete restrict
);

create table reposicao(
	numF varchar(55) primary key ,
	nif varchar(9),
	foreign key (nif) references funcionarios on  delete restrict
);

create table fornecedor(
	numFornecedor varchar(55) primary key,
	nomef varchar(55)
);

create table stock(
    numA varchar(55) primary key,
	nomeP varchar(55),
    quantidade integer check(quantidade >= 0),
    numFornecedor varchar(55),
	foreign key (numFornecedor) references fornecedor on  delete restrict
);

create table cliente(
	numC varchar(55) primary key, 
	nif varchar(9)
);

create table compra(
	numF varchar(55),
	numA varchar(55),
	numCompra varchar(55) primary key,
    numC varchar(55),
	tipoCompra varchar(9),
	foreign key (numF) references caixa on  delete restrict,
    foreign key (numA) references stock on  delete restrict,
	foreign key (numC) references cliente on  delete restrict
	
);

create table repoe(
	numF varchar(55),
	numA varchar(55),
    foreign key (numF) references reposicao on  delete restrict,
	foreign key (numA) references stock on  delete restrict
);

create table faz(
    numF varchar(55),
    numCompra varchar(55),
    foreign key (numF) references caixa on  delete restrict,
	foreign key (numCompra) references compra on  delete restrict
);

create table utiliza(
	numCompra varchar(55),
	numA varchar(55),
	foreign key (numCompra) references compra on  delete restrict,
	foreign key (numA) references stock on  delete restrict
);

create table pedido(
	numCompra varchar(55),
    numC varchar(55),
    foreign key (numCompra) references compra on  delete restrict,
	foreign key (numC) references cliente on  delete restrict
);
 
create table falta(
	numEncFor varchar(55),
	numFornecedor varchar(55),
    numA varchar(55),
    foreign key (numA) references stock on  delete restrict,
	foreign key (numFornecedor) references fornecedor on  delete restrict
);
	


	