package api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BankAccount {
    private String bank_en;
    private String alias;
    private String bank;
    private String iban;
    private String bic;
    private String currency;
    private String address;
    private Boolean isDefault;

}
