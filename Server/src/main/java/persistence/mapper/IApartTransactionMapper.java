package persistence.mapper;

import dto.ApartTransactionDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.sql.ApartTransactionSql;

import java.util.List;
import java.util.NoSuchElementException;

public interface IApartTransactionMapper {

    // 아파트 거래정보 전체 조회
    @SelectProvider(type = ApartTransactionSql.class, method = "selectAll")
    @Results(id = "Results", value = {
            @Result(property = "apartInfoId", column = "apart_info_id"),
            @Result(property = "siGunGu", column = "si_gun_gu"),
            @Result(property = "roadName", column = "road_name"),
            @Result(property = "danjiName", column = "danji_name"),
            @Result(property = "area", column = "area"),
            @Result(property = "buildYear", column = "build_year"),
            @Result(property = "floor", column = "floor")

    })
    List<ApartTransactionDTO> selectAll();

    // 지역별 아파트 거래정보 조회
    @SelectProvider(type = ApartTransactionSql.class, method = "selectBySiGunGu")
    List<ApartTransactionDTO> selectBySiGunGu(ApartTransactionDTO dto);

//    // 개인예산 이하 아파트 거래정보 조회
//    @SelectProvider(type = ApartInfoSql.class, method = "selectByAmountLess")
//    List<ApartTransactionDTO> selectByAmountLess(ApartTransactionDTO dto);

    // 면적별 아파트 거래정보 조회
    @SelectProvider(type = ApartTransactionSql.class, method = "selectByArea")
    List<ApartTransactionDTO> selectByArea(ApartTransactionDTO dto);

    // 건축년도별 아파트 거래정보 조회
    @SelectProvider(type = ApartTransactionSql.class, method = "selectByBuildYear")
    List<ApartTransactionDTO> selectByBuildYear(ApartTransactionDTO dto);

    // 층 이상 아파트 거래정보 조회
    @SelectProvider(type = ApartTransactionSql.class, method = "selectByFloorMore")
    List<ApartTransactionDTO> selectByFloorMore(ApartTransactionDTO dto);

    // 층 이하 아파트 거래정보 조회
    @SelectProvider(type = ApartTransactionSql.class, method = "selectByFloorLess")
    List<ApartTransactionDTO> selectByFloorLess(ApartTransactionDTO dto);

    //아파트 거래정보 등록 (모든 컬럼 입력)
    @InsertProvider(type = ApartTransactionSql.class, method = "insertApartInfo")
    void insertApartInfo(ApartTransactionDTO dto) throws DuplicateMemberException;

    // 아파트 거래정보 전체 수정
    @UpdateProvider(type = ApartTransactionSql.class, method = "updateApartInfo")
    void updateApartInfo(ApartTransactionDTO dto) throws NoSuchElementException;

    //해당 지역 아파트 거래정보 삭제
    @DeleteProvider(type = ApartTransactionSql.class, method = "deleteApartInfoByRegion")
    void deleteApartInfoByRegion(ApartTransactionDTO dto) throws NoSuchElementException;

    //해당 날짜 아파트 거래정보 삭제
    @DeleteProvider(type = ApartTransactionSql.class, method = "deleteApartInfoByBuildYear")
    void deleteApartInfoByBuildYear(ApartTransactionDTO dto) throws NoSuchElementException;
}
