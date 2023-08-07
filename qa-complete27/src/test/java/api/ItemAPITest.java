package api;

import api.dto.Credentials;
import api.dto.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

@Feature("ITEM API")
public class ItemAPITest {
    private String token = "";
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void beforeEach(){
        //Obtain token
        Credentials credentials = new Credentials("karamfilovs@gmail.com", "111111", "st2016");
        LoginAPI loginAPI = new LoginAPI("");
        token = loginAPI.obtainToken(credentials);
    }

    @Test
    @Tag("api")
    @DisplayName("Can create new item")
    public void canCreateNewItem(){
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
        //Extract id
        int id = createResp.then().extract().jsonPath().get("id");
        //Get item using the id
        Response getResp = itemAPI.getItem(id);
        Assertions.assertEquals(200, getResp.statusCode());
        //Convert the response body into Java object
        Item deserializedItem = GSON.fromJson(getResp.body().asString(), Item.class);
        //Check that the fields values are the same as DTO values
        Assertions.assertEquals(item.getName(), deserializedItem.getName());
        Assertions.assertEquals(item.getPrice(), deserializedItem.getPrice());
        Assertions.assertEquals(item.getPrice_for_quantity(), deserializedItem.getPrice_for_quantity());
        Assertions.assertEquals("BGN", deserializedItem.getCurrency());
    }

    @Test
    @Tag("api")
    @DisplayName("Can get all items")
    public void canGetAllItems(){
        ItemAPI itemAPI = new ItemAPI(token);
        Response createResp = itemAPI.getItems();
        Assertions.assertEquals(200, createResp.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can get single item")
    public void canGetSingleItem(){
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response getResp = itemAPI.getItem(id);
        Assertions.assertEquals(200, getResp.statusCode());
    }

    @Test
    @Tag("api")
    @DisplayName("Can delete single item")
    public void canDeleteSingleItem(){
        ItemAPI itemAPI = new ItemAPI(token);
        Item item = Item.builder().build();
        Response createResp = itemAPI.createItem(item);
        Assertions.assertEquals(201, createResp.statusCode());
        int id = createResp.then().extract().jsonPath().getInt("id");
        Response deleteResp = itemAPI.deleteItem(id);
        Assertions.assertEquals(204, deleteResp.statusCode());
    }
}
