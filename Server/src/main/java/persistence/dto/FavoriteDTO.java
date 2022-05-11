package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class FavoriteDTO {
    private String user_id;
    private int transaction_id;
}
