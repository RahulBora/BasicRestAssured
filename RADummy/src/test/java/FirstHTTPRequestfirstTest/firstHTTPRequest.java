package FirstHTTPRequestfirstTest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class firstHTTPRequest {
    int id;

//    @Test
    void getUsers(){
        given()
        .when()
            .get("https://reqres.in/api/users?page=2")
        .then()
            .statusCode(200)
            .body("page", equalTo(2))
            .log().all();

    }

    @Test(priority = 2)
    void createUser(){
        HashMap data= new HashMap();
        data.put("name", "testttt1");
        data.put("job", "traineeee1");
        id=given()
                .contentType("application/json")
                .body(data)
        .when()
            .post("https://reqres.in/api/users")
            .jsonPath().getInt("id");
//        .then()
//            .statusCode(201)
//            .body("name", equalTo("testttt"))
//            .log().all();

    }

    @Test(priority =3, dependsOnMethods = {"createUser"})
    void updateUser(){
        HashMap data= new HashMap();
        data.put("name", "testtNew");
        data.put("job", "traineeeeNeww");
        given()
            .contentType("application/json")
            .body(data)
        .when()
            .put("https://reqres.in/api/users/"+id)
        .then()
            .statusCode(200)
            .body("name", equalTo("testtNew"))
            .log().all();

    }
    @Test(priority =4, dependsOnMethods = {"createUser"})
    void deleteUser(){
        given()
                .when()
                .delete("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(204)
                .log().all();

    }
    @Test(priority = 5, dependsOnMethods = {"createUser"})
    void getUser(){
        given()
                .when()
                .get("https://reqres.in/api/users/"+id)
                .then()
                .statusCode(404)
                .log().all();

    }


}
