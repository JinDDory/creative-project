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

    public List<AdminDTO> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        IAdminMapper mapper = session.getMapper(IAdminMapper.class);
        List<AdminDTO> dtoList = mapper.selectAll();
        session.close();
        return dtoList;
    }

    public AdminDTO selectUserById(AdminDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IAdminMapper mapper = session.getMapper(IAdminMapper.class);
        AdminDTO adminDTO = mapper.selectAdminById(dto);
        session.close();
        return adminDTO;
    }


    public void insertAdmin(AdminDTO dto) throws DuplicateMemberException {
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

//    public void updateAdmin(AdminDTO dto){
//        SqlSession session = null;
//        try {
//            session = sqlSessionFactory.openSession();
//            IAdminMapper mapper = session.getMapper(IAdminMapper.class);
//            mapper.updateAdmin(dto);
//            session.commit();
//        } catch (Exception e) {
//            session.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }

    public void deleteAdmin(AdminDTO dto) throws NoSuchElementException {
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
