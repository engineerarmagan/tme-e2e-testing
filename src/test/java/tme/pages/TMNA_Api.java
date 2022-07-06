package tme.pages;

import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import tme.utilities.ConfigurationReader;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class TMNA_Api extends Base_page {


        //String CPID="BM3910~0~EN~33ec46fa-d837-4630-8899-4b3aff987195~ES~T2~9";
        public void before() {
            baseURI= ConfigurationReader.get("tmna_postman_URL");
        }


public void upload_tmna() {


    String publication_name = "16Tacoma_NM26L0U_EN_21-08-11_UXad0806033340";
    String text_body = "upload/" + publication_name + "/" + publication_name + ".met";
    String username = "armagan";
    String password = "1Armagan1";
    System.out.println(ConfigurationReader.get("tmna_postman_URL"));

//
////#1
    Response response = given().log().all().accept(ContentType.TEXT)
            .and()
            .contentType(ContentType.TEXT).and()
            .auth()
            .basic(username, password).body(text_body)
            .when()
            .post(ConfigurationReader.get("tmna_postman_URL"));




    given().log().all().accept(ContentType.TEXT)
            .and()
            .contentType(ContentType.TEXT).and().
            header("X-XSRF-TOKEN", response.cookie("XSRF-TOKEN")).and().auth().basic(username, password)
            .body(text_body).
            when().
            post(ConfigurationReader.get("tmna_postman_URL")).then().log().all().assertThat().statusCode(200);
    response.prettyPrint();
//}}
////
////1 ) get sessionId
//    Response response =
//            given().auth().basic(username, password).contentType(ContentType.TEXT).body(text_body).
//                    when().post(ConfigurationReader.get("tmna_postman_URL")).
//                    then().log().all().extract().response();
//    String jsessionidId = response.getSessionId();//or response.cookie("JSESSIONID");
//
////2) get XSRF-TOKEN using new/real sessionId
//    response =
//            given().auth().basic(username, password).
//                    sessionId(jsessionidId).//or cookie("JSESSIONID", jsessionidId).
//                    contentType(ContentType.TEXT).body(text_body).
//                    when().post(ConfigurationReader.get("tmna_postman_URL")).
//                    then().log().all().extract().response();
//
////3) post data using XSRF-TOKEN
//    given().log().all().auth().basic(username, password).
//            sessionId(jsessionidId).//or cookie("JSESSIONID", jsessionidId).
//            header("X-XSRF-TOKEN", response.cookie("XSRF-TOKEN")).
//            body(text_body).
//            contentType(ContentType.TEXT).
//            when().
//            post(ConfigurationReader.get("tmna_postman_URL")).
//            then().
//            log().all().assertThat().statusCode(200);
//    response.prettyPrint();

}}
