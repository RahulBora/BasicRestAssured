package FileDownloadUploadAndParsingXMLResfifthTest;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class parsingXMLResponse {

    //@Test
    void testParseXMLResponse(){

        //Approach 1 when we need to validate the static data
        /*given()
                .get("https://thetestrequest.com/authors.xml")
                .then()
                .statusCode(200)
                .log().all()
                .header("Content-Type", "application/xml; charset=utf-8")
                .body("objects.object[0].name", equalTo("Karl Zboncak"))
                .body("objects.object[1].name", equalTo("Jeffie Wolf I"));*/

        //Approach 2 to validate data by storing response into variable

        Response res= given()
                .get("https://thetestrequest.com/authors.xml");
        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml; charset=utf-8" );

        Assert.assertEquals(res.xmlPath().get("objects.object[0].name").toString(), "Karl Zboncak");

    }

    @Test
    void testParseXMLResponseBody(){

        Response res= given()
                .get("https://thetestrequest.com/authors.xml");
        XmlPath xmlobj= new XmlPath(res.asString());
        List<String> authorsData= xmlobj.getList("objects.object.name");
        Assert.assertEquals(authorsData.size(), 5);

        // Verify author name is present in response or not
        boolean status=false;
        for(String authorName:authorsData){
            System.out.println(authorName);
            if(authorName.equals("Gracia Keeling")){
                status=true;
                break;
            }
        }
        Assert.assertTrue(status);
    }
}
