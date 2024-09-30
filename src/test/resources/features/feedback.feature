# language: pt

Funcionalidade: Feedback

  @smoke @high
  Cenario: Criar um Feedback
    Dado Que desejo deixar um feedback para um restaurante
    Quando o feedback for submetido
    Entao deve criar o feedback com sucesso
    E apresentar o feedback


  Cenario: Buscar Feedbacks pelo Nome do Cliente
    Dado que existam feedbacks
    Quando efetuar a busca de feedbacks pelo nome do cliente
    Entao deve apresentar os feedbacks do cliente solicitado

  Cenario: Buscar Feedbacks pelo ID do Restaurante
    Dado que existam feedbacks
    Quando efetuar a busca de feedbacks pelo id do restaurante
    Entao deve apresentar os feedbacks do restaurante solicitado


  @smoke @high
  Cenario: Buscar Todos os Feedbacks
    Dado que existam feedbacks
    Quando efetuar a busca de todos os feedbacks
    Entao deve apresentar todos os feedbacks

  Cenário: Deletar um feedback existente
    Dado desejo deletar um feedback
    Quando o feedback existir
    Então deve deletar o feedback com sucesso