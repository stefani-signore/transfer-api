# Itaú Transfer API

API REST desenvolvida em Java e Spring Boot para gerenciamento de clientes e transferências bancárias.

## Funcionalidades

### Clientes

* Cadastro de clientes
* Listagem de clientes
* Busca de cliente por número da conta

### Transferências

* Transferência entre contas
* Validação de saldo suficiente
* Limite máximo de transferência de R$ 10.000,00
* Histórico de transferências
* Registro de transferências com sucesso e falha

## Tecnologias Utilizadas

* Java 25
* Spring Boot 3
* PostgreSQL
* Maven
* Docker Compose
* Swagger / OpenAPI


## Arquitetura e Decisões Técnicas

A aplicação foi estruturada em camadas, separando responsabilidades entre controllers, services e repositories.

A operação de transferência utiliza lock pessimista (PESSIMISTIC_WRITE) para evitar inconsistências em cenários de concorrência.

## Executando o Projeto

### 1. Subir o banco de dados

```bash
docker compose up -d
```

### 2. Executar a aplicação

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

```text
http://localhost:8080
```

## Documentação Swagger

Após iniciar a aplicação:

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

## Endpoints

### Criar Cliente

POST

```text
/api/v1/clients
```

Payload:

```json
{
  "name": "Stefani",
  "accountNumber": "12345",
  "balance": 5000.00
}
```

### Buscar Cliente

GET

```text
/api/v1/clients/{accountNumber}
```

### Listar Clientes

GET

```text
/api/v1/clients
```

### Transferência

POST

```text
/api/v1/transfers
```

Payload:

```json
{
  "sourceAccount": "12345",
  "destinationAccount": "99999",
  "amount": 1000.00
}
```

### Histórico

GET

```text
/api/v1/transfers/{accountNumber}
```

## Testes

Executar todos os testes:

```bash
mvn test
```



## Autor

Stefani Signore
