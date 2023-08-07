package api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client {
   private String name;
   private String town;
   private String address;
   private Boolean is_reg_vat;
   private String mol;
   private String bulstat;

}
