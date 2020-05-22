# Desafio QA - AME

Projeto desenvolvido para avaliação do processo seletivo AME

### Requisitos para execução dos teste automatizados

- Java 8 ou superior;
- Maven 3.5.4 ou superior;

### Execução dos testes

- API
  ```
  Para execução dos testes de api siga os passos:
  1 - Execute o prompt de comando;
  2 - Navegue até o diretório do projeto onde se encontra o arquivo "pom.xml";
  3 - Existem 2 opções para execução dos testes
            3.1 - Execute o comando 'mvn clean test -Drunner=RunTestAPI'
            3.2 - Execute o comando 'mvn clean test -Drunner=RunTest -Dcucumber.options="--tags @API"
  4 - Ao término da execução, o relatório será gerado no caminho 'target/extent-reports/ExtentHtml.html'
  ```
  OBS.: As apis propostas (http://dummy.restapiexample.com/) apresentam alguns problemas como:
    
    - Após realizar a inclusão de um cliente (Path=/create) e obter a mensagem esperada, o cliente não é incluído (Informação pode ser checada acessando o link: http://dummy.restapiexample.com/api/v1/employees)
    - Ao tentar realizar uma consulta (Path=/employee/) com o ID retornado após a inclusão, não é possível localizar o novo Funcionário.
    - Ao tentar deletar um funcionário (Path=/delete/), o mesmo apresenta intermitências, alternando entre sucesso e falha.
    - Ao realizar a exclusão de um funcionário (Path=/delete/) o funcionário não é efetivamente excluído.
    
- WEB
  ```
  Para execução dos testes de api siga os passos:
  1 - Execute o prompt de comando;
  2 - Navegue até o diretório do projeto onde se encontra o arquivo "pom.xml";
  3 - Existem 2 opções para execução dos testes
            3.1 - Execute o comando 'mvn clean test -Drunner=RunTestWEB'
            3.2 - Execute o comando 'mvn clean test -Drunner=RunTest -Dcucumber.options="--tags @WEB"
  4 - Ao término da execução, o relatório será gerado no caminho 'target/extent-reports/ExtentHtml.html'
  ```
  
- Autor

  **João Rener**