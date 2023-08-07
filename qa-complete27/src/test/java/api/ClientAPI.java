package api;

import api.dto.Client;
import io.restassured.response.Response;

import java.util.List;

public class ClientAPI extends Request {
    private static final String ENDPOINT = "/clients";

    public ClientAPI(String token) {
        super(token);
    }

    public Response getClient(String id){
        return get(ENDPOINT + "/" + id);
    }

    public List<Client> getClientsList(){
        Response response = get(ENDPOINT);
        String body = response.body().asString();
        return List.of(GSON.fromJson(body, Client[].class));
    }

    public Response getClients(){
        return get(ENDPOINT);
    }

    public Response deleteClient(String id){
        return delete(ENDPOINT + "/" + id);
    }

    public Response createClient(Client client){
        String body = GSON.toJson(client);
        return post(ENDPOINT, body);
    }

    public Response updateClient(int id, Client client){
        String body = GSON.toJson(client);
        return patch(ENDPOINT, body);
    }
}
