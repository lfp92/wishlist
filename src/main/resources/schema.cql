CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };

CREATE TABLE IF NOT EXISTS store.produtos_cliente(
	id_cliente uuid,
	id_produto uuid,
	nome_produto text,
	PRIMARY KEY(id_cliente, id_produto));

INSERT INTO store.produtos_cliente (id_cliente, id_produto, nome_produto)
VALUES (8bf4b22f-1d1d-4398-a529-94e012f956b9, c7610f14-4180-4576-8491-122cef723d24, 'Escova de dentes');