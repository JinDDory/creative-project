package dto;

import lombok.Builder;
import lombok.Getter;
import java.io.Serializable;

@Getter
@Builder
public class FavoritesDTO implements Serializable {
    private String userId;
    private int apartTransactionId;
}
