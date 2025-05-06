Feature: Validação dos contratos da API de Cidades
  Como desenvolvedor
  Quero garantir que os contratos da API estejam corretos
  Para assegurar a integração adequada com outros sistemas

  Scenario: Buscar uma cidade por ID
    Given que eu tenho acesso à API de cidades
    When eu faço uma requisição GET para obter uma cidade por ID "1"
    Then o sistema deve retornar o status code 200
    And o contrato da resposta deve estar de acordo com o schema "cidade-schema.json"

  Scenario: Atualizar uma cidade existente
    Given que eu tenho acesso à API de cidades
    When eu faço uma requisição PUT para atualizar uma cidade com ID "1"
    Then o sistema deve retornar o status code 200
    And o corpo da resposta deve conter o campo "nome"

  Scenario: Excluir uma cidade existente
    Given que eu tenho acesso à API de cidades
    When eu faço uma requisição DELETE para remover uma cidade com ID "1"
    Then o sistema deve retornar o status code 204
