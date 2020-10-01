INSERT INTO pessoa VALUES ('C1700','Ana','Rua da Horta das Figueiras, nº26');
INSERT INTO pessoa VALUES ('C1701','José','Rua da Gil Eanes nº1');
INSERT INTO pessoa VALUES ('C1702','Rita','Rua 25 de Abril nº14');
INSERT INTO pessoa VALUES ('C1703','Carlos','Rua do Raimundo nº37');
INSERT INTO pessoa VALUES ('C1704','Helena','Rua das Flores nº60');
INSERT INTO pessoa VALUES ('C1705','Pedro','Rua da Ribeira nº2');
INSERT INTO pessoa VALUES ('C1706','Sandra','Rua das Camélias nº15');
INSERT INTO pessoa VALUES ('C1707','Ricardo','Rua da Liberdade nº20');

INSERT INTO carro VALUES ('00-14-59','Toyota',2001);
INSERT INTO carro VALUES ('15-01-71','Mercedes',1997);
INSERT INTO carro VALUES ('20-43-37','Toyota',2000);
INSERT INTO carro VALUES ('32-CK-21','Nissan',2009);
INSERT INTO carro VALUES ('54-BN-23','Ford',2008);
INSERT INTO carro VALUES ('34-98-01','Peugeot',1999);
INSERT INTO carro VALUES ('07-AG-03','Volkswagen',2005);
INSERT INTO carro VALUES ('03-LB-17','Nissan',2017);

INSERT INTO acidente VALUES (0001,'24/12/2004','Portimão');
INSERT INTO acidente VALUES (0002,'12/01/2007','Lisboa');
INSERT INTO acidente VALUES (0003,'13/08/2014','Évora');
INSERT INTO acidente VALUES (0004,'28/08/2011','Porto');
INSERT INTO acidente VALUES (0005,'25/10/2017','Braga');
INSERT INTO acidente VALUES (0006,'28/02/2001','Beja');
INSERT INTO acidente VALUES (0007,'04/11/2012','Aveiro');
INSERT INTO acidente VALUES (0008,'18/06/2016','Moita');

INSERT INTO tem VALUES ('C1700','00-14-59');
INSERT INTO tem VALUES ('C1701','15-01-71');
INSERT INTO tem VALUES ('C1702','20-43-37');
INSERT INTO tem VALUES ('C1703','32-CK-21');
INSERT INTO tem VALUES ('C1704','54-BN-23');
INSERT INTO tem VALUES ('C1705','34-98-01');
INSERT INTO tem VALUES ('C1706','07-AG-03');
INSERT INTO tem VALUES ('C1707','03-LB-17');

INSERT INTO participação VALUES (0001,'00-14-59','C1700',100);
INSERT INTO participação VALUES (0002,'15-01-71','C1701',50);
INSERT INTO participação VALUES (0003,'20-43-37','C1702',500);
INSERT INTO participação VALUES (0004,'32-CK-21','C1703',200);
INSERT INTO participação VALUES (0005,'54-BN-23','C1704',700);
INSERT INTO participação VALUES (0006,'34-98-01','C1705',1000);
INSERT INTO participação VALUES (0007,'07-AG-03','C1706',200);
INSERT INTO participação VALUES (0008,'03-LB-17','C1707',100);