package teste.login;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostLoginTest {

    public static final String BASE_URL = "http://localhost:3000";
    String requestBodyValido = "{\"email\": \"fulano@qa.com\", \"password\": \"teste\"}";
    String requestBodyComUsuarioInexistente = "{\"email\": \"beltrano@qa.com\", \"password\": \"teste123\"}";
    String requestBodyComUsuarioInvalido = "{\"email\": \"beltrano@qa.com1\", \"password\": \"teste123\"}";
    
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = BASE_URL;

    }


        @Test
        public void DadoUmNovoPostComDadosValidosQuandoCrioPostEntaoObtenhoStatusCode200() {

            given()
                    .basePath("/login") // Define o caminho base
                    .header("Content-Type", "application/json") // Define o header Content-Type
                    .body(requestBodyValido) // Define o corpo da requisição
                    .log().all() // Loga todos os detalhes da requisição
                    .when()
                    .post() // Faz a requisição POST
                    .then()
                    .log().ifValidationFails() // Loga detalhes se a validação falhar
                    .statusCode(200) // Valida o status code (Created)
                    .header("Content-Type", equalTo("application/json; charset=utf-8")) // Valida o Content-Type
                    .body("message", equalTo("Login realizado com sucesso")) // Valida o título
                    .body("authorization", startsWith("Bearer ")); // Valida o corpo

        }

    @Test
    public void DadoUmNovoPostComUsuarioInexistenteQuandoCrioPostEntaoObtenhoStatusCode401() {

        given()
                .basePath("/login") // Define o caminho base
                .header("Content-Type", "application/json") // Define o header Content-Type
                .body(requestBodyComUsuarioInexistente) // Define o corpo da requisição
                .log().all() // Loga todos os detalhes da requisição
                .when()
                .post() // Faz a requisição POST
                .then()
                .log().ifValidationFails() // Loga detalhes se a validação falhar
                .statusCode(401) // Valida o status code (Created)
                .body("message", equalTo("Email e/ou senha inválidos")); // Valida o título

    }
    
    @Test
    public void DadoUmNovoPostComUsuarioInvalidoQuandoCrioPostEntaoObtenhoStatusCode400() {

        given()
                .basePath("/login") // Define o caminho base
                .header("Content-Type", "application/json") // Define o header Content-Type
                .body(requestBodyComUsuarioInvalido) // Define o corpo da requisição
                .log().all() // Loga todos os detalhes da requisição
                .when()
                .post() // Faz a requisição POST
                .then()
                .log().ifValidationFails() // Loga detalhes se a validação falhar
                .statusCode(400) // Valida o status code (Created)
                .body("email", equalTo("email deve ser um email válido")); // Valida o título

    }

    @Test

    public void DadoUmNovoPostValidoQuandoCrioPostEntaoValidoHeaders() {

        given()
                .basePath("/login") // Define o caminho base
                .header("Content-Type", "application/json") // Define o header Content-Type
                .body(requestBodyValido) // Define o corpo da requisição
                .log().all() // Loga todos os detalhes da requisição
                .when()
                .post() // Faz a requisição POST
                .then()
                .log().ifValidationFails() // Loga detalhes se a validação falhar
                .header("Content-Type", equalTo("application/json; charset=utf-8")); // Valida o Content-Type
    }

    @Test
    public void DadoUmNovoPostValidoQuandoCrioPostEntaoValidoBody () {

        given()
                .basePath("/login") // Define o caminho base
                .header("Content-Type", "application/json") // Define o header Content-Type
                .body(requestBodyValido) // Define o corpo da requisição
                .log().all() // Loga todos os detalhes da requisição
                .when()
                .post() // Faz a requisição POST
                .then()
                .log().ifValidationFails() // Loga detalhes se a validação falhar
                .statusCode(200) // Valida o status code (Created)
                .header("Content-Type", equalTo("application/json; charset=utf-8")) // Valida o Content-Type
                .body("message", equalTo("Login realizado com sucesso")) // Valida o título
                .body("authorization", startsWith("Bearer ")); // Valida o corpo

    }
}
