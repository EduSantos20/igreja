
# Igreja (Spring Boot)

Projeto simples em Spring Boot que implementa uma API REST para gerenciar entidades de uma igreja (membros, finanças, ocasiões, orações, estudos e a própria igreja).

## Descrição

Este repositório contém uma aplicação Java baseada em Spring Boot organizada em pacotes clássicos: `entities`, `repository`, `service` e `resources` (camada de controle/REST). A configuração principal está em `src/main/resources/application.yaml`.

## Requisitos

- Java 11+ (ou versão compatível com o `pom.xml`)
- Maven (pode usar o wrapper incluído `mvnw` / `mvnw.cmd`)

## Como rodar

No Windows (usando o wrapper incluído):

```
mvnw.cmd spring-boot:run
```

Ou empacotar e executar o jar:

```
mvnw.cmd clean package
java -jar target/*.jar
```

Em Linux/macOS:

```
./mvnw spring-boot:run
```

Para rodar os testes:

```
mvnw.cmd test
```

## Estrutura do projeto (resumo)

- `src/main/java/com/edusantos/igreja/entities` — classes de entidade (ex.: `Church`, `Member`, `Financial`, `Occasion`, `Pray`, `Study`).
- `src/main/java/com/edusantos/igreja/repository` — interfaces Spring Data JPA responsáveis pela persistência.
- `src/main/java/com/edusantos/igreja/service` — camada de negócio com serviços para cada entidade.
- `src/main/java/com/edusantos/igreja/resources` — controladores/recursos REST que expõem os endpoints da API.
- `src/main/resources/application.yaml` — arquivo de configuração da aplicação (datasource, porta, profiles, etc.).

## Endpoints

Os recursos REST estão implementados nas classes em `com.edusantos.igreja.resources`. Elas fornecem operações CRUD para as entidades principais (Church, Member, Financial, Occasion, Pray, Study). Para detalhes de rotas e payloads, consulte os controladores correspondentes.

## Tratamento de erros

Existe um manipulador de exceções na pasta `resources/exceptions` (`ResouceExceptionHandler` e `StandardError`) para padronizar respostas de erro.

## Observações de desenvolvimento

- Use o wrapper Maven (`mvnw` / `mvnw.cmd`) para garantir a mesma versão do Maven no time.
- Verifique `application.yaml` para configurar a conexão com o banco (H2, Postgres, MySQL, conforme estiver configurado).
- Estruture novos endpoints em `resources` e mova lógica complexa para `service`.

## Testes

Os testes unitários/integrados ficam em `src/test/java/com/edusantos/igreja`. Execute com `mvnw test`.

## Contribuição

- Abra uma issue descrevendo a alteração desejada.
- Crie branches temáticos (`feature/`, `fix/`) e envie pull requests para `main`.

## Licença

(ex.: MIT) 
