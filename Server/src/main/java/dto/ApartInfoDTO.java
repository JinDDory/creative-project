package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
public class ApartInfoDTO {
    private int apartInfoId;
    private String siGunGu;
    private String roadName;
    private String danjiName;
    private String area;
    private String floor;
    private String buildYear;
}
