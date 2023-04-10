package com.br;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class ControladorRiscoTest {
 
@Test    
private ExecutorService enviandoRisco() {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(() -> {
        while(true) {
            given()
            .contentType(ContentType.JSON)
            .body("{\"id_cadastro\":\"1b42efe8-3a86-4ac2-9815-0a859223f35b\",\"id_documento\":\"23056872812\",\"registro\":\"id_documento\":\"94278653000\"}")
    .when()
            .post("/riscos")
    .then()
            .statusCode(202);

    given()
            .contentType(ContentType.JSON)
            .body("{\"id_cadastro\":\"1b42efe8-3a86-4ac2-9815-0a859223f35b\",\"id_documento\":\"23056872812\",\"registro\":\"id_documento\":\"94278653000\"}")
    .when()
            .post("/riscos")
    .then()
            .statusCode(202);

    try {
        Thread.sleep(200L);
    } catch (InterruptedException e) {
        break;
    }
        }
    });
    return executorService;
}


}
