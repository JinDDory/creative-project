package persistence.dao;

import dto.ApartInfoDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.mapper.IApartInfoMapper;
import java.util.List;
import java.util.NoSuchElementException;

public class ApartInfoDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public ApartInfoDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    // 아파트 거래정보 전체 조회
    public List<ApartInfoDTO> selectAll(){
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
        List<ApartInfoDTO> dtoList = mapper.selectAll();
        return dtoList;
    }

    // 지역별 아파트 거래정보 조회
    public List<ApartInfoDTO> selectBySiGunGu(ApartInfoDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
        List<ApartInfoDTO> dtoList = mapper.selectBySiGunGu(dto);
        return dtoList;
    }

    // 개인예산 이하 아파트 거래정보 조회


    // 면적별 아파트 거래정보 조회
    public List<ApartInfoDTO> selectByArea(ApartInfoDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
        List<ApartInfoDTO> dtoList = mapper.selectByArea(dto);
        return dtoList;
    }


    // 건축년도별 아파트 거래정보 조회
    public List<ApartInfoDTO> selectByBuildYear(ApartInfoDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
        List<ApartInfoDTO> dtoList = mapper.selectByBuildYear(dto);
        return dtoList;
    }

    // 층 이상 아파트 거래정보 조회
    public List<ApartInfoDTO> selectByFloorMore(ApartInfoDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
        List<ApartInfoDTO> dtoList = mapper.selectByFloorMore(dto);
        return dtoList;
    }

    // 층 이하 아파트 거래정보 조회
    public List<ApartInfoDTO> selectByFloorLess(ApartInfoDTO dto){
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
        List<ApartInfoDTO> dtoList = mapper.selectByFloorLess(dto);
        return dtoList;
    }

    //아파트 거래정보 등록 (모든 컬럼 입력)
    public void insertApartInfo(ApartInfoDTO dto) throws DuplicateMemberException {
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
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
    public void updateApartInfo(ApartInfoDTO dto) throws NoSuchElementException {
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
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
    public void deleteApartInfoByRegion(ApartInfoDTO dto) throws NoSuchElementException {
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
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
    public void deleteApartInfoByBuildYear(ApartInfoDTO dto) {
        SqlSession session = sqlSessionFactory.openSession();
        IApartInfoMapper mapper = session.getMapper(IApartInfoMapper.class);
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
