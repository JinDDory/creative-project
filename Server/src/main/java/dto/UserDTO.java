package dto;

import lombok.Builder;
import lombok.Getter;
import java.io.Serializable;

@Getter
@Builder
public class UserDTO implements Serializable {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
}
