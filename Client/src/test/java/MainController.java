import dto.UserDTO;
import network.Packet;
import network.Protocol;

import java.io.IOException;

public class MainController {
    public static void main(String[] args) {
        Packet sendPt = new Packet(Protocol.TYPE_REQUEST, Protocol.T1_CODE_LOGIN);
        UserDTO userDTO = UserDTO.builder()
                .userName("leb001122")
                .userPassword("dmsqls")
                .build();
        System.out.println(userDTO.toString());


    }
}
