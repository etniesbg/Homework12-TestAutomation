package api;

import api.dto.Client;
import api.dto.Credentials;
import api.dto.Item;
import api.dto.ItemList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class ItemAPI extends Request {
    private static final String ENDPOINT = "/items";

    public ItemAPI (String token){
        super(token);
    }

    /**
     * Create items
     * @param item item info
     * @return Response
     */
    public Response createItem(Item item){
        String body = GSON.toJson(item);
        return post(ENDPOINT, body);
    }

    /**
     * Deletes item by id
     * @param id id
     * @return Response
     */
    public Response deleteItem(int id){
        return delete(ENDPOINT + "/" + id);
    }

    public Response getItem(int id){
        return get(ENDPOINT + "/" + id);
    }

    /**
     * Updates existing item
     * @param item dto of the item
     * @return Response
     */
    public Response updateItem(Item item){
        return patch(ENDPOINT, GSON.toJson(item));
    }

    public Response getItems(){
        return get(ENDPOINT);
    }

    public static void main(String[] args) {
        ItemAPI itemAPI = new ItemAPI("");
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
    }

    public void deleteAll() {
        String body = getItems().asString();
        ItemList items =  GSON.fromJson(body, ItemList.class);
        items.getItems().forEach(item -> deleteItem(item.getId()));
    }


}
