# crzBurguer - Sistema de Vendas

Sistema de gestão de vendas para lanchonete, desenvolvido em Java com persistência em PostgreSQL. Permite consultar o cardápio, montar um carrinho de compras e finalizar pedidos via terminal.

---

## Português (PT-BR)

### Visão geral

O sistema opera por um menu de linha de comando com quatro operações principais: listagem de produtos por categoria (lanches, refrigerantes e bebidas alcoólicas), inclusão de itens no carrinho, visualização do carrinho corrente e finalização da compra. A camada de acesso a dados segue o padrão DAO, isolando as instruções SQL da lógica de apresentação.

### Arquitetura

- **Main** - controla o fluxo do menu e a interação com o usuário.
- **ConexaoBD** - centraliza a criação de conexões JDBC, lendo parâmetros de um arquivo de propriedades externo em vez de credenciais fixas no código.
- **ProdutoDAO** (e DAOs correlatos) - encapsula as consultas ao banco de dados, retornando objetos de domínio ao restante da aplicação.
- **config.properties** - arquivo local, não versionado, com a string de conexão e as credenciais do banco.
- **config.example.properties** - modelo versionado que documenta as chaves esperadas, sem valores sensíveis.

### Pré-requisitos

- JDK 17 ou superior
- PostgreSQL em execução (local ou remoto)
- Driver JDBC do PostgreSQL disponível no classpath do projeto
- IDE com suporte a projetos Java (o projeto foi desenvolvido em IntelliJ IDEA)

### Configuração e execução

1. Clone o repositório.
2. Duplique `config.example.properties`, renomeie a cópia para `config.properties` e preencha com os dados reais de conexão:
   ```properties
   db.url=jdbc:postgresql://localhost:5432/crzburguer
   db.usuario=seu_usuario
   db.senha=sua_senha
   ```
3. Garanta que `config.properties` permaneça na raiz do classpath de execução (por padrão, dentro de `src`, para que seja copiado ao diretório de build).
4. Adicione o driver JDBC do PostgreSQL às bibliotecas do projeto (via gerenciador de dependências ou manualmente, em *Project Structure > Libraries*, no caso do IntelliJ).
5. Crie o esquema do banco de dados correspondente às entidades utilizadas pelos DAOs.
6. Compile e execute a classe `Main`.

### Considerações de segurança

O arquivo `config.properties` contém credenciais e está listado em `.gitignore`, não devendo ser commitado. Apenas `config.example.properties` deve permanecer sob controle de versão, servindo de referência para novas instalações.

### Estado do projeto

Projeto em desenvolvimento, com fins de estudo, cobrindo conexão a banco de dados relacional, padrão DAO e fluxo básico de vendas via terminal.

---

## English (US)

### Overview

The system runs through a command-line menu with four core operations: browsing the menu by category (sandwiches, soft drinks, and alcoholic beverages), adding items to a cart, viewing the current cart, and completing a purchase. The data access layer follows the DAO pattern, isolating SQL statements from presentation logic.

### Architecture

- **Main** - drives the menu flow and user interaction.
- **ConexaoBD** - centralizes JDBC connection creation, reading parameters from an external properties file instead of hardcoded credentials.
- **ProdutoDAO** (and related DAOs) - encapsulates database queries, returning domain objects to the rest of the application.
- **config.properties** - local, untracked file holding the connection string and database credentials.
- **config.example.properties** - versioned template documenting the expected keys, with no sensitive values.

### Prerequisites

- JDK 17 or later
- A running PostgreSQL instance (local or remote)
- PostgreSQL JDBC driver available on the project classpath
- An IDE with Java project support (developed in IntelliJ IDEA)

### Setup and execution

1. Clone the repository.
2. Copy `config.example.properties`, rename the copy to `config.properties`, and fill it in with real connection details:
   ```properties
   db.url=jdbc:postgresql://localhost:5432/crzburguer
   db.usuario=your_username
   db.senha=your_password
   ```
3. Make sure `config.properties` stays at the root of the runtime classpath (by default, inside `src`, so it gets copied to the build output).
4. Add the PostgreSQL JDBC driver to the project's libraries (through a dependency manager, or manually via *Project Structure > Libraries* in IntelliJ).
5. Create the database schema matching the entities used by the DAOs.
6. Build and run the `Main` class.

### Security notes

`config.properties` contains credentials and is listed in `.gitignore`; it should never be committed. Only `config.example.properties` should remain under version control, serving as a reference for new setups.

### Project status

Work in progress, built for learning purposes, covering relational database connectivity, the DAO pattern, and a basic terminal-driven sales flow.
