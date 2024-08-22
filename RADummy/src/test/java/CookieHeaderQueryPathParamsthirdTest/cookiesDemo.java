package CookieHeaderQueryPathParamsthirdTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class cookiesDemo {

    @Test(priority = 1)
    void testCookies(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .cookie("AEC", "dcdcdcdcdscsdcd")
                .log().all();
    }

    @Test(priority = 2)
    void testGetCookiesInfo(){
        Response res=given()
                .when()
                .get("https://www.google.com/");
        //get single cookie info
        //String AEC_cookie_value=res.getCookie("ACE");
        //System.out.println(AEC_cookie_value);

        //get all cookies
        Map<String, String> cookies_values= res.getCookies();
        System.out.println(cookies_values.keySet()); // print only keys
        for(String k:cookies_values.keySet() ){
            String cookie_value=res.getCookie(k);
            System.out.println(k+ "  "+ cookie_value);
        }

//                .then()
//                .cookie("AEC", "dcdcdcdcdscsdcd")
//                .log().all();
    }
}
