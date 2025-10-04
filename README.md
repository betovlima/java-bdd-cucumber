# BDD com Spring Boot 3 (Java 21) + Cucumber + YAML

Projeto mínimo para rodar **Cucumber (BDD)** integrado ao **Spring Boot** usando **`application.yml`** e configs de execução do Cucumber via **JUnit Platform**.

## Requisitos

- Java 21
- Gradle 8+ instalado (ou use o wrapper do seu ambiente)

## Rodando a aplicação (endpoint de saúde)
```bash
./gradlew bootRun
# ou: gradle bootRun
# Acesse: http://localhost:8080/health
```

## Rodando os testes BDD
```bash
./gradlew test
# Relatório: build/reports/tests/test/index.html
```

### Filtrando por tags
```bash
./gradlew test -Dcucumber.filter.tags="@fast"
```

## Estrutura principal
```
src/main/java/...        # app Spring
src/test/resources/...   # features cucumber + junit-platform.properties
src/test/java/...        # steps e config de integração Cucumber+Spring
```

## Observações
- As configurações da aplicação ficam em `src/main/resources/application.yml` (inclui um profile `test`).
- As configurações do Cucumber ficam em `src/test/resources/junit-platform.properties` (ou via `-D` no Gradle).
```

