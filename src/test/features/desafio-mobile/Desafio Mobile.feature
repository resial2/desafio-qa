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
    Entao será realizado o preenchimento das informações aleatórias necessárias
    E deverá ser exibida a mensagem de "Cadastro efetuado com sucesso"
    E encerrarei o aplicativo

  Cenario: Pesquisa por usuário
    Dada abertura do aplicativo "app.cadastro.clientes.path"
    Quando realizar a pesquisa pelo nome do arquivo "file.cliente"
    Entao deverá ser exibida a tela de informações do usuário contendo o nome pesquisado
    E encerrarei o aplicativo

  Cenario: Exclusão de usuário
    Dada abertura do aplicativo "app.cadastro.clientes.path"
    Quando realizar a pesquisa pelo nome do arquivo "file.cliente"
    E deverá ser exibida a tela de informações do usuário contendo o nome pesquisado
    Entao será realizada a exclusão do usuário
    E deverá ser exibida a mensagem de exclusão "Cadastro excluído com sucesso"
    E encerrarei o aplicativo