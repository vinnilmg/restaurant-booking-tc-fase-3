build:
	@echo "Compilando..."
	mvn compile

package:
	@echo "Gerando JAR..."
	mvn package -Dmaven.test.skip

docker-build:
	@echo "Gerando imagem..."
	docker build . -t restaurant-booking-tc:latest

docker-start:
	@echo "Executando containers..."
	docker compose -f docker-compose.yml up -d

docker-stop:
	@echo "Finalizando containers..."
	docker compose -f docker-compose.yml down

unit-test:
	@echo "Executando testes unitarios..."
	mvn test

integration-test:
	@echo "Executando testes de integracao..."
	mvn test -P integration-test

# Compila, empacota (gera o jar) e gera a imagem atualizada
init: build package docker-build

# Roda os testes unitários e os de integração
test: unit-test integration-test

# Roda a aplicação, executa os testes de sistema e derruba os containers
system-test:
	make docker-start
	@echo "Executando testes de sistema (E2E)..."
	mvn test -P system-test
	make docker-stop

# Roda a aplicação, executa os testes de perfomance e derruba os containers
performance-test:
	make docker-start
	@echo "Executando testes de perfomance..."
	mvn gatling:test -P perfomance-test
	make docker-stop
