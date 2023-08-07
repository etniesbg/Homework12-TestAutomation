package api.dto;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;

//Data Transfer Object (DTO)
//Plain Old Java Object (POJO)
@AllArgsConstructor
public class Credentials {
    private String email;
    private String password;
    private String domain;


    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Credentials myCredentials = new Credentials("karamfilovs@gmail.com", "111111", "st2016");
        //Convert java object to json
        String credentialsJson = gson.toJson(myCredentials);
        System.out.println("Serialized object:");
        System.out.println(credentialsJson);
        //Convert json to java object
        Credentials deserializedCredentials = gson.fromJson(credentialsJson, Credentials.class);
        System.out.println("Deserialized fields:");
        System.out.println(deserializedCredentials.email);
        System.out.println(deserializedCredentials.password);
        System.out.println(deserializedCredentials.domain);
    }

}