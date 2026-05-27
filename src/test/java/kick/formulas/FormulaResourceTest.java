package kick.formulas;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class FormulaResourceTest {

    @Test
    @DisplayName("Debería registrar una fórmula médica exitosamente")
    public void testRegistrarFormulaExitoso() {
        String nuevaFormulaJson = """
            {
                "idMedicamento": "1313",
                "medicamento": "Paracetamol",
                "dosis": "1 tableta",
                "identificacionPaciente": "10102030",
                "nombrePaciente": "Diego",
                "fecha": "2026-05-26"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(nuevaFormulaJson)
                .when()
                .post("/formulas")
                .then()
                .log().all() // <-- ESTA LÍNEA TE MOSTRARÁ EL JSON O TEXTO DE ERROR QUE DA EL BACKEND
                .statusCode(org.hamcrest.Matchers.oneOf(200, 201))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Debería retornar 400 Bad Request cuando el payload es vacío")
    public void testRegistrarFormulaCamposVacios() {
        String formulaInvalidaJson = "{}";

        given()
                .contentType(ContentType.JSON)
                .body(formulaInvalidaJson)
                .when()
                .post("/formulas") // Ajustado sin el prefijo duplicado
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Debería obtener la lista de fórmulas registradas")
    public void testListarFormulas() {
        given()
                .when()
                .get("/formulas") // Ajustado sin el prefijo duplicado
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", notNullValue());
    }
}