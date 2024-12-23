
# Gerenciador de Pedidos

Um sistema para gerenciamento de pedidos, desenvolvido em Java, com conexão ao banco de dados MySQL. Este projeto tem como objetivo
praticar conceitos de desenvolvimento backend, incluindo manipulação de dados, DAO, e padrões de design.

## Funcionalidades

- **Cadastro de Pedidos**: Registre pedidos com itens e mesas associadas.
- **Gerenciamento de Estoque**: Controle os itens disponíveis para pedidos.
- **Funcionários**: Mantenha informações dos funcionários que registram pedidos.
- **Conexão com Banco de Dados**: Utilização de MySQL para armazenar os dados.

## Requisitos

- **Java JDK 11+**
- **MySQL Server**
- **Maven**: Para gerenciar dependências.

## Configuração

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```

2. Importe o projeto em sua IDE (IntelliJ IDEA ou Eclipse) como um projeto Maven.

3. Configure o banco de dados MySQL:
   - Crie um banco de dados utilizando o script `scriptDB.sql` presente na pasta `/Gerenciador de pedidos`.
   - Atualize as credenciais de acesso ao banco no arquivo `ConexaoMySQL.java`.

4. Compile e execute o projeto.

## Estrutura do Projeto

- `/src/main/java/org/example/Classes`: Contém as classes principais do sistema (e.g., Pedidos, Estoque, Funcionários).
- `/src/main/java/org/example/DAO`: Implementação dos DAOs para manipulação do banco de dados.
- `/src/main/java/org/example/Conexao`: Configuração e gerenciamento de conexões com o MySQL.
- `/Gerenciador de pedidos/scriptDB.sql`: Script para criação do banco de dados.

## Tecnologias Utilizadas

- **Java**
- **MySQL**
- **Maven**
- **Git**

## Melhorias Futuras

- Adicionar uma API RESTful para integração com frontends.
- Implementar testes unitários para garantir a qualidade do código.
- Adicionar logs para rastreamento de atividades no sistema.

## Autor

Charles Henrique
