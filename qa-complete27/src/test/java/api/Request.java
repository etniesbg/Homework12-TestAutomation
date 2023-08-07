package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request {
    private String token;
    private String BASE_URL = System.getProperty("baseUrl", "https://api.inv.bg");
    private String BASE_PATH = System.getProperty("basePath", "v3");
    private String USER_AGENT = "My User Agent";
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Request(String token){
        this.token = token;
    }

    private RequestSpecification baseRequest(){
        return RestAssured.given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL) //Sets the base uri for the request
                .basePath(BASE_PATH) //Sets the base path for the request
                .header("User-Agent", USER_AGENT)
                .accept(ContentType.JSON) //Sets Accept header
                .contentType(ContentType.JSON);
    }

    protected Response get(String endpoint){
        return baseRequest().get(endpoint).prettyPeek();
    }

    protected Response post(String endpoint, String body){
        return baseRequest().body(body).post(endpoint).prettyPeek();
    }

    protected Response delete(String endpoint){
        return baseRequest().delete(endpoint).prettyPeek();
    }

    protected Response patch(String endpoint, String body){
        return baseRequest().body(body).post(endpoint).prettyPeek();
    }
}
