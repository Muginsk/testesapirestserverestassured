package teste.usuarios;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PutUsuariosTest {
    static ExtentReports extent;
    ExtentTest test;

    public static final String BASE_URL = "http://localhost:3000";

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
    public void DadoUmPutComUsuarioExistenteQuandoCrioPutPorIdValidoEntaoObtenhoStatusCode200() {

        test = extent.createTest("Deve retornar 200 quando usuario for alterado pelo id valido");

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz1") // Define o parâmetro de caminho "_id" com o valor especificado (ID do usuário a ser atualizado)
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .body("{\"nome\": \"Fulano da silva\" , \"email\": \"fulano@qa.com\", \"password\": \"teste2\", \"administrador\": \"true\"}") // Define o corpo da requisição com os dados a serem enviados no PUT (atualização do usuário)
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la
                .when() // Inicia a execução da requisição
                .put("/{_id}") // Realiza uma requisição HTTP do tipo PUT para o endpoint "/usuarios/{_id}", com o ID especificado para atualizar o usuário
                .then() // Inicia as validações da resposta da requisição
                .log().all() // Loga os detalhes da resposta da requisição se a validação falhar
                .log().status() // Loga o código de status da resposta para análise em caso de falha
                .statusCode(200) // Valida que o código de status da resposta é 200 (OK), indicando sucesso na atualização
                .body("message", equalTo("Registro alterado com sucesso")); // Valida que o corpo da resposta contém a mensagem "Registro alterado com sucesso", indicando que a atualização foi bem-sucedida


        test.pass("Alteracao realizada pelo parametro id com sucesso");
    }

    @Test
    public void DadoUmPutComEmailExistenteQuandoCrioPutPorIdDiferenteDoExistenteEntaoObtenhoStatusCode400() {

        test = extent.createTest("Deve retornar 400 quando usuario tiver o mesmo nome e email mas id diferente do existente");


        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", "0uxuPY0cbmQhpEz11") // Define o parâmetro de caminho "_id" com o valor especificado (ID do usuário a ser atualizado)
                .header("Content-Type", "application/json") // Adiciona um cabeçalho para indicar que o corpo da requisição está em formato JSON
                .body("{\"nome\": \"Fulano da silva\" , \"email\": \"ciclano@qa.com\", \"password\": \"teste2\", \"administrador\": \"true\"}") // Define o corpo da requisição com os dados a serem enviados no PUT (atualização do usuário)
                .log().all() // Loga todos os detalhes da requisição antes de enviá-la
                .when() // Inicia a execução da requisição
                .put("/{_id}") // Realiza uma requisição HTTP do tipo PUT para o endpoint "/usuarios/{_id}", com o ID especificado para atualizar o usuário
                .then() // Inicia as validações da resposta da requisição
                .log().all() // Loga os detalhes da resposta da requisição se a validação falhar
                .log().status() // Loga o código de status da resposta para análise em caso de falha
                .statusCode(400) // Valida que o código de status da resposta é 400 (Bad Request), indicando que houve um erro na requisição
                .body("message", equalTo("Este email já está sendo usado")); // Valida que o corpo da resposta contém a mensagem "Este email já está sendo usado", indicando que o email fornecido já está registrado no sistema


        test.pass("Alteracao nao realizada pelo valor do parametro email ja ser existente");
    }

    @Test
    public void DadoUmPutComEmailInexistenteQuandoCrioPutPorIdInexistenteEntaoObtenhoStatusCode201() {

        test = extent.createTest("Deve retornar 201 quando usuario tiver email e id diferente do existente - criacao de usuario");

        String idAleatorio = UUID.randomUUID().toString(); // Gera um ID aleatório
        String emailAleatorio = "user" + UUID.randomUUID().toString().substring(0, 5) + "@qa.com"; // Gera um email único

        given()
                .basePath("/usuarios") // Define o caminho base para a requisição (endpoint de usuários)
                .pathParam("_id", idAleatorio) // Define o parâmetro de caminho "_id" com um valor aleatório para o ID do usuário
                .header("Content-Type", "application/json") // Define o cabeçalho da requisição indicando que o corpo da requisição será em formato JSON
                .body("{\"nome\": \"Fulano da Silva\" , \"email\": \"" + emailAleatorio + "\", \"password\": \"teste2\", \"administrador\": \"true\"}") // Define o corpo da requisição com um nome fixo e um email aleatório gerado na variável emailAleatorio
                .log().all() // Exibe o log completo da requisição antes de enviá-la
                .when() // Inicia a execução da requisição
                .put("/{_id}") // Realiza uma requisição HTTP do tipo PUT para o endpoint "/usuarios/{_id}", usando o ID aleatório para atualizar ou criar o usuário
                .then() // Inicia as validações da resposta
                .log().all() // Exibe o log da resposta da requisição apenas se a validação falhar
                .statusCode(201) // Valida que o código de status da resposta é 201 (Created), indicando que o cadastro foi realizado com sucesso
                .body("message", equalTo("Cadastro realizado com sucesso")) // Valida que a mensagem na resposta seja "Cadastro realizado com sucesso"
                .body("_id", notNullValue()); // Valida que o campo "_id" não seja nulo, indicando que o usuário foi criado com sucesso e recebeu um ID


        test.pass("Criacao de usuario realizada com sucesso");
    }

    @AfterAll
    public static void tearDown() {
        // Finaliza o relatório
        extent.flush();

    }

}
