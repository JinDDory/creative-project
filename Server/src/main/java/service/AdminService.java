package service;

import dto.AdminDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.dao.AdminDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    // 전체 조회
    public List<AdminDTO> selectAll() {
        return adminDAO.selectAll();
    }

    // 아이디로 조회
    public AdminDTO selectAdminById(AdminDTO dto) {
        return adminDAO.selectUserById(dto);
    }

    // 회원 추가
    public void insertAdmin(AdminDTO dto) {
        try {
            adminDAO.insertAdmin(dto);
        } catch (DuplicateMemberException e) {
            System.out.println(e.getMessage());
        }
    }

    // 회원 삭제
    public void deleteAdmin(AdminDTO dto) {
        try {
            adminDAO.deleteAdmin(dto);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
