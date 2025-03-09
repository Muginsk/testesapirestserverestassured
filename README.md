# testesapirestserverestassured

# Testes Automatizados de API com Rest Assured

## 📖 Descrição
Este repositório contém testes automatizados de API utilizando o framework **Rest Assured** em conjunto com **JUnit**. O objetivo é validar endpoints de uma API RESTful, garantindo qualidade e confiabilidade nos serviços expostos. 

## 🛠️ Tecnologias Utilizadas
- [Java](https://www.oracle.com/java/) - Linguagem de programação
- [Rest Assured](https://rest-assured.io/) - Framework para automação de testes de API
- [JUnit](https://junit.org/) - Framework para testes unitários em Java
- [Maven](https://maven.apache.org/) - Gerenciador de dependências
- [Git](https://git-scm.com/) - Controle de versão

## 🚀 Como Configurar e Executar os Testes

### 📥 Clonar o Repositório
```sh
git clone https://github.com/Muginsk/testesapirestserverestassured.git
cd testesapirestserverestassured
```

### 🏗️ Configurar o Projeto
Certifique-se de ter o **Java 11+** e o **Maven** instalados. Para verificar:
```sh
java -version
mvn -version
```
Caso precise instalar, consulte:
- [Java](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/install.html)

### ▶️ Executar os Testes
Para rodar os testes, utilize o Maven:
```sh
mvn test
```
Isso executará todos os testes automatizados definidos no projeto.

## 📌 Estrutura do Projeto
```
src/test/java/teste
  ├── login (Testes automatizados para a funcionalidade de login da API)
  ├── usuarios (Testes automatizados para a funcionalidade de usuários da API)
  |
target
  ├── extent.report.html (Relatório detalhado gerado após a execução dos testes)
```

## 📜 Exemplo de Teste com Rest Assured
```java
@Test
public void validarLoginComSucesso() {
    given()
        .basePath("/login")
        .header("Content-Type", "application/json")
        .body("{\"email\": \"usuario@teste.com\", \"password\": \"123456\"}")
    .when()
        .post()
    .then()
        .statusCode(200)
        .body("message", equalTo("Login realizado com sucesso"));
}
```

## 📊 Relatórios de Testes
Após a execução dos testes, um relatório detalhado é gerado em:
```
/target/extent.report.html
```
O relatório contém informações como:
- Quantidade de testes executados.
- Status (sucesso ou falha).
- Detalhes de cada teste.

## 🛠️ Boas Práticas Utilizadas
- **Organização por funcionalidade:** Os testes estão separados por pastas de acordo com as funcionalidades da API (login e usuários).
- **Relatórios automatizados:** Utilização do **ExtentReports** para gerar relatórios detalhados após a execução dos testes.
- **Facilidade de manutenção:** Estrutura clara e modular, facilitando a adição de novos testes ou modificações.


## 📄 Licença
Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
🔹 *Desenvolvido por Felipe Almeida Muginsk* 🔹

