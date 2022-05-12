package controller;

import dto.UserDTO;
import network.Protocol;

public class LoginController {

    public loginRequest(Protocol recvPt) throws Exception {
        System.out.println("loginRequest");
        Protocol sendPt = new Protocol(Protocol.TYPE_RESPONSE);
        UserDTO recvUserDTO = null; // 받은 패킷에서 객체 꺼내옴

        UserDTO user = userService.login(recvUserDTO);
        // 로그인 성공
        sendPt.setCode(Protocol.T2_CODE_SUCCESS);
        UserDTOp[] dtos;
        sendPt.setObject(user);
        sendPt.send(out);

        if ()

    }

}
