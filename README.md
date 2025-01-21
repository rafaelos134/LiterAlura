# FórumHub - API REST com Spring

## Descrição

O **FórumHub** é uma API REST criada com o objetivo de replicar operações básicas de um fórum, permitindo a criação, leitura, atualização e exclusão de tópicos. Utilizando o framework **Spring**, o projeto implementa funcionalidades para o gerenciamento de tópicos de forma simples e eficaz, incluindo autenticação/autorização para garantir a segurança das operações.

## Funcionalidades

A API **FórumHub** oferece as seguintes funcionalidades para manipulação de tópicos:

1. **Criar um novo tópico**: Permite que os usuários criem tópicos com um título e descrição.
2. **Mostrar todos os tópicos criados**: Exibe todos os tópicos registrados no banco de dados.
3. **Mostrar um tópico específico**: Exibe detalhes de um tópico específico com base no seu ID.
4. **Atualizar um tópico**: Permite a atualização do título e/ou descrição de um tópico já criado.
5. **Eliminar um tópico**: Exclui um tópico do banco de dados.

Essas operações seguem o padrão CRUD (Create, Read, Update, Delete) e foram implementadas utilizando o Spring para facilitar o desenvolvimento e a manutenção.

## Objetivos do Projeto

- **Implementação de API RESTful**: As rotas da API foram projetadas e implementadas seguindo as melhores práticas do modelo REST, permitindo fácil acesso e manipulação dos dados.
- **Validações**: A API realiza validações de dados para garantir que as operações respeitem as regras de negócio.
- **Persistência com Banco de Dados Relacional**: Utilizamos um banco de dados relacional para armazenar os tópicos, garantindo persistência e integridade dos dados.
- **Autenticação e Autorização**: Implementação de um sistema de autenticação/autorização para garantir que apenas usuários autorizados possam acessar e modificar informações sensíveis.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java com suporte a microservices e APIs REST.
- **JPA/Hibernate**: ORM para persistência de dados em banco relacional.
- **Banco de Dados Relacional**: MySQL, PostgreSQL ou outro banco de dados relacional compatível.
- **Spring Security**: Biblioteca para implementar autenticação e autorização de usuários.
