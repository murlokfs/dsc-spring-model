# Sistema de Biblioteca

Projeto Spring Boot com o modelo JPA e os repositórios do sistema de biblioteca proposto na atividade.

## Requisitos

- Java 8 ou superior
- Maven 3.6+

## Execução

```bash
mvn spring-boot:run
```

A aplicação usa H2 em memória e cria as tabelas automaticamente. Para executar os testes:

```bash
mvn test
```

## Consultas implementadas

- Livros disponíveis, ordenados por título
- Livros por categoria
- Usuários por parte do nome
- Empréstimos ativos de um usuário, com os livros
- Livros de um autor, ordenados por ano de publicação
- Empréstimos atrasados, com usuário e livros
- Quantidade de livros por categoria
