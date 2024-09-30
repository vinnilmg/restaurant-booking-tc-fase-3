# language: pt

Funcionalidade: Restaurante

  @smoke @high
  Cenario: Criar um Restaurante
    Dado Que desejo cadastrar um restaurante
    Quando o cadastro for submetido
    Entao deve criar o restaurante com sucesso
    E apresentar o restaurante

  @smoke @high
  Cenario: Buscar Todos os Restaurantes
    Dado que existam restaurantes
    Quando efetuar a busca de todos os restaurantes
    Entao deve apresentar todos os restaurantes

  Cenario: Buscar um Restaurante Específico
    Dado que existam restaurantes
    Quando efetuar a busca de restaurante específico
    Entao deve apresentar o resultado

  Cenario: Buscar um Restaurante pelo Nome
    Dado que existam restaurantes
    Quando efetuar a busca de um restaurante pelo nome dele
    Entao deve apresentar o resultado

  Cenario: Buscar um Restaurante pela Rua
    Dado que existam restaurantes
    Quando efetuar a busca de um restaurante pela rua dele
    Entao deve apresentar o resultado

  Cenario: Buscar um Restaurante pelo Bairro
    Dado que existam restaurantes
    Quando efetuar a busca de um restaurante pelo bairro dele
    Entao deve apresentar o resultado

  Cenario: Buscar um Restaurante pela Cidade
    Dado que existam restaurantes
    Quando efetuar a busca de um restaurante pela cidade dele
    Entao deve apresentar o resultado

  Cenario: Buscar um Restaurante pela Média de Feedback
    Dado que existam restaurantes
    Quando efetuar a busca de um restaurante pela média de feedback dele
    Entao deve apresentar o resultado

  Cenario: Buscar um Restaurante pelo Tipo de Culinária
    Dado que existam restaurantes
    Quando efetuar a busca de um restaurante pelo tipo de culinária dele
    Entao deve apresentar o resultado


