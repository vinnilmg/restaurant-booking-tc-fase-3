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

Para gerar o relatório do allure (precisa do allure): <br/> 
```sh
allure serve ${PATH}
```
