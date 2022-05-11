package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Apart_transaction_infoDTO {
    private int transaction_id;
    private String contract_y_m;
    private String contract_d;
    private int trade_amount;
    private int apart_info_id;
}
