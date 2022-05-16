package controller;

import network.Packet;
import network.Protocol;
import network.Server;
import persistence.MyBatisConnectionFactory;
import persistence.dao.AdminDAO;
import persistence.dao.MemberDAO;
import persistence.dao.UserDAO;
import service.AdminService;
import service.MemberService;
import service.UserService;

import java.io.*;
import java.net.Socket;

public class MainController extends Thread{

    // user 구분
    public static final int USER_UNDEFINED = 1;
    public static final int USER_MEMBER = 2;
    public static final int USER_ADMIN = 3;
    private int userType;

    private int clientID;
    private Socket socket;
    private ObjectInputStream is;
    private ObjectOutputStream out;

    private boolean running;

    private final UserService userService;
    private final AdminService adminService;
    private final MemberService memberService;
    private final UserDAO userDAO;
    private final AdminDAO adminDAO;
    private final MemberDAO memberDAO;
    private LoginController loginController;

    {
        userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        adminDAO = new AdminDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        memberDAO = new MemberDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        adminService = new AdminService(adminDAO);
        memberService = new MemberService(memberDAO);
        userService = new UserService(userDAO, adminService, memberService);
        loginController = new LoginController(is, out, userService, adminService, memberService);

    }

    public MainController(Socket socket){
        this.socket = socket;
        clientID = socket.getPort();
        try {
            is = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        running = true;
        Packet packet = new Packet();
        System.out.println("main controller entry");
        while (running) {
            try {
                handler(packet.read(is));
            } catch (Exception e) {
                e.printStackTrace();
                exit(); // 스레드 종료
            }
        }
        System.out.println("스레드 종료");
    }

    public int getClientID() {
        return clientID;
    }

    public void handler(Packet recvPt) throws IOException {
        System.out.println("handler entry");

        // 로그인하기 전
        switch (userType) {
            case USER_UNDEFINED:
                loginController.handler(recvPt);
                break;
        }
    }


    // 소켓 종료 및 스레드 종료
    private void exit() {
        Server.removeThread(clientID);
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        running = false;
    }

}
