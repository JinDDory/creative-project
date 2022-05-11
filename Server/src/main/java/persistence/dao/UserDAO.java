package persistence.dao;

import dto.UserDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.mapper.IUserMapper;

import java.util.List;
import java.util.NoSuchElementException;

public class UserDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public UserDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<UserDTO> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        IUserMapper mapper = session.getMapper(IUserMapper.class);
        List<UserDTO> dtoList = mapper.selectAll();
        session.close();
        return dtoList;
    }

    public UserDTO selectUserById(UserDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IUserMapper mapper = session.getMapper(IUserMapper.class);
        UserDTO userDTO = mapper.selectUserById(dto);
        session.close();
        return userDTO;
    }


    public void insertUser(UserDTO dto) throws DuplicateMemberException {
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            mapper.insertUser(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateUser(UserDTO dto){
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            mapper.updateUser(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteUser(UserDTO dto) throws NoSuchElementException{
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            IUserMapper mapper = session.getMapper(IUserMapper.class);
            mapper.deleteUser(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
