package persistence.dao;

import dto.ApartTransactionDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.mapper.IApartTransactionMapper;
import java.util.List;
import java.util.NoSuchElementException;

public class ApartTransactionDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public ApartTransactionDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    // 아파트 거래정보 전체 조회
    public List<ApartTransactionDTO> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        List<ApartTransactionDTO> dtoList = mapper.selectAll();
        return dtoList;
    }

    // 지역별 아파트 거래정보 조회
    public List<ApartTransactionDTO> selectBySiGunGu(ApartTransactionDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        List<ApartTransactionDTO> dtoList = mapper.selectBySiGunGu(dto);
        return dtoList;
    }

    // 개인예산 이하 아파트 거래정보 조회


    // 면적별 아파트 거래정보 조회
    public List<ApartTransactionDTO> selectByArea(ApartTransactionDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        List<ApartTransactionDTO> dtoList = mapper.selectByArea(dto);
        return dtoList;
    }


    // 건축년도별 아파트 거래정보 조회
    public List<ApartTransactionDTO> selectByBuildYear(ApartTransactionDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        List<ApartTransactionDTO> dtoList = mapper.selectByBuildYear(dto);
        return dtoList;
    }

    // 층 이상 아파트 거래정보 조회
    public List<ApartTransactionDTO> selectByFloorMore(ApartTransactionDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        List<ApartTransactionDTO> dtoList = mapper.selectByFloorMore(dto);
        return dtoList;
    }

    // 층 이하 아파트 거래정보 조회
    public List<ApartTransactionDTO> selectByFloorLess(ApartTransactionDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        List<ApartTransactionDTO> dtoList = mapper.selectByFloorLess(dto);
        return dtoList;
    }

    //아파트 거래정보 등록 (모든 컬럼 입력)
    public void insertApartInfo(ApartTransactionDTO dto) throws DuplicateMemberException {
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        try {
            mapper.insertApartInfo(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // 아파트 거래정보 수정
    public void updateApartInfo(ApartTransactionDTO dto) throws NoSuchElementException {
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        try {
            mapper.updateApartInfo(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    //해당 지역 아파트 거래정보 삭제
    public void deleteApartInfoByRegion(ApartTransactionDTO dto) throws NoSuchElementException {
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        try {
            mapper.deleteApartInfoByRegion(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //해당 날짜 아파트 거래정보 삭제
    public void deleteApartInfoByBuildYear(ApartTransactionDTO dto) {
        SqlSession session = sqlSessionFactory.openSession();
        IApartTransactionMapper mapper = session.getMapper(IApartTransactionMapper.class);
        try {
            mapper.deleteApartInfoByBuildYear(dto);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
