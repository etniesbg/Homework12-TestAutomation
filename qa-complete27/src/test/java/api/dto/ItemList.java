package api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItemList {
    Integer total;
    String next_page;
    String prev_page;
    List<Item> items;
}
