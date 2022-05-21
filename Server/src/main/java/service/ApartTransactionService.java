package service;

import dto.ApartTransactionDTO;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.dao.ApartTransactionDAO;

import java.util.List;
import java.util.NoSuchElementException;

public class ApartTransactionService {
    private final ApartTransactionDAO dao;

    public ApartTransactionService(ApartTransactionDAO dao) {
        this.dao = dao;
    }

    // 아파트 거래정보 전체 조회
    public List<ApartTransactionDTO> selectAll() {
        return dao.selectAll();
    }

    // 지역별 아파트 거래정보 조회
    public List<ApartTransactionDTO> selectBySiGunGu(ApartTransactionDTO dto) {
        return dao.selectBySiGunGu(dto);
    }

    // 아파트 거래정보 등록
    public void insertApartInfo(ApartTransactionDTO dto) {
        try {
            dao.insertApartInfo(dto);
        } catch (DuplicateMemberException e) {
            e.printStackTrace();
        }
    }

    // 아파트 거래정보 수정
    public void updateApartInfo(ApartTransactionDTO dto) {
        try {
            dao.updateApartInfo(dto);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    //해당 지역 아파트 거래정보 삭제
    public void deleteApartInfoByRegion(ApartTransactionDTO dto) {
        try {
            dao.deleteApartInfoByRegion(dto);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
}
