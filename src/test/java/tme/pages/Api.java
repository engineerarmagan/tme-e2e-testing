package tme.pages;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import tme.utilities.ConfigurationReader;


public class Api extends Base_page{
    //String CPID="BM3910~0~EN~33ec46fa-d837-4630-8899-4b3aff987195~ES~T2~9";
    public void before() {
        baseURI=ConfigurationReader.get("postman_URL");
    }

    public void post_published(){

        String username="armagan";
        String password="1Armagan1";
        String jsonBody="{\n" +
                "    \"user\": \"Automation\",\n" +
                "    \"status\": \"published\",\n" +
                "    \"reason\": \"VQD Recovered\"\n" +
                "}";
        Response response=  given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON).and()
                .auth()
                .basic(username,password).body(jsonBody)
                .when()
                .post(ConfigurationReader.get("postman_URL")+CPID);

       response.prettyPrint();

    }
    public void post_receivedOK(){

        String username="armagan";
        String password="1Armagan1";
        String jsonBody="{\n" +
                "    \"user\": \"Automation\",\n" +
                "    \"status\": \"receivedOk\",\n" +
                "    \"reason\": \"received well\"\n" +
                "}";
        Response response=  given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON).and()
                .auth()
                .basic(username,password).body(jsonBody)
                .when()
                .post(ConfigurationReader.get("postman_URL")+CPID);

        response.prettyPrint();
    }
    public void post_qaclaimed(){

        String username="armagan";
        String password="1Armagan1";
        String jsonBody="{\n" +
                "    \"user\": \"Automation\",\n" +
                "    \"status\": \"qaClaimed\",\n" +
                "    \"reason\": \"recovery\"\n" +
                "}";
        Response response=  given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON).and()
                .auth()
                .basic(username,password).body(jsonBody)
                .when()
                .post(ConfigurationReader.get("postman_URL")+CPID);

        response.prettyPrint();
    }
}
