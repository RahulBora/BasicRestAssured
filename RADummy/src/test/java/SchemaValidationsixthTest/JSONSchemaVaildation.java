package SchemaValidationsixthTest;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class JSONSchemaVaildation {

    @Test
    void testJSONSchema(){
        given()
                .get("http://localhost:3000/store")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));

    }
}
