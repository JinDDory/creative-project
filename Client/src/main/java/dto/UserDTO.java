package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Builder
@ToString
public class UserDTO implements Serializable {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
}
