package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtils extends Base{
    private PrintStream log;
    private PrintStream out;

    /**
     * This will return base for all request builder that  will print rest request logs to terminal and logs/app.log filer
     * @return RequestSpecBuilder with logging filers enable
     */
    public RequestSpecBuilder baseLogBuilder(){
        try {
            log = new PrintStream(new FileOutputStream("logs/app.log",true));
            out = new PrintStream(System.out);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return  new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .addFilter(RequestLoggingFilter.logRequestTo(out))
                .addFilter(ResponseLoggingFilter.logResponseTo(out));
    }

    public RequestSpecBuilder getBaseReqBuilder(String baseURI){
        return baseLogBuilder().setBaseUri(baseURI);
    }

    public Response hitGETAPI(RequestSpecification requestSpecification, String endpoint){
        return given()
                .spec(requestSpecification)
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
    }

    public Response hitGETAPI(RequestSpecification requestSpecification, String endpoint, Map<String,Object> queryParam){
        return given()
                .spec(requestSpecification)
                .queryParams(queryParam)
                .when()
                .get(endpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();
    }
}
