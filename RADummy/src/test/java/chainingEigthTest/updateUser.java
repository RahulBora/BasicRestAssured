package chainingEigthTest;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class updateUser {

    @Test
    void testUpdateUser(ITestContext context){
        Faker faker= new Faker();
        String bearerToken= (String) context.getSuite().getAttribute("bearerToken");
        int id= (Integer) context.getSuite().getAttribute("userid");
        JSONObject data=new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender", "Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");


        given()
                .headers("Authorization", "Bearer "+ bearerToken)
                .contentType("application/json")
                .pathParams("id",id )
                .body(data.toString())
                .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                .statusCode(200)
                .log().all();

    }
}
