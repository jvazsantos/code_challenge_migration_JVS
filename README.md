
# DummyJSON Client - Java 17 e Spring Boot 3.2.5

## Descrição do Projeto

Este projeto é um microsserviço Java que interage com a API pública [DummyJSON](https://dummyjson.com/docs/products) para realizar operações de busca de produtos. 
Trata-se da modernização de um serviço que originalmente foi desenvolvido usando Java 8 e Spring Boot 2.6.x.

## Alterações realizadas

1. Pom.xml ajustado  para usar Java 17 e Spring Boot 3.2.5.
2. A  RestTemplate foi substituída pela WebClient para executar chamadas HTTP à API externa 
3. Os testes unitários feitos com `JUnit 4` e `Mockito` foram substituídos por testes de integração utilizando `@SpringBootTest`. Foi utilizada uma outra biblioteca para executar as asserções, teve-se o cuidado de garantir que as condições/validações continuam as mesmas.
4. Foi feito um pequeno ajuste para que o serviço getAllProducts funcionasse (foi criado um novo dto com alguns campos que estavam faltando); 
5. Todos os testes são executados com sucesso.
6. Foram definidos no pom.xml dois profiles, dev e prod, onde se pode parametrizar o  URL da API dummyjson  por ambiente. Esta parametrização é feita na propriedade ApiUrl em cada um dos profiles.
   Optou-se por configurar para um ambiente de prod um url não funcional. Quando se inicia o serviço com o perfil Prod é possível testar (pela negativa) a funcionalidade apresentada no ponto seguinte.
8. Entre a adição e remoção de várias dependencias, acrescentou-se a dependencia **spring-boot-starter-actuator** e parametrizou-se no projeto um novo path `actuator/health` que retorna a saúde do microsserviço, dando detalhe do status do serviço que é consumido
9. Foi acrescentado o arquivo Dockerfile que permite a geração de uma imagem/container com o serviço



## Estrutura do Projeto

```bash
dummyjson-client
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.dummyjsonclient
│   │   │       ├── DummyJsonClientApplication.java
│   │   │       ├── config
│   │   │       │   └── RestTemplateConfig.java
│   │   │       ├── controller
│   │   │       │   └── ProductController.java
│   │   │       ├── dto
│   │   │       │   └── Product.java
│   │   │       │   └── ProductResponse.java
|       |       └── monitoring
│       │       |    └── ProductApiHealthIndicator.java
│   │   │       ├── service
│   │   │       │   └── ProductService.java
│   │   └── resources
│   │       └── application.yaml
│   └── test
│       ├── java
│       │   └── com.example.dummyjsonclient
│       │       ├── config
│       │       │   └── RestTemplateConfigTest.java
│       │       └── controller
│       │       │   └── ProductControllerTest.java
│       │       ├── dto
│       │       │   └── ProductTest.java
│       │       │   └── ProductResponseTest.java
|       |       └── monitoring
│       │       |    └── ProductApiHealthIndicatorTest.java
│       │       └── service
│       │           └── ProductServiceTest.java
│       └── resources
└── pom.xml
```

## Passos para Executar o Projeto

### Pré-requisitos

- **Java 17**
- **Maven 3.8.x**
- **Docker ou podman para executar a versão containerizada**

### Executar a Aplicação



1. Execução do projeto:


   1.1 Modo standard
    ```bash
    mvn clean install -
    (Para executar com o profile dev)
    mvn spring-boot:run -Pdev

    
    ```bash
    mvn clean install
    (Para executar com o profile prod)
    mvn spring-boot:run -Pprod

    1.2 Modo containerizado
    ```bash
    docker build -t code_challenge_migration  (apenas na primeira execução ou se a base de código for alterada)

    docker run -p 8080:8080 code_challenge_migration

    ```

2. Acesse o serviço:

    O serviço estará disponível em `http://localhost:8080`.

### Executar Testes

Para executar os testes unitários:

```bash
mvn clean test
```

