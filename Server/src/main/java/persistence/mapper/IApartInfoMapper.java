package persistence.mapper;

import dto.ApartInfoDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.sql.ApartInfoSql;
import persistence.sql.UserSql;

import java.util.List;
import java.util.NoSuchElementException;

public interface IApartInfoMapper {

    // 아파트 거래정보 전체 조회
    @SelectProvider(type = ApartInfoSql.class, method = "selectAll")
    @Results(id = "Results", value = {
            @Result(property = "apartInfoId", column = "apart_info_id"),
            @Result(property = "siGunGu", column = "si_gun_gu"),
            @Result(property = "roadName", column = "road_name"),
            @Result(property = "danjiName", column = "danji_name"),
            @Result(property = "area", column = "area"),
            @Result(property = "buildYear", column = "build_year"),
            @Result(property = "floor", column = "floor")

    })
    List<ApartInfoDTO> selectAll();

    // 지역별 아파트 거래정보 조회
    @SelectProvider(type = ApartInfoSql.class, method = "selectBySiGunGu")
    List<ApartInfoDTO> selectBySiGunGu(ApartInfoDTO dto);

//    // 개인예산 이하 아파트 거래정보 조회
//    @SelectProvider(type = ApartInfoSql.class, method = "selectByAmountLess")
//    List<ApartInfoDTO> selectByAmountLess(ApartInfoDTO dto);

    // 면적별 아파트 거래정보 조회
    @SelectProvider(type = ApartInfoSql.class, method = "selectByArea")
    List<ApartInfoDTO> selectByArea(ApartInfoDTO dto);

    // 건축년도별 아파트 거래정보 조회
    @SelectProvider(type = ApartInfoSql.class, method = "selectByBuildYear")
    List<ApartInfoDTO> selectByBuildYear(ApartInfoDTO dto);

    // 층 이상 아파트 거래정보 조회
    @SelectProvider(type = ApartInfoSql.class, method = "selectByFloorMore")
    List<ApartInfoDTO> selectByFloorMore(ApartInfoDTO dto);

    // 층 이하 아파트 거래정보 조회
    @SelectProvider(type = ApartInfoSql.class, method = "selectByFloorLess")
    List<ApartInfoDTO> selectByFloorLess(ApartInfoDTO dto);

    //아파트 거래정보 등록 (모든 컬럼 입력)
    @InsertProvider(type = ApartInfoSql.class, method = "insertApartInfo")
    void insertApartInfo(ApartInfoDTO dto) throws DuplicateMemberException;

    // 아파트 거래정보 전체 수정
    @UpdateProvider(type = ApartInfoSql.class, method = "updateApartInfo")
    void updateApartInfo(ApartInfoDTO dto) throws NoSuchElementException;

    //해당 지역 아파트 거래정보 삭제
    @DeleteProvider(type = ApartInfoSql.class, method = "deleteApartInfoByRegion")
    void deleteApartInfoByRegion(ApartInfoDTO dto) throws NoSuchElementException;

    //해당 날짜 아파트 거래정보 삭제
    @DeleteProvider(type = ApartInfoSql.class, method = "deleteApartInfoByBuildYear")
    void deleteApartInfoByBuildYear(ApartInfoDTO dto) throws NoSuchElementException;
}
