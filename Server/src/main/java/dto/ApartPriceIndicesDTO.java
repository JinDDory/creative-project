package dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ApartPriceIndicesDTO {
    private String apart_price_id;
    private String region;
    private String date;
    private String price_indices;
}
