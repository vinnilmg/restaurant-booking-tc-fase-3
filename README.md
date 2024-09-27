# restaurant-booking-tc-fase-3

## Testes
- Execução dos testes unitários:
```sh
mvn test
```

- Execução dos testes de integracão:
```sh
mvn test -Pintegration-test
```

- Execução dos testes de sistema:
```sh
mvn test -Psystem-test
```

<p>O relatório do Cucumber está sendo gerado no caminho abaixo: <br/>
target/cucumber-reports/cucumber.html</p>

Para gerar o relatório do allure: <br/> 
```sh
allure serve target/allure-results
```
<b>Obs: Precisa do allure na máquina</b>

Para realizar a execução de todas as etapas de teste (unitário, integração e sistema), executar comandos na raiz:
- Testes unitários e integração:
```sh
make test
```
- Testes de sistema (comportamento):
```sh
system-test
```

<b>Obs: Precisa do make na máquina</b> 
