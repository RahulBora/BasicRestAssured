package FileDownloadUploadAndParsingXMLResfifthTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class fileUpload {

    @Test
    void testSingleFileUpload(){
        File myFile1= new File("/Users/rahul/IdeaProjects/RADummy/src/test/java/fifthTest/Template.pdf");
//        File myFile2= new File("Template.pdf");

        // Pass multiple files in an array instead of sending one by one as above
//        File filearr[]={myFile1, myFile2};

        Response res=
                given()
//                .multiPart("file", "filearr")
                .multiPart("file",myFile1,"multipart/form-data")
//                .multiPart("file","myFile2")
                .post("https://the-internet.herokuapp.com/upload")
                        .thenReturn();
        System.out.println(res.prettyPrint());
    }

    @Test
    void testSingleFileDownload() {

        given()
                .get("https://the-internet.herokuapp.com/download/CV .pdf")
                .then()
                .statusCode(200)
                .log().body();

    }
}
