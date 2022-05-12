package service;

import dto.AdminDTO;
import dto.MemberDTO;
import dto.UserDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.dao.MemberDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class MemberService {
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    // 전체 조회
    public List<MemberDTO> selectAll() {
        return memberDAO.selectAll();
    }

    // 아이디로 조회
    public MemberDTO selectMemberById(MemberDTO dto) {
        return memberDAO.selectMemberById(dto);
    }

    // 회원 추가
    public void create(UserDTO userDTO) {
        MemberDTO dto = MemberDTO.builder()
                .userId(userDTO.getUserId())
                .build();

        // 관리자 데이터베이스에 생성
        memberDAO.insertMember(dto);
    }

    // 회원 삭제
    public void deleteMember(MemberDTO dto) {
        memberDAO.deleteMember(dto);
    }
}
