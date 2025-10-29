
# Api Spring Boot

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)



### Versão 0.1.6
#### Esta api receberá atualizações e será mais documentada e em breve, ela fará parte de um conjunto de micro serviços.


### Tecnologias Utilizadas

- Java: Linguagem utilizada na construção da API.
- Mavem: Para o gerenciamento de dependências
- Spring Boot: Framework utilizado para a construção da API.
- Spring Boot JPA: Injeção de dependencias
- Spring Boot validation: Para validações
- Spring Boot starter-web: Para start do server web
- Spring Boot SpringDoc Openapi Starter Webmvc-ui: Para utilização do Swagger da API
- Spring Boot devtools: Para reload automático
- Spring Boot Starter Test: Para realização de testes automatizados
- PostGreSQL: Banco de dados
- PgAdmin: Interface para o banco de dados PostGreSQL
- Docker: Conteirização do banco de dados e de seua interface

### Estrutura do Projeto.
            ├── src
            │   ├── main
            │   │   ├── java
            │   │   │   └── br
            │   │   │       └── com
            │   │   │           └── dicume
            │   │   │               └── springboot
            │   │   │                   ├── config
            │   │   │                   │   └── OpenApiConfig.java
            │   │   │                   ├── controllers
            │   │   │                   │   └── ProductController.java
            │   │   │                   ├── dtos
            │   │   │                   │   └── ProductRecordDto.java
            │   │   │                   ├── exception
            │   │   │                   │   ├── GlobalExceptionHandler.java
            │   │   │                   │   └── ResourceNotFoundException.java
            │   │   │                   ├── models
            │   │   │                   │   └── ProductModel.java
            │   │   │                   ├── repositories
            │   │   │                   │   └── ProductRepository.java
            │   │   │                   ├── services
            │   │   │                   │   └── ProductService.java
            │   │   │                   └── SpringbootApplication.java
                
        
### Como rodar:
- #### Subir o banco e sua interfaçe em container:
        docker compose up
- #### Rodar API (caso não use IDE)
        mvn spring-boot:run


### Para acessar o swagger da API
        http://127.0.0.1:8080/api/v1/swagger-ui/index.html


### Para acessar o PgAdmin (Interface do Banco de Dados) PostGreSQL
        http://127.0.0.1:8081/login?next=/


### Contribuições

Se você deseja contribuir para este projeto, fique à vontade para criar pull requests ou relatar issues. Melhorias como persistência de dados, maior segurança, e otimizações de desempenho são sempre bem-vindas.

## Autores
- [@Rodrigo_Kelven](https://github.com/Rodrigo-Kelven)

