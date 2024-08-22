package chainingEigthTest;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class getUser {

    @Test
    void testGetUser(ITestContext context){
        String bearerToken= (String) context.getSuite().getAttribute("bearerToken");
        int id= (Integer) context.getSuite().getAttribute("userid") ;

        given()
                .headers("Authorization", "Bearer "+ bearerToken)
                .contentType("application/json")
                .pathParam("id",id )
                .get("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();
    }
}
