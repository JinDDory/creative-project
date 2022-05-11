package dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ApartTransactionInfoDTO {
    private int transaction_id;
    private String contract_y_m;
    private String contract_d;
    private int trade_amount;
    private int apart_info_id;
}
