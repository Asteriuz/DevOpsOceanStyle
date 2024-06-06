# Projeto OceanStyle

Este repositório contém a configuração necessária para construir e executar o projeto OceanStyle, uma aplicação Java, utilizando Docker e Docker Compose.

## Dockerfile

O Dockerfile descreve como a imagem Docker para a aplicação Java será construída.

### Estrutura do Dockerfile

1. **Definição da versão do JDK**
    ```Dockerfile
    ARG JDK_VERSION=17-jdk-slim
    ```
    Define uma variável de argumento `JDK_VERSION` com o valor `17-jdk-slim`.

2. **Imagem base**
    ```Dockerfile
    FROM openjdk:${JDK_VERSION}
    ```
    Usa a imagem do OpenJDK na versão especificada pela variável `JDK_VERSION` como imagem base.

3. **Configuração do usuário da aplicação**
    ```Dockerfile
    ENV APP_USER=app

    RUN groupadd -r ${APP_USER} && useradd -r -g ${APP_USER} -m -d /home/${APP_USER} -s /sbin/nologin ${APP_USER}
    ```
    Define uma variável de ambiente `APP_USER` e cria um grupo e um usuário sem acesso ao shell para a aplicação.

4. **Diretório de trabalho**
    ```Dockerfile
    WORKDIR /app
    ```
    Define `/app` como o diretório de trabalho dentro do container.

5. **Cópia dos arquivos da aplicação**
    ```Dockerfile
    COPY . /app
    ```
    Copia todos os arquivos do diretório atual (onde está o Dockerfile) para o diretório de trabalho `/app` no container.

6. **Permissões dos arquivos**
    ```Dockerfile
    RUN chown -R ${APP_USER}:${APP_USER} /app
    ```
    Altera o proprietário dos arquivos no diretório `/app` para o usuário `APP_USER`.

7. **Execução com usuário específico**
    ```Dockerfile
    USER ${APP_USER}
    ```
    Define que todos os comandos subsequentes serão executados como o usuário `APP_USER`.

8. **Construção da aplicação**
    ```Dockerfile
    RUN ./mvnw clean package
    ```
    Executa o comando Maven Wrapper para limpar e empacotar a aplicação.

9. **Exposição da porta**
    ```Dockerfile
    EXPOSE 8080
    ```
    Expõe a porta 8080 do container para comunicação externa.

10. **Comando de inicialização**
    ```Dockerfile
    CMD ["java", "-jar", "target/oceanstyle-0.0.1-SNAPSHOT.jar"]
    ```
    Define o comando que será executado quando o container iniciar, rodando a aplicação Java.

## Docker Compose

O arquivo `docker-compose.yml` descreve os serviços que compõem a aplicação e como eles interagem.


## Como usar

1. **Construir a imagem Docker**
    ```sh
    docker build -t oceanstyle .
    ```

2. **Executar a aplicação com Docker Compose**
    ```sh
    docker-compose up
    ```

3. **Acessar o Contêiner MySQL**
    ```sh
   docker-compose exec db mysql -u root -p
    ```
Isso abrirá um prompt interativo do MySQL. Digite a senha quando solicitado (a senha padrão é password).

4. **Criar um Banco de Dados**
    ```sh
   CREATE DATABASE meu_banco_de_dados;
    ```
5. **Selecionar o Banco de Dados Recém-Criado**
     ```sh
   USE meu_banco_de_dados;
    ```
6. **Criar uma Tabela:**
     ```sh
CREATE TABLE minha_tabela (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    idade INT
);
    ```
7. **Inserir dados na Tabela**
     ```sh
INSERT INTO minha_tabela (nome, idade) VALUE('Alice', 30), ('Bob', 35), ('Carol', 25);
    ```
8. **Verificar os Dados**
     ```sh
SELECT * FROM minha_tabela;
    ```
9. **Sair do Cliente MySQL**
     ```sh
exit;
    ```
Isso irá construir a imagem, criar e iniciar os containers definidos no `docker-compose.yml`.Podendo também testar a persistência de dados.

## Notas

- Certifique-se de que o Maven Wrapper (`./mvnw`) está configurado corretamente no projeto.
- As variáveis de ambiente devem ser ajustadas conforme necessário para o seu ambiente de banco de dados.
- O volume `app_data` deve apontar para o caminho correto onde os dados da aplicação serão armazenados.
"# DevOps-GS" 
