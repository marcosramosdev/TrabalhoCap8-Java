# Projeto SmartCities - Testes Automatizados BDD

Este projeto contém implementações de testes automatizados usando BDD (Behavior Driven Development) com Gherkin para validar as funcionalidades da API SmartCities.

## Cenários de Teste Implementados

### 1. Gerenciamento de Cidades

```gherkin
# Language: pt-br
Funcionalidade: Gerenciamento de Cidades
  Como um administrador do sistema
  Eu quero poder gerenciar as informações de cidades
  Para manter o cadastro de cidades atualizado

  Cenário: Cadastro de nova cidade com sucesso
    Dado que eu tenho uma nova cidade com nome "São Paulo" e população 12000000
    Quando eu enviar uma requisição POST para "/api/cidades"
    Então devo receber o status code 201
    E a resposta deve conter o nome da cidade "São Paulo"
    E a cidade deve ser salva no banco de dados

  Cenário: Buscar cidade por ID
    Dado que existe uma cidade cadastrada com ID 1
    Quando eu enviar uma requisição GET para "/api/cidades/1"
    Então devo receber o status code 200
    E a resposta deve conter o nome da cidade

  Cenário: Tentar cadastrar cidade sem nome
    Dado que eu tenho uma cidade sem nome e população 500000
    Quando eu enviar uma requisição POST para "/api/cidades"
    Então devo receber o status code 400
    E a resposta deve conter a mensagem de erro
```

### 2. Validação de Contratos das APIs

```gherkin
# Language: pt-br
Funcionalidade: Validação de contratos das APIs
  Como um desenvolvedor
  Eu quero garantir que os contratos das APIs estão mantidos
  Para assegurar compatibilidade entre sistemas

  Cenário: Validar contrato da API de cidades
    Quando eu enviar uma requisição GET para "/api/cidades"
    Então devo receber o status code 200
    E o JSON de resposta deve estar de acordo com o schema "cidade-schema.json"

  Cenário: Validar contrato da API de busca de cidade por ID
    Dado que existe uma cidade cadastrada com ID 1
    Quando eu enviar uma requisição GET para "/api/cidades/1"
    Então devo receber o status code 200
    E o JSON de resposta deve estar de acordo com o schema "cidade-schema.json"

  Cenário: Validar contrato da API de atualização de cidade
    Dado que existe uma cidade cadastrada com ID 1
    Quando eu enviar uma requisição PUT para "/api/cidades/1" com dados atualizados
    Então devo receber o status code 200
    E o JSON de resposta deve estar de acordo com o schema "cidade-schema.json"
```

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal
- **Cucumber**: Framework para execução de testes BDD
- **RestAssured**: Biblioteca para testes de API REST
- **JSON Schema Validator**: Para validação de contratos JSON
- **JUnit**: Framework de testes unitários
- **GitHub Actions**: Para integração contínua e execução automatizada dos testes

## Como Executar os Testes

### Localmente

1. Certifique-se de que a aplicação está em execução
2. Execute o comando: `mvn clean test`

### Via CI/CD

Os testes são executados automaticamente em cada push para a branch main através do GitHub Actions.

## Evidências de Execução

### Relatórios Cucumber

Após a execução dos testes, relatórios detalhados são gerados na pasta `target/cucumber-reports`.

### Resultados dos Testes

Os testes validam:
- Status codes das respostas (200, 201, 400, 404...)
- Estrutura do JSON retornado (através de validação de schema)
- Valores específicos nos campos de resposta
- Persistência de dados no banco de dados

## Estrutura do Projeto

```
TrabalhoCap8-Java/
├── features/                         # Arquivos .feature com cenários Gherkin
│   ├── smartcities.feature
│   └── api-contracts.feature
├── src/
│   └── test/
│       ├── java/
│       │   ├── runner/               # Configurações para execução dos testes
│       │   │   └── CucumberTestRunner.java
│       │   └── steps/                # Implementação dos passos do Gherkin
│       │       ├── SmartCitiesSteps.java
│       │       └── ApiContractValidationSteps.java
│       └── resources/
│           └── schemas/              # Schemas JSON para validação de contratos
│               └── cidade-schema.json
├── .github/
│   └── workflows/
│       └── ci-tests.yml              # Pipeline de CI para execução de testes
└── pom.xml                           # Configurações de dependências
```
