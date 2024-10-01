# restaurant-booking-tc-fase-3

Documentação do projeto:
- https://docs.google.com/document/d/1lUFmM9Qkay3HMyTQha3oZiq69MQRNsmjWClve4ZIGuM/edit?usp=sharing

Na pasta resources, localizada na raiz do projeto, contém a collection para realizar os testes em todos os endpoints via postman (LOCAL).
Foi realizado o deploy em cloud, segue: https://restaurant-booking-fiap.onrender.com
<p style="color:red">Obs: O deploy foi feito em nuvem grátis, portanto, a aplicação ficará inativa e quando o primeiro request for realizado, a mesma será ativada. Aguardar alguns minutos para que a aplicação fique ativa.</p>

### Passo a passo a partir do Makefile
<p style="color:red">Obs: Precisa do make na máquina.</p>

#### Geração de imagem
Compila, gera o executável (JAR) e gera a imagem:
```sh
make init
```

#### Etapa de testes
Roda os testes unitários e de integração:
```sh
make test
```

Inicializa a aplicação e executa os testes de sistema feitos com o cucumber:
```sh
make system-test
```

Inicializa a aplicação e executa os testes de performance:
```sh
make performance-test
```

<p>Qualquer ponto ou dúvida, abrir o arquivo Makefile localizado na raiz</p>

### Relatórios
<p>O relatório do Cucumber está sendo gerado no caminho abaixo: <br/>
target/cucumber-reports/cucumber.html</p>

Para gerar o relatório do allure: <br/> 
```sh
allure serve target/allure-results
```
<p style="color:red">Obs: Precisa do allure na máquina</p>

<p>Após a execução dos testes de performance, será exibido um log com o relatório gerado. 
O mesmo também fica localizado na pasta target/gatling.</p>

<p>Todas as evidências da geração de relatórios está na pasta resources na RAIZ.</p>
