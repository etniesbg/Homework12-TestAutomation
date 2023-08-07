package api;

import api.dto.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class LoginAPI extends Request {
    private static final String ENDPOINT = "/login/token";

    public LoginAPI(String token) {
        super(token);
    }

    public String obtainToken(Credentials credentials) {
        String body = GSON.toJson(credentials);
        return post(ENDPOINT, body)
                .then().extract()
                .body().jsonPath()
                .getString("token"); //Extract the token from the response body
    }

}
