package dto;

import lombok.Builder;
import lombok.Getter;
import java.io.Serializable;

@Getter
@Builder
public class AdminDTO implements Serializable {
    private String userId;
}
