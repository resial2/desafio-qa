#language: pt
#encoding: utf-8
#author: João Rener

@API
Funcionalidade: Testes de API
  Serão realizados testes na API publica Dummy de acordo com o desafio

  Esquema do Cenario: Inclusão de empregado
    Dada configuração da requisição com as informações abaixo
      | NOME   | SALARIO   | IDADE   |
      | <NOME> | <SALARIO> | <IDADE> |
    Quando realizar o envio da requisição para URL "http://dummy.restapiexample.com/api/v1/create"
    Entao deverá ser retornado o status code <STATUS_CODE>
    E será validada a mensagem "<MENSAGEM>"
    Exemplos:
      | NOME      | SALARIO | IDADE | STATUS_CODE | MENSAGEM |
      | Fulano QA | 4000.00 | 22    | 200         | success  |