package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class UserDTO {
    private String user_id;
    private String user_password;
    private String user_name;
    private String user_email;
}
