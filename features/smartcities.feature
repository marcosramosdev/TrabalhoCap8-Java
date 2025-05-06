Feature: Gerenciamento de Cidades Inteligentes
  Como usuário do sistema
  Quero gerenciar cidades inteligentes
  Para garantir que as funcionalidades principais estão funcionando corretamente

  Scenario: Criar uma nova cidade com sucesso
    Given que o sistema está funcionando
    When eu envio uma requisição para criar uma cidade com os dados válidos
    Then o sistema deve retornar o status code 201
    And a cidade deve ser criada com os dados corretos

  Scenario: Tentar criar uma cidade com dados inválidos
    Given que o sistema está funcionando
    When eu envio uma requisição para criar uma cidade com dados inválidos
    Then o sistema deve retornar o status code 400
    And deve exibir uma mensagem de erro apropriada

  Scenario: Listar todas as cidades cadastradas
    Given que o sistema está funcionando
    When eu envio uma requisição para listar todas as cidades
    Then o sistema deve retornar o status code 200
    And deve exibir a lista de cidades cadastradas
