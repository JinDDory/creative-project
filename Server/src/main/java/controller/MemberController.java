package controller;

import network.Packet;
import network.Protocol;
import service.AdminService;
import service.MemberService;
import service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MemberController {
    public static final int USER_UNDEFINED = 1;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private UserService userService;
    private MemberService memberService;


    public MemberController(ObjectInputStream in, ObjectOutputStream out,
                            UserService userService, MemberService memberService) {
        this.in = in;
        this.out = out;
        this.userService = userService;
        this.memberService = memberService;
    }

    public int handler(Packet recvPt) throws IOException {
        switch (recvPt.getCode()) {
            case Protocol.T1_CODE_CREATE:
                break;
            case Protocol.T1_CODE_READ:
                break;
            case Protocol.T1_CODE_UPDATE:
            case Protocol.T1_CODE_DELETE:
            case Protocol.T1_CODE_LOGOUT:   // 로그아웃 요청
                return logoutRequest();
        }
        return USER_UNDEFINED;
    }

    private int logoutRequest() throws IOException {
        Packet sendPt = new Packet(Protocol.TYPE_RESPONSE, Protocol.T2_CODE_SUCCESS);
        sendPt.send(out);
        return USER_UNDEFINED;
    }

    private int requestCreate(Packet packet) {
        switch (packet.getEntity()) {
            case Protocol.ENTITY_FAVORITES:   // 즐겨찾기 생성
        }
        return 0;
    }

    private int requestRead(Packet packet) {
        switch (packet.getEntity()) {
            case Protocol.ENTITY_USER:
            case Protocol.ENTITY_APART_TRANSACTION:
            case Protocol.ENTITY_PRICE_INDICES:
            case Protocol.ENTITY_FAVORITES:
        }
        return 0;

    }

    private int requestUpdate() {

        return 0;
    }

    private int requestDelete() {
        return 0;

    }


}
