package api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class Item {
    private Integer id;
    private String name;
    private Float price;
    private String quantity_unit;  //quantityUnit
    private Float price_for_quantity;
    private String currency;
}
