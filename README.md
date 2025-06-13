GetInfo Backend

API RESTful para o projeto GetInfo, desenvolvida com Spring Boot, Java 17 e MySQL. A aplicação gerencia informações sobre usuários, profissionais e seus respectivos dados.

✨ Funcionalidades
Cadastro e autenticação de usuários.
Gerenciamento de perfis de profissionais e clientes.
Armazenamento e consulta de dados relevantes da aplicação.
API documentada com Swagger para fácil consumo.
🚀 Tecnologias Utilizadas
Java 17: Versão mais recente do Java com suporte de longo prazo (LTS).
Spring Boot 3: Framework para criação de aplicações Java de forma rápida e configurável.
Spring Security: Para implementação de autenticação e controle de acesso.
MySQL: Banco de dados relacional para persistência dos dados.
JPA (Hibernate): Para mapeamento objeto-relacional e geração automática do schema do banco de dados.
Maven: Gerenciador de dependências e build do projeto.
Docker: Para containerização da aplicação e do banco de dados, facilitando o setup do ambiente.
Swagger (Springdoc): Para documentação interativa da API.
📋 Pré-requisitos
Antes de começar, garanta que você tenha as seguintes ferramentas instaladas em seu ambiente:

Java 17 (JDK)
Maven 3.8+
Docker e Docker Compose (Opcional, para rodar com Docker)
MySQL Server (Opcional, para rodar localmente sem Docker)
⚙️ Como Executar o Projeto
Siga os passos abaixo para configurar e executar a aplicação localmente.

1. Clone o Repositório
Bash

git clone https://github.com/Luiz-Nascimento/getinfo-backend.git
cd getinfo-backend
2. Configuração do Banco de Dados
Você tem duas opções para o banco de dados: usar o Docker (recomendado para simplicidade) ou uma instância local do MySQL.

Opção A: Rodar com Docker (Recomendado)
O docker-compose.yml no repositório já está configurado com as credenciais corretas.

Inicie o container do MySQL:
Na raiz do projeto, execute o seguinte comando:

Bash

docker-compose up -d
Este comando iniciará um container com um banco de dados chamado contratosdb, acessível pelo usuário contratos_user e senha getinfo123.

Verifique a configuração:
O arquivo src/main/resources/application-dev.properties já está configurado para se conectar a este banco de dados.

Opção B: Rodar com um Banco MySQL Local
Se preferir não usar o Docker, você pode configurar uma instância do MySQL que já esteja rodando na sua máquina.

Acesse o seu cliente MySQL (MySQL Workbench, DBeaver, ou terminal) e execute os seguintes comandos para criar o banco de dados e o usuário:

SQL

-- Cria o banco de dados se ele não existir
CREATE DATABASE IF NOT EXISTS contratosdb;

-- Cria o usuário e define a senha
CREATE USER 'contratos_user'@'localhost' IDENTIFIED BY 'getinfo123';

-- Concede todos os privilégios no banco de dados para o novo usuário
GRANT ALL PRIVILEGES ON contratosdb.* TO 'contratos_user'@'localhost';

-- Aplica as alterações de privilégios
FLUSH PRIVILEGES;
3. Configuração da Aplicação
O projeto utiliza o perfil dev do Spring, que está configurado no arquivo src/main/resources/application-dev.properties. As propriedades de conexão são:

Properties

# src/main/resources/application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/contratosdb
spring.datasource.username=contratos_user
spring.datasource.password=getinfo123
spring.jpa.hibernate.ddl-auto=update
A propriedade spring.jpa.hibernate.ddl-auto=update garante que o Hibernate criará e atualizará automaticamente as tabelas no banco de dados com base nas suas entidades JPA.

4. Execute a Aplicação
Você pode executar a aplicação diretamente pela sua IDE (Eclipse, IntelliJ, etc.) ou usando o Maven no terminal.

Bash

mvn spring-boot:run
Após alguns instantes, o servidor estará rodando na porta 8080.

📝 Documentação da API (Swagger)
Com a aplicação em execução, a documentação completa e interativa da API estará disponível no Swagger UI. Acesse o seguinte endereço no seu navegador:

URL do Swagger: http://localhost:8080/swagger-ui.html
Lá você poderá visualizar todos os endpoints disponíveis, seus parâmetros, e até mesmo testá-los diretamente.
