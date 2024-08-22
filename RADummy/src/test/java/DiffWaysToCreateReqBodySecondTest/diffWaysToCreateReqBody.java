package DiffWaysToCreateReqBodySecondTest;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.*;

/*
1. Post request using Hashmap
2. Post request using Org.Json
3. Post request using POJO Class
4. Post request using external json file

*/
public class diffWaysToCreateReqBody {

//    @Test(priority = 1)
    void createUserUsingHashMap(){
        HashMap data= new HashMap();
        data.put("name", "qwertyy");
        data.put("location", "Russia");
        data.put("phone", "1234567898");
        String[] courseArr = {"DS", "ML", "Rest"};
        data.put("courses", courseArr);

        given()
                .contentType("application/json")
                .body(data)
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json")
                .log().all();
    }

//    @Test(priority = 1)
    void createUserUsingOrgJson(){
        JSONObject data= new JSONObject();
        data.put("name", "Queeeen");
        data.put("location", "UK");
        data.put("phone", "0987654321");
        String[] courseArr = {"DS", "ML", "Rest"};
        data.put("courses", courseArr);


        given()
                .contentType("application/json")
                .body(data.toString())
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json")
                .log().all();
    }

//    @Test(priority = 1)
    void createUserUsingPOJOClass(){
        POJO_CreateData data= new POJO_CreateData();
        data.setName("King");
        data.setLocation("London");
        data.setPhone("2233223345");
        String[] courseArr = {"DS", "ML", "Rest"};
        data.setCoursesArr(courseArr);


        given()
                .contentType("application/json")
                .body(data)
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 1)
    void createUserUsingExternalFile() throws FileNotFoundException {
        File f= new File(".//body.json");
        FileReader fr= new FileReader(f);
        JSONTokener jt= new JSONTokener(fr);
        JSONObject data= new JSONObject(jt);
        given()
                .contentType("application/json")
                .body(data.toString())
                .post("http://localhost:3000/students")
                .then()
                .statusCode(201)
                .header("Content-Type", "application/json")
                .log().all();
    }

    @Test(priority = 2)
    void deleteUser(){
        given()
                .contentType("application/json")
                .delete("http://localhost:3000/students/5")
                .then()
                .statusCode(200)
                .log().all();

    }

}
