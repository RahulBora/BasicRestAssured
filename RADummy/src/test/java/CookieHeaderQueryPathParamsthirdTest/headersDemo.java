package CookieHeaderQueryPathParamsthirdTest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class headersDemo {

//    @Test(priority = 1)
    void testHeadersInfo(){
        given()
                .when()
                .get("https://www.google.com/")
                .then()
                .header("Content-Type", "text/html; charset=ISO-8859-1")
                .and()  // to separate different header validations. It is non-mandatory
                .header("Content-Encoding", "gzip");
        }
    @Test(priority = 1)
    void testMultipleHeadersInfo(){
       Response res= given()
                .when()
                .get("https://www.google.com/");

       //get Single header info
//       String contentType= res.getHeader("Content-Type");
//       System.out.println("The value of Content-Type is : "+ contentType);
//       String contentEncoding= res.getHeader("Content-Encoding");
//       System.out.println("The value of Content-Encoding is : "+ contentEncoding);

        //get all Headers info

        Headers headerContent= res.getHeaders();
        for(Header hd:headerContent) {
           System.out.println(hd.getName()+"   "+ hd.getValue());
        }
    }
}
