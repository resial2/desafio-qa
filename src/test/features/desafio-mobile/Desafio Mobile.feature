#language: pt
#encoding: utf-8
#author: João Rener

  @MOBILE
  Funcionalidade: Mobile
    Serão realizados testes mobile com o aplicativo disponibilizado


  Cenario: Cadastro de usuário
    Dada abertura do aplicativo "app.cadastro.clientes.path"
    Quando clicar no botão de Menu
    E selecionar a opção Cadastrar Novo
    E encerrarei o aplicativo

  Cenario: Pesquisa por usuário
    Dada abertura do aplicativo "app.cadastro.clientes.path"
    Quando realizar a pesquisa pelo nome "Joao Rener"
    Entao deverá ser exibida a tela de informações do usuário contendo o nome pesquisado
    E encerrarei o aplicativo