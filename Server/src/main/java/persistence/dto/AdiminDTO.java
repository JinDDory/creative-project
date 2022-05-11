package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class AdiminDTO {
    private String admin_id;
    private String admin_password;
    private String admin_name;
}
