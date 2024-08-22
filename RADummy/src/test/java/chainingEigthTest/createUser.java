package chainingEigthTest;

import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class createUser {

    @Test
    void testCreateUser(ITestContext context){

        Faker faker= new Faker();
        String bearerToken= "4574c1adee4e9d163846a4d78eb97ce12a58f34d11fa8ce81ba13758777b84d2";
        JSONObject data=new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender", "Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");


        int id =given()
                .headers("Authorization", "Bearer "+ bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        context.getSuite().setAttribute("userid", id);
        context.getSuite().setAttribute("bearerToken", bearerToken);
        System.out.println(id);

    }
}
