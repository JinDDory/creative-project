package persistence.dao;

import dto.AdminDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.mapper.IAdminMapper;

import java.util.List;
import java.util.NoSuchElementException;

public class AdminDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public AdminDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    // 전체 조회
    public List<AdminDTO> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        IAdminMapper mapper = session.getMapper(IAdminMapper.class);
        List<AdminDTO> dtoList = mapper.selectAll();
        session.close();
        return dtoList;
    }

    // 아이디로 조회
    public AdminDTO selectUserById(AdminDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IAdminMapper mapper = session.getMapper(IAdminMapper.class);
        AdminDTO adminDTO = mapper.selectAdminById(dto);
        session.close();
        return adminDTO;
    }

    // 관리자 생성
    public void insertAdmin(AdminDTO dto) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IAdminMapper mapper = session.getMapper(IAdminMapper.class);
            mapper.insertAdmin(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // 관리자 삭제
    public void deleteAdmin(AdminDTO dto) {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IAdminMapper mapper = session.getMapper(IAdminMapper.class);
            mapper.deleteAdmin(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
