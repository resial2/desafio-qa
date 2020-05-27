# Desafio QA - AME

Projeto desenvolvido para avaliação do processo seletivo AME

### Requisitos para execução dos teste automatizados

- Java 8 ou superior;
- Maven 3.5.4 ou superior;
- Appium 1.17.1 (Instalação via NodeJS)
- Appium-doctor 1.15.1 (Instalação via NodeJS)
- Configurações recomendadas pelo comando "appium-doctor --android"
- AVD (Android Virtual Device) baixada por meio do Android Studio. (Versão recomendada Nexus 5X API 28)
- Modo Desenvolvedor liberado no computador

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
  
- Mobile
  ```
  Para execução dos testes de api siga os passos:
  1 - Execute o prompt de comando;
  2 - Navegue até o diretório do projeto onde se encontra o arquivo "pom.xml";
  3 - Existem 2 opções para execução dos testes
            3.1 - Execute o comando 'mvn clean test -Drunner=RunTestMobile'
            3.2 - Execute o comando 'mvn clean test -Drunner=RunTest -Dcucumber.options="--tags @MOBILE"
  4 - Ao término da execução, o relatório será gerado no caminho 'target/extent-reports/ExtentHtml.html'
  ```  
  
  OBS.: Caso deseje utilizar outra AVD que não seja a versão indicada (Nexus 5X API 28), será necessário informar o nome da AVD que será utilizada no arquivo que se encontra no diretório "src/test/resources/properties/mobile/mobile.properties"
  
### Ferramentas Utilizadas

- IntelliJ IDEA Community Edition 2020.1.1
- Apache Maven 3.5.4
- Java SDK 8
- Selenium Java 3.149.5
- Cucumber Java 5.7.0
- Cucumber Junit 5.7.0
- Extent-Reports 4.1.5
- Tech Grasshopper Extent Cucumber 5 Adapter 1.3.1
- Commons Configuration 1.9
- Junit 4.11
- Appium 1.17.1
- Appium Desktop 1.15.1
- Node JS 6.14.4
- Android Studio 3.6.3
 
### Autor

  **João Rener**