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

* A aplicação foi estruturada em camadas, separando responsabilidades entre controllers, services e repositories.

* A operação de transferência utiliza lock pessimista (PESSIMISTIC_WRITE) para evitar inconsistências em cenários de concorrência.

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
* Testes unitários: validam a regra de negócio da classe, utilizando JUnit e Mockito.
* Testes de integração: executados com Spring Boot utilizando o profile "test" e um banco PostgreSQL dedicado (itau-postgres_test), isolando do banco principal da aplicação.

## Executando os Testes

### 1. Subir o banco de dados dedicado aos testes de integração:

```bash
docker compose -f docker-compose.test.yml up -d
```

### 2.a) Executar somente os testes:

```bash
mvn test
```

### 2.b) Gerar artefato e executar os testes:

```bash
mvn clean package
```



## Autor

Stefani Signore

## Observações

_Este projeto contou com apoio de ferramentas de IA generativa (ChatGPT e Claude.AI) para esclarecimento de dúvidas, revisão de código e discussões de decisões técnicas._

***_As validações e as decisões finais foram realizadas de forma consciente durante o desenvolvimento do projeto._***
