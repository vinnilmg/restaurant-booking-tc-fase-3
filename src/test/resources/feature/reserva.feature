# language: pt

Funcionalidade: Reserva

  Cenario: Reservar uma Mesa
    Dado Que desejo reservar uma mesa em um restaurante
    Quando a mesa estiver disponível
    Entao deve realizar uma reserva na mesa do restaurante escolhido
    E apresentar o resultado

  Cenario: Buscar Todas as Reservas
    Dado que existam reservas
    Quando efetuar a busca de todas as reservas
    Entao deve apresentar todas as reservas

  Cenario: Buscar uma Reserva Específica
    Dado que existam reservas
    Quando efetuar a busca de uma reserva específica
    Entao deve apresentar o resultado

  Cenario: Buscar Reservas Solicitadas
    Dado que alguma reserva tenha sido solicitada
    Quando efetuar a busca das reservas solicitadas
    Entao deve apresentar todas as reservas que foram solicitadas

  Cenario: Cancelar uma Reserva
    Dado que desejo cancelar uma reserva
    Quando a reserva existir
    Entao deve cancelar a reserva com sucesso

  Cenario: Buscar Reservas Canceladas
    Dado que alguma reserva tenha sido cancelada
    Quando efetuar a busca das reservas canceladas
    Entao deve apresentar todas as reservas que foram canceladas

  Cenario: Confirmar uma Reserva
    Dado que desejo confirmar uma reserva
    Quando a reserva existir
    Entao deve confirmar a reserva com sucesso

  Cenario: Buscar Reservas Confirmadas
    Dado que alguma reserva tenha sido confirmada
    Quando efetuar a busca das reservas confirmadas
    Entao deve apresentar todas as reservas que foram confirmadas
