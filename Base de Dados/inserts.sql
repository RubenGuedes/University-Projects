insert into funcionarios values('124578965','654123654','Filipa Conceição',750.60,'Quinta da Malata Lto.2');
insert into funcionarios values('794658312','123457896','André Filipe',750.60,'Rua 25 de Abril Lto.3');


insert into caixa values('F01','124578965');

insert into reposicao values('F02','794658312');

insert into fornecedor values('Fornecedor01','MiraFlor');
insert into fornecedor values('Fornecedor02','Floralia');
insert into fornecedor values('Fornecedor03','Flores Armazenistas');
insert into fornecedor values('Fornecedor04','Boom Flores');

insert into stock values('A01','Rosa',60,'Fornecedor01');
insert into stock values('A02','Papoila',70,'Fornecedor02');
insert into stock values('A03','Cravo',50,'Fornecedor03');
insert into stock values('A04','Edelvaisse',90,'Fornecedor04');

insert into cliente values('C01','145786423');
insert into cliente values('C02','145754373');
insert into cliente values('C03','784696423');
insert into cliente values('C04','145785436');
insert into cliente values('C05','463871254');
insert into cliente values('C06','986523417');

insert into compra values('F01','A04','Compra01','C06','Encomenda');
insert into compra values('F01','A03','Compra02','C02','Encomenda');
insert into compra values('F01','A02','Compra03','C03','Direto');
insert into compra values('F01','A01','Compra04','C01','Direto');
insert into compra values('F01','A01','Compra05','C05','Encomenda');
insert into compra values('F01','A04','Compra06','C06','Direto');
insert into compra values('F01','A03','Compra07','C04','Encomenda');
insert into compra values('F01','A04','Compra08','C03','Direto');

insert into repoe values('F02','A01');
insert into repoe values('F02','A02');
insert into repoe values('F02','A03');
insert into repoe values('F02','A04');

insert into faz values('F01','Compra01');
insert into faz values('F01','Compra02');
insert into faz values('F01','Compra03');
insert into faz values('F01','Compra04');
insert into faz values('F01','Compra05');
insert into faz values('F01','Compra06');
insert into faz values('F01','Compra07');
insert into faz values('F01','Compra08');

insert into utiliza values('Compra01','A04');
insert into utiliza values('Compra02','A03');
insert into utiliza values('Compra03','A02');
insert into utiliza values('Compra04','A01');
insert into utiliza values('Compra05','A01');
insert into utiliza values('Compra06','A04');
insert into utiliza values('Compra07','A03');
insert into utiliza values('Compra08','A04');

insert into pedido values('Compra01','C06');
insert into pedido values('Compra02','C02');
insert into pedido values('Compra03','C03');
insert into pedido values('Compra04','C01');
insert into pedido values('Compra05','C05');
insert into pedido values('Compra06','C06');
insert into pedido values('Compra07','C04');
insert into pedido values('Compra08','C03');

insert into falta values('E01','Fornecedor01','A01');
insert into falta values('E02','Fornecedor02','A02');
insert into falta values('E03','Fornecedor03','A03');
insert into falta values('E04','Fornecedor04','A04');
insert into falta values('E05','Fornecedor02','A02');
insert into falta values('E06','Fornecedor01','A01');














