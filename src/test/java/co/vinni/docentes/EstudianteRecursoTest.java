package co.vinni.docentes;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class EstudianteRecursoTest {
    @Test
    public void testCrearEstudiante() {
        String json = """
        { "nombre":"Elsa",
          "apellido":"Patero",
          "email":"epatero@gmail.com"
        }
        """;
        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                    .post("/docentes")
                .then()
                .statusCode(201);
    }
    @Test
    public void testListaEstudiante() {
        testCrearEstudiante();

        given()
                .when()
                    .get("/docentes")
                .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("nombre",hasItems("Elsa") )
                    .body("email",hasItems("epatero@gmail.com") );


    }
    @Test
    public void testCorreoInvalido() {
        String jsonInvalido = """
        { "nombre":"Elsa",
          "apellido":"Patero",
          "email":"epatero"
        }
        """;
        given()
                .contentType(ContentType.JSON)
                .body(jsonInvalido)
                .when()
                .post("/docentes")
                .then()
                    .statusCode(400)
                .body("mensaje",is("Error de validación en los datos de entrada") );
    }
}
