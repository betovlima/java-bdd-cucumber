# language: pt
@crud
Funcionalidade: CRUD de Produtos (sem banco)
  Como um usuário da API
  Quero gerenciar produtos
  Para manter um catálogo simples

  Contexto:
    Dado que o catálogo de produtos está vazio

  Cenário: Fluxo completo de CRUD
    Quando eu crio um produto com nome "Camiseta" e preco 49.90
    Então a resposta deve ter status 201
    E eu guardo o id retornado
    Quando eu busco o produto pelo id guardado
    Então a resposta deve ter status 200 e nome "Camiseta" e preco 49.90
    Quando eu atualizo o produto guardado para nome "Camiseta Premium" e preco 79.90
    Então a resposta deve ter status 200 e nome "Camiseta Premium" e preco 79.90
    Quando eu deleto o produto guardado
    Então a resposta deve ter status 204
    Quando eu busco o produto pelo id guardado
    Então a resposta deve ter status 404
    Quando eu crio um produto sem nome e com preco 49.90
    Então a resposta deve ter status 400



