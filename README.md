# testesapirestserverestassured

Automação de API com Rest Assured
Este repositório contém testes de automação para uma API utilizando o framework Rest Assured. O objetivo dos testes é garantir que a API esteja funcionando corretamente, validando os endpoints e as respostas.

📝 Índice
Sobre o Projeto
Pré-requisitos
Como Rodar os Testes
Estrutura de Diretórios
Exemplo de Teste
Contribuindo
Licença
📌 Sobre o Projeto
Este projeto utiliza Rest Assured, um framework de testes para automação de APIs em Java, para realizar testes automatizados contra uma API REST. Os testes têm como objetivo garantir que os endpoints da API estejam funcionando como esperado, validando os dados de entrada e saída, status codes e respostas.

⚙️ Pré-requisitos
Antes de rodar os testes, você precisará ter as seguintes ferramentas instaladas:

Java 8 ou superior
Maven (para gerenciar as dependências e rodar os testes)
IDE como IntelliJ IDEA ou Eclipse para rodar o código
Rest Assured (este projeto já inclui a dependência no pom.xml)
🚀 Como Rodar os Testes
Clone o repositório:

bash
Copiar
Editar
git clone https://github.com/Muginsk/testesapirestserverestassured.git
cd testesapirestserverestassured
Verifique se o Maven está instalado:

bash
Copiar
Editar
mvn -v
Instale as dependências: Se você nunca executou o Maven no projeto antes, execute o seguinte comando para baixar todas as dependências:

bash
Copiar
Editar
mvn install
Execute os testes: Após configurar o Maven, basta rodar os testes com o seguinte comando:

bash
Copiar
Editar
mvn test
Isso executará todos os testes automatizados na pasta src/test/java.

Verifique os resultados: Após a execução dos testes, os resultados serão exibidos no terminal, e você pode verificar os detalhes nos logs de cada execução.

🗂️ Estrutura de Diretórios
O repositório tem a seguinte estrutura de diretórios:

bash
Copiar
Editar
testesapirestserverestassured/
│
├── src/
│   ├── main/
│   │   └── java/                  # Código da aplicação (não se aplica para este repositório)
│   └── test/
│       └── java/
│           └── com/
│               └── example/       # Pacote de testes
│                   └── ApiTest.java
│                   └── AuthTest.java
├── pom.xml                         # Dependências e configurações do Maven
├── README.md                       # Este arquivo
👨‍💻 Exemplo de Teste
Aqui está um exemplo de como um teste simples para um endpoint de login pode ser estruturado usando Rest Assured:

java
Copiar
Editar
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AuthTest {

    @Test
    public void loginTest() {
        RestAssured.baseURI = "https://api.exemplo.com";
        
        given()
            .header("Content-Type", "application/json")
            .body("{ \"email\": \"usuario@exemplo.com\", \"senha\": \"senha123\" }")
        .when()
            .post("/login")
        .then()
            .statusCode(200)
            .body("message", equalTo("Login realizado com sucesso"))
            .body("authorization", startsWith("Bearer "));
    }
}
Esse teste realiza um POST para o endpoint de login e valida que o código de status retornado é 200 OK, a mensagem de sucesso e o formato do token de autorização.

🤝 Contribuindo
Se você deseja contribuir para o projeto, siga os passos abaixo:

Faça um fork deste repositório.
Crie uma branch para suas alterações (git checkout -b minha-branch).
Faça as alterações necessárias e faça um commit (git commit -m 'Adicionando novos testes').
Faça o push para a sua branch (git push origin minha-branch).
Abra um Pull Request explicando as alterações feitas.
📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.
