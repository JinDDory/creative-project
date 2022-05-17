package controller;

import dto.MessageDTO;
import dto.UserDTO;
import network.Header;
import network.Protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainController {
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public MainController(ObjectInputStream in, ObjectOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public void run() {
        Header sendPt = new Header(Protocol.TYPE_REQUEST, Protocol.T1_CODE_LOGIN);
        UserDTO userDTO = UserDTO.builder()
                        .userName("leb001122")
                        .userPassword("dmsqls")
                        .build();
        System.out.println(userDTO.toString());

        try {
            ArrayList<Object> list = new ArrayList<>();
            list.add(sendPt);
            list.add(userDTO);
            out.writeObject(list);

            list.clear();
            list = (ArrayList<Object>) in.readObject();
            Header recvPt = (Header) list.get(0);

            if (recvPt.getType() == Protocol.TYPE_RESPONSE) {
                if (recvPt.getCode() == Protocol.T2_CODE_SUCCESS) {
                    System.out.println("로그인 성공");
                    UserDTO user = (UserDTO) list.get(1);
                    System.out.println(user.toString());
                } else {
                    System.out.println("로그인 실패");
                    MessageDTO message = (MessageDTO) list.get(1);
                    System.out.println(message.getMessage());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
