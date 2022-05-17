package dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ApartTransactionDTO implements Serializable {
    private int id;
    private String siGunGu;
    private String roadName;
    private String danjiName;
    private String area;
    private String floor;
    private String buildYear;
    private String contract_y_m;
    private String contract_d;
    private int trade_amount;
}
