package SchemaValidationsixthTest;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class XMLSchemaValidation {

    @Test
    void testXMLSchemaValidation(){

        given().get("https://thetestrequest.com/authors.xml")
                .then()
                .assertThat().body(RestAssuredMatchers.matchesXsd("author.xsd"));

    }
}
