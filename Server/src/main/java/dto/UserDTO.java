package dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
}
