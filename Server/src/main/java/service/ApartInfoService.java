package service;

import dto.ApartInfoDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.dao.ApartInfoDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class ApartInfoService {
    private final ApartInfoDAO dao;

    public ApartInfoService(ApartInfoDAO dao) {
        this.dao = dao;
    }

    // 아파트 거래정보 전체 조회
    public List<ApartInfoDTO> selectAll() {
        return dao.selectAll();
    }

    // 지역별 아파트 거래정보 조회
    public List<ApartInfoDTO> selectBySiGunGu(ApartInfoDTO dto) {
        return dao.selectBySiGunGu(dto);
    }

    // 아파트 거래정보 등록
    public void insertApartInfo(ApartInfoDTO dto) {
        try {
            dao.insertApartInfo(dto);
        } catch (DuplicateMemberException e) {
            e.printStackTrace();
        }
    }

    // 아파트 거래정보 수정
    public void updateApartInfo(ApartInfoDTO dto) {
        try {
            dao.updateApartInfo(dto);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    //해당 지역 아파트 거래정보 삭제
    public void deleteApartInfoByRegion(ApartInfoDTO dto) {
        try {
            dao.deleteApartInfoByRegion(dto);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
