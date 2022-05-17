package dto;


import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class ApartPriceIndicesDTO implements Serializable {
    private String id;
    private String region;
    private String date;
    private String priceIndices;
}
