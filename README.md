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
/test
  ├── java
  │   ├── api.tests (Testes automatizados de API)
  │   ├── utils (Métodos auxiliares para os testes)
  ├── resources
  │   ├── payloads (Corpos de requisição JSON usados nos testes)
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

## 🛠️ Contribuição
Sinta-se à vontade para contribuir! Para isso:
1. Fork este repositório 🍴
2. Crie uma branch para suas alterações (`git checkout -b minha-feature`)
3. Commit suas mudanças (`git commit -m 'Adicionando nova funcionalidade'`)
4. Faça push para a branch (`git push origin minha-feature`)
5. Abra um Pull Request 🚀

## 📄 Licença
Este projeto está sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
🔹 *Desenvolvido por Felipe Almeida Muginsk* 🔹




