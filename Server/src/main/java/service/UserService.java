package service;

import dto.UserDTO;
import exception.NoSuchUserException;
import persistence.dao.UserDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class UserService {
    private final UserDAO userDAO;
    private final AdminService adminService;
    private final MemberService memberService;

    public UserService(UserDAO userDAO, AdminService adminService, MemberService memberService) {
        this.userDAO = userDAO;
        this.adminService = adminService;
        this.memberService = memberService;
    }

    // 로그인
    public UserDTO login(UserDTO recvDTO) throws NoSuchUserException {
        // 데이터베이스에서 해당 아이디로 사용자정보를 가져옴
        UserDTO userDTO = retrieveById(recvDTO.getUserId());
        // 비밀번호 일치 여부 확인
        if (userDTO.getUserPassword() != recvDTO.getUserPassword()) {
            throw new NoSuchUserException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 해당 사용자 정보 반환
        return userDTO;
    }

    // 전체 조회
    public List<UserDTO> retrieveAll() {
        return userDAO.selectAll();
    }

    // 아이디로 조회
    public UserDTO retrieveById(String id) {
        // 아이디를 받아 UserDTO로 변환
        UserDTO dto = UserDTO.builder()
                .userId(id)
                .build();
        return userDAO.selectUserById(dto);
    }

    // 관리자 회원가입
    public void createAdmin(UserDTO dto) {
        // 사용자 데이터베이스에 생성
        userDAO.insertUser(dto);
        // 관리자 데이터베이스에 생성
        adminService.create(dto);
    }

    // 멤버 회원가입
    public void createMember(UserDTO dto) {
        // 사용자 데이터베이스에 생성
        userDAO.insertUser(dto);
        // 멤버 데이터베이스에 생성
        memberService.create(dto);

    }

    // 회원정보 수정
    public void updateUser(UserDTO dto) {
        userDAO.updateUser(dto);
    }

    // 회원탈퇴
    public void deleteUser(UserDTO dto) {
        try {
            userDAO.deleteUser(dto);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
