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
    Quando realizar o envio da requisição POST para URL "http://dummy.restapiexample.com/api/v1/create"
    Entao deverá ser retornado o status code <STATUS_CODE>
    E será validada a mensagem "<MENSAGEM>"
    E será salva a informação de ID para uso futuro no arquivo "file.api"
    Exemplos:
      | NOME      | SALARIO | IDADE | STATUS_CODE | MENSAGEM |
      | Fulano QA | 4000.00 | 22    | 200         | success  |

  Cenario: Consulta de empregado
    Dada configuração da requisição com a informação armazenada no arquivo "file.api"
    Quando realizar o envio da requisição GET para URL "http://dummy.restapiexample.com/api/v1/employee/"
    Entao deverá ser retornado o status code 200
    E será validada a mensagem "success"

  Cenario: Exclusão de empregado
    Dada configuração da requisição com a informação armazenada no arquivo "file.api"
    Quando realizar o envio da requisição DELETE para URL "http://dummy.restapiexample.com/api/v1/delete/"
    Entao deverá ser retornado o status code 200
    E será validada a mensagem "success"