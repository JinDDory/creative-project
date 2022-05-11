package service;

import dto.MemberDTO;
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
    public void insertMember(MemberDTO dto) {
        try {
            memberDAO.insertMember(dto);
        } catch (DuplicateMemberException e) {
            System.out.println(e.getMessage());
        }
    }

    // 회원 삭제
    public void deleteMember(MemberDTO dto) {
        try {
            memberDAO.deleteMember(dto);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}
