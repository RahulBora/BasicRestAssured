package ParsingReqBodyfourthTest;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class parsingReqBody {

    @Test(priority = 1)
    void testJsonResponse1(){

        //Approach 1
        /*given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store")
                .then()
                .statusCode(200)
                .body("book[3].title", equalTo("The Lord of the Rings"))
                .log().all();*/

        //Approach 2 Capturing entire response and doing validation
        Response res= given()
                .contentType(ContentType.JSON)
                .get("http://localhost:3000/store");
        // After storing the entire response in res variable now we will use TestNg Assertions
       /* Assert.assertEquals(res.getStatusCode(), 200);
        String title= res.jsonPath().get("book[3].title").toString();
        Assert.assertEquals(title, "The Lord of the Rings"); */

        // Assertion Approach New. Now we will capture multiple data points which have similar key
        //using JSON object Class

        JSONObject jo= new JSONObject(res.asString()); // convert json response to string

        //asserting on a particular title of book
        boolean status= false;
        for(int i=0; i<jo.getJSONArray("book").length();i++) {
            String bookTitle= jo.getJSONArray("book").getJSONObject(i).get("title").toString();
            if(bookTitle.equals("The Lord of the Rings")){
                status= true;
                break;
            }
        }
        Assert.assertTrue(status);

        // Assertion on total price of the book
        double totalPrice =0;
        for(int i=0; i<jo.getJSONArray("book").length();i++) {
            String price= jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            totalPrice+=Double.parseDouble(price);;
        }
        System.out.println(totalPrice);
        Assert.assertEquals(totalPrice, 446.0);
    }
}
