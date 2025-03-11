package teste.login;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostLoginTest {

    static ExtentReports extent;
    ExtentTest test;

    public static final String BASE_URL = "https://serverest.dev";

    String requestBodyValido = "{\"email\": \"fulano@qa.com\", \"password\": \"teste2\"}";
    String requestBodyComUsuarioInexistente = "{\"email\": \"beltrano@qa.com\", \"password\": \"teste123\"}";
    String requestBodyComUsuarioInvalido = "{\"email\": \"beltrano@qa.com1\", \"password\": \"teste123\"}";

    @BeforeAll
    public static void report() {
        // Configura o ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void DadoUmNovoPostComDadosValidosQuandoCrioPostEntaoObtenhoStatusCode200() {

        test = extent.createTest("Deve retornar 200 quando login válido - status");

        given()
                .basePath("/login") // Define o caminho base da API para a requisição (endpoint de login)
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .body(requestBodyValido) // Define o corpo da requisição com os dados válidos para o login (armazenados na variável requestBodyValido)
                .log().all() // Exibe no log todos os detalhes da requisição antes de enviá-la
                .when() // Quando: Ação a ser executada
                .post()  // Envia a requisição HTTP do tipo POST para o endpoint de login
                .then() // Então: Validações após o envio da requisição
                .log().ifValidationFails()  // Caso ocorra uma falha na validação, os detalhes da resposta serão exibidos no log
                .statusCode(200); // Valida se a resposta retornada tem o status HTTP 200 (OK), indicando que o login foi bem-sucedido


        test.pass("Login realizado com sucesso");
    }

    @Test
    public void DadoUmNovoPostComUsuarioInexistenteQuandoCrioPostEntaoObtenhoStatusCode401() {

        test = extent.createTest("Deve retornar 401 quando usuario inexistente");

        given()
                .basePath("/login") // Define o caminho base da API para a requisição (endpoint de login)
                .header("Content-Type", "application/json") // Define o cabeçalho da requisição indicando que o corpo da requisição está em formato JSON
                .body(requestBodyComUsuarioInexistente) // Define o corpo da requisição com os dados de um usuário inexistente, simulando um erro de autenticação
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la
                .when() // Quando: Ação a ser executada
                .post() // Envia a requisição HTTP do tipo POST para o endpoint de login
                .then() // Então: Validações após o envio da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .statusCode(401) // Valida se a resposta retornada tem o status HTTP 401 (Unauthorized), indicando que a autenticação falhou
                .body("message", equalTo("Email e/ou senha inválidos")); // Valida se a mensagem de erro no corpo da resposta é a esperada ("Email e/ou senha inválidos")

        test.pass("Login não realizado conforme esperado");
    }

    @Test
    public void DadoUmNovoPostComUsuarioInvalidoQuandoCrioPostEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 401 quando usuario invalido");

        given()
                .basePath("/login") // Define o caminho base da API para a requisição (endpoint de login)
                .header("Content-Type", "application/json") // Adiciona um cabeçalho indicando que o corpo da requisição está em formato JSON
                .body(requestBodyComUsuarioInvalido) // Define o corpo da requisição contendo um email inválido para testar a validação do campo
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la
                .when() // Quando: Ação a ser executada
                .post() // Envia a requisição HTTP do tipo POST para o endpoint de login
                .then() // Então: Validações após o envio da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .statusCode(400) // Valida se a resposta retornada tem o status HTTP 400 (Bad Request), indicando erro de validação nos dados enviados
                .body("email", equalTo("email deve ser um email válido")); // Valida se a mensagem de erro retornada para o campo "email" é a esperada ("email deve ser um email válido")

        test.pass("Login nao realizado conforme esperado");
    }

    @Test

    public void DadoUmNovoPostValidoQuandoCrioPostEntaoValidoHeaders() {

        test = extent.createTest("Deve retornar 200 quando login valido - headers");

        given()
                .basePath("/login") // Define o caminho base da API para a requisição (endpoint de login)
                .header("Content-Type", "application/json") // Adiciona um cabeçalho indicando que o corpo da requisição está em formato JSON
                .body(requestBodyValido) // Define o corpo da requisição com dados válidos para login
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la
                .when() // Quando: Ação a ser executada
                .post() // Envia a requisição HTTP do tipo POST para o endpoint de login
                .then() // Então: Validações após o envio da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .header("Content-Type", equalTo("application/json; charset=utf-8")); // Valida se o cabeçalho "Content-Type" da resposta é o esperado, que deve ser "application/json; charset=utf-8"

        test.pass("Login retorna headers com sucesso");
    }

    @Test
    public void DadoUmNovoPostValidoQuandoCrioPostEntaoValidoBody() {

        test = extent.createTest("Deve retornar 200 quando login valido - body");

        given()
                .basePath("/login") // Define o caminho base da API para a requisição
                .header("Content-Type", "application/json") // Adiciona um cabeçalho indicando que o corpo da requisição está em formato JSON
                .body(requestBodyValido) // Define o corpo da requisição com dados válidos para login
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la
                .when() // Quando: Ação a ser realizada
                .post() // Envia a requisição HTTP do tipo POST para o endpoint de login
                .then() // Então: Validações após o envio da requisição
                .log().ifValidationFails() // Caso a validação falhe, exibe os detalhes da resposta no log
                .statusCode(200) // Valida se o status da resposta é 200 (OK), indicando sucesso no login
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Valida se o cabeçalho "Content-Type" da resposta é o esperado
                .body("message", equalTo("Login realizado com sucesso")) // Valida se a mensagem no corpo da resposta é "Login realizado com sucesso"
                .body("authorization", startsWith("Bearer ")); // Valida se o campo "authorization" começa com "Bearer ", indicando que um token de autorização foi gerado com sucesso

        test.pass("Login retorna body com sucesso");
    }

    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }
}
