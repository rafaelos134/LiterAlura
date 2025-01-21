# LiterAlura - Catálogo de Livros

## Descrição

O **LiterAlura** é um desafio de programação que eu desenvolvi para construir meu próprio catálogo de livros. Durante esse projeto, aprendi a consumir uma API de livros, manipular dados em formato JSON, armazená-los em um banco de dados e, por fim, exibir e filtrar os livros e autores de interesse. Este projeto foi uma ótima oportunidade para praticar o desenvolvimento em Java e integração com APIs externas.

## Objetivo

O objetivo do **LiterAlura** é desenvolver um **Catálogo de Livros** que oferece interação com o usuário via console. Eu criei um sistema com pelo menos 5 opções de interação, onde o usuário pode buscar livros, visualizar informações e aplicar filtros. Para isso, a aplicação consome dados de uma API de livros, os armazena em um banco de dados e permite que os resultados sejam apresentados ao usuário.

## Funcionalidades

O **LiterAlura** permite as seguintes interações com o usuário:

1. **Buscar livros por autor**: O usuário pode buscar livros de um autor específico.
2. **Buscar livros por título**: O usuário pode procurar livros que correspondam a um título fornecido.
3. **Mostrar todos os livros**: Exibe todos os livros cadastrados no catálogo.
4. **Exibir detalhes de um livro**: Mostra informações detalhadas de um livro, como título, autor e descrição.
5. **Filtrar livros por categoria**: Permite ao usuário filtrar livros por categorias, como ficção, não-ficção, etc.

## Tecnologias Utilizadas

- **Java**: Linguagem principal para o desenvolvimento do projeto.
- **API de Livros**: Utilizei uma API pública de livros para consumir dados (detalhes da API podem ser encontrados na seção "Backlog").
- **Banco de Dados**: Armazenamento dos livros e autores em um banco de dados relacional (MySQL ou PostgreSQL).
- **JDBC**: Utilizado para integrar o Java com o banco de dados e realizar operações CRUD.
- **JSON**: Manipulação das respostas da API, que são retornadas em formato JSON.
- **Console Interativo**: Interface de interação com o usuário via terminal/console.

## Passos para Completar o Desafio

Aqui estão os passos para concluir o projeto:

1. **Configuração do Ambiente Java**:
   - Primeiro, configurei meu ambiente de desenvolvimento Java, garantindo que o JDK estivesse instalado corretamente.

2. **Criação do Projeto**:
   - Em seguida, criei o projeto Java e comecei o desenvolvimento do catálogo de livros.

3. **Consumo da API**:
   - Para buscar informações sobre livros e autores, consumi a API de livros. As respostas vieram em formato JSON.

4. **Análise da Resposta JSON**:
   - Com as respostas JSON da API, usei bibliotecas como `Gson` ou `Jackson` para analisar e extrair os dados relevantes.

5. **Inserção e Consulta no Banco de Dados**:
   - Armazenei as informações sobre os livros e autores em um banco de dados, usando JDBC para realizar inserções e consultas.

6. **Exibição de Resultados aos Usuários**:
   - Por fim, criei uma interface de console onde os usuários podem visualizar os livros, filtrar por autor, título ou categoria, e ver detalhes completos de cada livro.

## Exemplo de Interação com o Usuário

A aplicação permite que o usuário interaja com o catálogo de livros por meio de um menu no console. Aqui está um exemplo de como o menu pode ser exibido:

