package service;

import dto.AdminDTO;
import dto.UserDTO;
import persistence.dao.AdminDAO;

import java.util.List;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    // 전체 조회
    public List<AdminDTO> retrieveAll() {
        return adminDAO.selectAll();
    }

    // 아이디로 조회
    public AdminDTO retrieveById(AdminDTO dto) {
        return adminDAO.selectUserById(dto);
    }

    // 관리자 생성
    public void create(UserDTO userDTO) {
        AdminDTO dto = AdminDTO.builder()
                .userId(userDTO.getUserId())
                .build();

        // 관리자 데이터베이스에 생성
        adminDAO.insertAdmin(dto);
    }

    // 관리자 삭제
    public void deleteAdmin(AdminDTO dto) {
        adminDAO.deleteAdmin(dto);
    }
}
