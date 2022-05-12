package persistence.dao;

import dto.MemberDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.mapper.IMemberMapper;

import java.util.List;
import java.util.NoSuchElementException;

public class MemberDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MemberDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<MemberDTO> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        IMemberMapper mapper = session.getMapper(IMemberMapper.class);
        List<MemberDTO> dtoList = mapper.selectAll();
        session.close();
        return dtoList;
    }

    public MemberDTO selectMemberById(MemberDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IMemberMapper mapper = session.getMapper(IMemberMapper.class);
        MemberDTO memberDTO = mapper.selectMemberById(dto);
        session.close();
        return memberDTO;
    }


    public void insertMember(MemberDTO dto)  {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IMemberMapper mapper = session.getMapper(IMemberMapper.class);
            mapper.insertMember(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    public void updateMember(MemberDTO dto){
//        SqlSession session = null;
//        try {
//            session = sqlSessionFactory.openSession();
//            IMemberMapper mapper = session.getMapper(IMemberMapper.class);
//            mapper.updateMember(dto);
//            session.commit();
//        } catch (Exception e) {
//            session.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }

    public void deleteMember(MemberDTO dto) throws NoSuchElementException {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IMemberMapper mapper = session.getMapper(IMemberMapper.class);
            mapper.deleteMember(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
