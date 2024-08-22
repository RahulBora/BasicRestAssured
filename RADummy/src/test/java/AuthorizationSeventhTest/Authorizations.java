package AuthorizationSeventhTest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

    public class Authorizations {
        @Test(priority = 1)
        void testBasicAuthentication(){
                given()
                        .auth().basic("postman", "password")
                        .get("https://postman-echo.com/basic-auth")
                        .then()
                        .statusCode(200)
                        .body("authenticated", equalTo(true))
                        .log().all();
        }

        @Test(priority = 2)
        void testDigestAuthentication(){
            given()
                    .auth().digest("postman", "password")
                    .get("https://postman-echo.com/basic-auth")
                    .then()
                    .statusCode(200)
                    .body("authenticated", equalTo(true))
                    .log().all();
        }

        @Test(priority = 3)
        void testPreemptiveAuthentication(){
            given()
                    .auth().preemptive().basic("postman", "password")
                    .get("https://postman-echo.com/basic-auth")
                    .then()
                    .statusCode(200)
                    .body("authenticated", equalTo(true))
                    .log().all();
        }

        @Test(priority = 4)
        void testBearerTokenAuthentication(){

            String bearerToken= "";
            given()
                    .headers("Authorization", "Bearer "+ bearerToken)
                    .get("https://api.github.com/user/repos")
                    .then()
                    .statusCode(200)
                    .log().all();

        }

    //    @Test(priority = 5)
        void testOAuth1Authentication(){

            given()
                    .auth().oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
                    .get("https://api.github.com/user/repos")
                    .then()
                    .statusCode(200)
                    .log().all();

        }
        @Test(priority = 6)
        void testOAuth2Authentication(){

            given()
                    .auth().oauth2("")
                    .get("https://api.github.com/user/repos")
                    .then()
                    .statusCode(200)
                    .log().all();

        }

        @Test(priority = 7)
        void testAPIKeyAuthentication(){

            given()
                    .headers("X-Auth-Token", "")
                    .queryParam("documentId", "FMAI5OQ")
                    .get("https://preprod.leegality.com/api/v3.3/document/details")
                    .then()
                    .statusCode(200)
                    .log().all();

        }
    }
