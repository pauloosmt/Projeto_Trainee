# 📚 Projeto Trainee - API REST de Biblioteca  

Este projeto é uma **API REST** desenvolvida em **Spring Boot** para gerenciar uma **biblioteca**, permitindo:  
- Cadastro de **pessoas**  
- Cadastro de **livros**  
- Sistema de **empréstimos** de livros  

A aplicação segue arquitetura REST e utiliza **Spring Data JPA** para persistência no banco de dados relacional (SQL).  

---

## 📂 Estrutura do Projeto  

- `controller/` → Endpoints da API  
- `service/` → Regras de negócio  
- `repository/` → Acesso ao banco de dados (JPA)  
- `data/dto/` → DTOs de requisição e resposta  
- `entity/` → Entidades persistidas no banco  

---

## ⚙️ Pré-requisitos  

- **Java 17+**  
- **Maven**  
- Banco de dados relacional (PostgreSQL recomendado)  
- IDE de sua preferência (IntelliJ, VSCode, Eclipse...)  

---

## 📌 Configuração do Banco de Dados  

O arquivo `application.properties` **não está no repositório** (por conter credenciais sensíveis).  
Você precisa criar esse arquivo em: src/main/resources/application.properties

---
Exemplo de configuração para PostgreSQL:  

```properties
spring.application.name=apiProjeto

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/sua_base
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

## JWT
api.security.token.secret=seu-segredo-jwt

## Swagger
server.port=8080
springdoc.swagger-ui.path=/swagger-ui.html
```
# 📖 Endpoints da API  

### 👤 Pessoas  

| Método | Endpoint         | Descrição                | Corpo da Requisição (JSON) |
|--------|------------------|--------------------------|-----------------------------|
| POST   | `/pessoas`       | Cadastra uma pessoa      | ```json { "nome": "Maria Silva", "cpf": "12345678901", "email": "maria@email.com", "senha": "123456", "cep": "01001000" } ``` |
| GET    | `/pessoas`       | Lista todas as pessoas   | — |
| GET    | `/pessoas/{id}`  | Busca uma pessoa por ID  | — |
| PUT    | `/pessoas/{id}`  | Atualiza uma pessoa      | ```json { "nome": "Novo Nome", "email": "novo@email.com" } ``` |
| DELETE | `/pessoas/{id}`  | Remove uma pessoa        | — |

---

### 📚 Livros  

| Método | Endpoint        | Descrição               | Corpo da Requisição (JSON) |
|--------|-----------------|-------------------------|-----------------------------|
| POST   | `/livros`       | Cadastra um livro       | ```json { "titulo": "O Senhor dos Anéis", "autor": "J.R.R. Tolkien", "disponivel": true } ``` |
| GET    | `/livros`       | Lista todos os livros   | — |
| GET    | `/livros/{id}`  | Busca um livro por ID   | — |
| PUT    | `/livros/{id}`  | Atualiza um livro       | ```json { "titulo": "Novo Título", "disponivel": false } ``` |
| DELETE | `/livros/{id}`  | Remove um livro         | — |

---

### 📦 Empréstimos  

| Método | Endpoint           | Descrição                    | Corpo da Requisição (JSON) |
|--------|--------------------|------------------------------|-----------------------------|
| POST   | `/emprestimos`     | Realiza um empréstimo        | ```json { "idPessoa": 1, "idLivro": 2 } ``` |
| GET    | `/emprestimos`     | Lista todos os empréstimos   | — |
| GET    | `/emprestimos/{id}`| Busca um empréstimo por ID   | — |
| PUT    | `/emprestimos/{id}/devolucao` | Devolve um livro | — |

---

### 🔑 Autenticação  

| Método | Endpoint         | Descrição                     | Corpo da Requisição (JSON) |
|--------|------------------|-------------------------------|-----------------------------|
| POST   | `/auth/register` | Registra um novo usuário      | ```json { "nome": "João", "cpf": "98765432100", "email": "joao@email.com", "senha": "123456", "cep": "22041001" } ``` |
| POST   | `/auth/login`    | Faz login e retorna um token  | ```json { "email": "joao@email.com", "senha": "123456" } ``` |

Resposta do login:  
```json
{
  "token": "seu_token_jwt_aqui"
}
```
## 🚀 Tecnologias Utilizadas  

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security (JWT)  
- PostgreSQL  
- OpenFeign (para consumo de API de CEP)  
- Swagger (documentação interativa) 
