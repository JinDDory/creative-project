package service;

import dto.MemberDTO;
import dto.UserDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.dao.UserDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // 전체 조회
    public List<UserDTO> selectAll() {
        return userDAO.selectAll();
    }

    // 아이디로 조회
    public UserDTO selectUserById(UserDTO dto) {
        return userDAO.selectUserById(dto);
    }

    // 회원가입
    public void insertUser(UserDTO dto) {
        try {
            userDAO.insertUser(dto);
        } catch (DuplicateMemberException e) {
            System.out.println(e.getMessage());
        }
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
