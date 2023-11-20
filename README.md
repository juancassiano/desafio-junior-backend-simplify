<h1 align="center">
  TODO List
</h1>

API para gerenciar tarefas (CRUD) que faz parte [desse desafio](https://github.com/simplify-liferay/desafio-junior-backend-simplify) para pessoas desenvolvedoras backend júnior, que se candidatam para a Simplify.


## Tecnologias
 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring-framework/reference/web/webmvc.html)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [SpringDoc OpenAPI 3](https://springdoc.org/v2/#spring-webflux-support)
- [Mysql](https://dev.mysql.com/downloads/)
- [TESTES](https://www.youtube.com/watch?v=N1UkkK4jIHM)
- [FLYWAY](https://flywaydb.org/) 
- [HATEOAS - HAL Specification](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)
- [AWS](https://aws.amazon.com)

## Práticas adotadas

- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro
- Geração automática do Swagger com a OpenAPI 3
- Testes Unitários e de Repositório
- Migration orquestradas pelo Flyway
- Deploy na AWS criando instância EC2 e conectando ao banco RDS
- HATEOAS (Hypermedia As The Engine Of Application State)

## Como Executar

- Clonar repositório git
- Construir o projeto:
```
$ ./mvnw clean package
```
- Executar a aplicação:
```
$ java -jar target/todolist-1.0.0.jar
```

## Ou acessar direto a instância da aplicação

A API poderá ser acessada em [ec2-18-218-135-163.us-east-2.compute.amazonaws.com:8080](http://localhost:8080).
O Swagger poderá ser visualizado em [ec2-18-218-135-163.us-east-2.compute.amazonaws.com:8080/swagger-ui.html](http://ec2-18-218-135-163.us-east-2.compute.amazonaws.com:8080/swagger-ui.html)

## API Endpoints

Para fazer as requisições HTTP abaixo, foi utilizada a ferramenta [POSTMAN](https://www.postman.com/):

- Criar Tarefa 
```
$ http POST :8080/todos nome="Todo 1" descricao="Desc Todo 1" prioridade=1

[
  {
    "descricao": "Desc Todo 1",
    "id": 1,
    "nome": "Todo 1",
    "prioridade": 1,
    "realizado": false
  }
]
```

- Listar Tarefas
```
$ http GET :8080/todos

[
  {
    "descricao": "Desc Todo 1",
    "id": 1,
    "nome": "Todo 1",
    "prioridade": 1,
    "realizado": false
  }
]
```

- Atualizar Tarefa
```
$ http PUT :8080/todos/1 nome="Todo 1 Up" descricao="Desc Todo 1 Up" prioridade=2

[
  {
    "descricao": "Desc Todo 1 Up",
    "id": 1,
    "nome": "Todo 1 Up",
    "prioridade": 2,
    "realizado": false
  }
]
```

- Remover Tarefa
```
http DELETE :8080/todos/1

[ ]
```