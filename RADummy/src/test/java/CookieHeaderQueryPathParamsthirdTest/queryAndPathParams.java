package CookieHeaderQueryPathParamsthirdTest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class queryAndPathParams {

    @Test
    void testPathAndQueryParam(){
        given()
                .pathParams("mypath", "users")
                .queryParam("id", 5)
                .queryParam("page", 2)
                .when()
                .get("https://reqres.in/api/{mypath}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
