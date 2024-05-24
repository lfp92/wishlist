### Instruções para rodar a aplicação

Primeiro, realize o pull da imagem do docker:
```
docker pull cassandra:latest
docker run --name cassandra -p 9042:9042 -d cassandra:latest
```

O container leva alguns segundos para iniciar completamente, portanto é necessário esperar.  Após estar pronto, rode o seguinte comando:
 ```
docker exec -it cassandra cqlsh  
```
Se aparecer a mensagem a seguir, significa que o container ainda não está pronto:

```
Connection error: ('Unable to connect to any servers', {'127.0.0.1:9042': ConnectionRefusedError(111, "Tried connecting to [('127.0.0.1', 9042)]. Last error: Connection refused")})
```

Quando o Cassandra estiver pronto, o comando resultará nesse prompt:

```
Connected to Test Cluster at 127.0.0.1:9042
[cqlsh 6.1.0 | Cassandra 4.1.4 | CQL spec 3.4.6 | Native protocol v5]
Use HELP for help.
cqlsh>
```

Execute os comandos a seguir no terminal do cqlsh:

```cql  
CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };  
  
CREATE TABLE IF NOT EXISTS store.produtos_cliente(  
id_cliente uuid,  
id_produto uuid,  
nome_produto text,  
PRIMARY KEY(id_cliente, id_produto));  
  
INSERT INTO store.produtos_cliente (id_cliente, id_produto, nome_produto)  
VALUES (8bf4b22f-1d1d-4398-a529-94e012f956b9, c7610f14-4180-4576-8491-122cef723d24, 'Escova de dentes');
 ```

Agora, na raiz da aplicação, execute o comando:

```
mvn clean install
```

Em seguida, inicialize a aplicação "WishListApplication".


Chamada de exemplo:
```
GET http://localhost:8089/wishlist/v1/clientes/8bf4b22f-1d1d-4398-a529-94e012f956b9/produtos/c7610f14-4180-4576-8491-122cef723d24

Deve retornar esse registro:

{
	"id_cliente": "8bf4b22f-1d1d-4398-a529-94e012f956b9",
	"id_produto": "c7610f14-4180-4576-8491-122cef723d24",
	"nome_produto": "Escova de dentes"
}
```
