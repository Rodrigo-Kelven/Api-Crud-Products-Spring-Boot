
# Api Spring Boot

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JUnit](https://img.shields.io/badge/Junit5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)
![Grafana](https://img.shields.io/badge/grafana-%23F46800.svg?style=for-the-badge&logo=grafana&logoColor=white)
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=Prometheus&logoColor=white)



### Versão 0.1.7
#### Esta api receberá atualizações e será mais documentada e em breve, ela fará parte de um conjunto de micro serviços.


### Tecnologias Utilizadas

- Java: Linguagem utilizada na construção da API.
- Mavem: Para o gerenciamento de dependências
- JUnit: Para Tests
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
- SonarQube: Para verificação e qualidade de código.
- RabbitMQ: Será implementado para melhor performance em partes do sistema.
- Spring Actuador: Monitoramento de métricas da aplicação ou tráfego/estado de banco de dados.
- Prometheus: Para captura de logs
- Grafana: Para geração e visualização de dashboard de dados da aplicação.

### Estrutura do Projeto.
            ├── src
            │   ├── main
            │   │   ├── java
            │   │   │   └── br
            │   │   │       └── com
            │   │   │           └── dicume
            │   │   │               └── springboot
            │   │   │                   ├── config
            │   │   │                   │   ├── OpenApiConfig.java
            │   │   │                   │   └── WebConfig.java
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
- #### Para rodar os tests de qualidade de código e ser visualizado no SonarQube:
       ./script_qualitity_code.sh


### Para acessar o swagger da API
        http://127.0.0.1:8080/api/v1/swagger-ui/index.html

### Para acessar o PgAdmin (Interface do Banco de Dados) PostGreSQL
        http://127.0.0.1:8082/login?next=/
    
    Dados: {
        email: admin@admin.com
        senha: admin
        server -> register -> geral
            nome: db
            maintance database: dicumeproductsdb
            username: postgres
            senha: postgres
    }

### Para acessar a interface do SonarQube
        http://127.0.0.1:9000/

    Dados: {
        Login: admin
        Senha: admin

        "Precisa mudar a senha! E gerar um token para exportar!"
        My Account -> Security -> Generate Tokens

        "No terminal": export SONAR_TOKEN=token_gerado
    }

### Para acessar a interface do RabbitMQ
        http://127.0.0.1:15672/

    Dados: {
        Login: guest
        Senha: guest
    }

### Para acessar a interface do Prometheus
        http://127.0.0.1:9090/



### Para acessar a interface do Grafana
        http://127.0.0.1:3000/

    Dados: {
        Login: admin
        Senha: admin
    }

# Como obter os dashboards no Grafana?
## Segue o passo a passo:
#### Clique no botão (New) direito superior e clique em (Import) como exibido na imagem abaixo.
![Imagem 01](imgs/01.png)

#### Insira o ID do template para realizar o import do mesmo.
![Imagem 02](imgs/02.png)

#### Selecione o Prometheus para a configuração de captura de dados do dashboard.
![Imagem 03](imgs/03.png)

#### Veja, seu dashboard ja está pronto.
![Imagem 04](imgs/04.png)

### Estes são os dashboards que utilizei para monitoramenot de Processamento, JVM, Requests da aplicação e de endpoints.
- #### JVM Micrometer: 12271
- #### Spring Boot Statistics & Endpoint Metrics: 14430
- #### SpringBoot APM Dashboard: 12900

### Para inserir novos dashboards, segue o mesmo processo!

### Contribuições

Se você deseja contribuir para este projeto, fique à vontade para criar pull requests ou relatar issues. Melhorias como persistência de dados, maior segurança, e otimizações de desempenho são sempre bem-vindas.

## Autores
- [@Rodrigo_Kelven](https://github.com/Rodrigo-Kelven)

