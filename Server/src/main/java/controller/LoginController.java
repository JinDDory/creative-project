package controller;

import dto.AdminDTO;
import dto.MessageDTO;
import dto.UserDTO;
import exception.NoSuchUserException;
import network.Header;
import network.Protocol;
import service.AdminService;
import service.MemberService;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginController {
    public static final int USER_UNDEFINED = 1;
    public static final int USER_MEMBER = 2;
    public static final int USER_ADMIN = 3;

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

    public int handler(ArrayList<Object> objectArrayList) throws IOException {
        System.out.println("loginController entry");
        Header header = (Header) objectArrayList.get(0);

        switch (header.getCode()) {
            case Protocol.T1_CODE_LOGIN:  // 로그인 요청
                return loginRequest(objectArrayList);
            case Protocol.T1_CODE_CREATE:   // 계정 생성 요청
                break;
                 // 아이디 찾기
              // 비밀번호 찾기
        }
        return USER_UNDEFINED;
    }

    // 로그인 요청
    private int loginRequest(ArrayList<Object> objectArrayList) throws IOException {
        System.out.println("loginRequest");
        ArrayList<Object> sendPt = null;

        Header header = new Header(Protocol.TYPE_RESPONSE);
        // 받은 패킷에서 객체 꺼내옴
        UserDTO recvUserDTO = (UserDTO) objectArrayList.get(1);

        UserDTO user = null;
        try {
            user = userService.login(recvUserDTO);
        } catch (NoSuchUserException e) {
            System.out.println(e.getMessage());
            header.setCode(Protocol.T2_CODE_FAIL);
            sendPt.add(header);
            sendPt.add(new MessageDTO(e.getMessage()));
            out.writeObject(sendPt);
        }

        // 로그인 성공
        header.setCode(Protocol.T2_CODE_SUCCESS);
        sendPt.add(header);
        sendPt.add(recvUserDTO);
        out.writeObject(sendPt);

        AdminDTO adminDTO = AdminDTO.builder().userId(user.getUserId()).build();
        if (adminService.retrieveById(adminDTO) != null) {
            // 관리자
            return USER_ADMIN;
        } else {
            // 회원
            return USER_MEMBER;
        }
    }

    // 계정 생성 요청
//    private void createUser(Header rectPt) throws Exception {
//        Header sendPt = new Header(Protocol.TYPE_RESPONSE);
//
//        try {
//            if (rectPt.getEntity() == Protocol.ENTITY_ADMIN) { // 관리자 계정생성
//                userService.createAdmin((UserDTO) rectPt.getBody().get(0));
//            } else if (rectPt.getEntity() == Protocol.ENTITY_MEMBER) { // 회원 계정생성
//                userService.createMember((UserDTO) rectPt.getBody().get(0));
//            }
//            sendPt.setCode(Protocol.T2_CODE_SUCCESS);
//            sendPt.send(out);
//        } catch (Exception e) {
//            // 계정생성 실패 시 예외처리
//            sendPt.setCode(Protocol.T2_CODE_FAIL);
//            sendPt.send(out);
//        }
//    }


}
