#language: pt
#encoding: utf-8
#author: João Rener
  @LOGIN
  Funcionalidade: Login
    Serão realizados testes com tentativas de login no site proposto.

  Cenario: Login com Sucesso
    Dada abertura do site na página principal
    Quando clicar no botão "Sign in"
    E preencher os campos de e-mail e senha com as informações do arquivo de "login"
    E clicar no botão "Sign in" para entrar
    Entao deverá ser exibida a pagina principal do usuário

  Cenario: Login Inválido
    Dada abertura do site na página principal
    Quando clicar no botão "Sign in"
    E preencher os campos de e-mail e senha com as informações inválidas do arquivo de "login"
    E clicar no botão "Sign in" para entrar
    Entao deverá ser exibida a mensagem "Authentication failed"

  Cenario: Login em Branco
    Dada abertura do site na página principal
    Quando clicar no botão "Sign in"
    E não realizar o preenchimento dos campos de e-mail e senha
    E clicar no botão "Sign in" para entrar
    Entao deverá ser exibida a mensagem "An email address required"