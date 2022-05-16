package controller;

import com.sun.tools.javac.Main;
import dto.AdminDTO;
import dto.MemberDTO;
import dto.MessageDTO;
import dto.UserDTO;
import exception.NoSuchUserException;
import network.Packet;
import network.Protocol;
import service.AdminService;
import service.MemberService;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginController {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private UserService userService;
    private AdminService adminService;
    private MemberService memberService;

    public LoginController(ObjectInputStream in, ObjectOutputStream out, UserService userService,
                           AdminService adminService, MemberService memberService) {
        this.in = in;
        this.out = out;
        this.userService = userService;
        this.adminService = adminService;
        this.memberService = memberService;
    }

    public void handler(Packet recvPt) throws IOException {
        switch (recvPt.getCode()) {
            case Protocol.T1_CODE_LOGIN:
                loginRequest(recvPt);
                break;
            case Protocol.T1_CODE_LOGOUT:
                break;
        }
    }

    private int loginRequest(Packet recvPt) throws IOException {
        System.out.println("loginRequest");
        Packet sendPt = new Packet(Protocol.TYPE_RESPONSE);
        // 받은 패킷에서 객체 꺼내옴
        UserDTO recvUserDTO = (UserDTO) recvPt.getBody().get(0);

        UserDTO user = null;
        try {
            user = userService.login(recvUserDTO);
        } catch (NoSuchUserException e) {
            System.out.println(e.getMessage());
            sendPt.setCode(Protocol.T2_CODE_FAIL);
            sendPt.setBody(new MessageDTO(e.getMessage()));
            sendPt.send(out);
        }

        // 로그인 성공
        sendPt.setCode(Protocol.T2_CODE_SUCCESS);
        sendPt.setBody(user);
        sendPt.send(out);

        AdminDTO adminDTO = AdminDTO.builder().userId(user.getUserId()).build();
        if (adminService.retrieveById(adminDTO) != null) {
            // 관리자
            return MainController.USER_ADMIN;
        } else {
            // 회원
            return MainController.USER_MEMBER;
        }
    }

    private void logoutRequest(Packet recvPt) {

    }

}
