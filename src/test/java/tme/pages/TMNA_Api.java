package tme.pages;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import tme.utilities.ConfigurationReader;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class TMNA_Api extends Base_page {


        //String CPID="BM3910~0~EN~33ec46fa-d837-4630-8899-4b3aff987195~ES~T2~9";
        public void before() {
            baseURI= ConfigurationReader.get("tmna_postman_URL");
        }
        String folder_name="";
        public void import_to_tmna(){

            String username="karenkelly";
            String password="M0uchamps";
            String text_body="upload/"+folder_name+"/"+folder_name+".met";
            Response response=  given().log().all()
                    .accept(ContentType.TEXT)
                    .and()
                    .contentType(ContentType.TEXT).and()
                    .auth()
                    .basic(username,password).body(text_body)
                    .when()
                    .post(ConfigurationReader.get("tmna_postman_URL"));

            response.prettyPrint();
        }
    }




