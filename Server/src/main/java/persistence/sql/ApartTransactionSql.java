package persistence.sql;

import dto.ApartTransactionDTO;
import org.apache.ibatis.jdbc.SQL;

public class ApartTransactionSql {
    final static int DEFAULT_VALUE = 0;

    // 아파트 거래정보 전체 조회
    public static String selectAll() {
        return new SQL() {{
            SELECT("*");
            FROM("apart_info");
            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
        }}.toString();
    }

    // 지역별 아파트 거래정보 조회
    public static String selectBySiGunGu(ApartTransactionDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("Apart_info");
            WHERE("siGunGu like CONCAT('%',#{siGunGu},'%')");
            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
        }}.toString();
    }

//    // 개인예산 이하 아파트 거래정보 조회
//    public static String selectByAmountLess(ApartTransactionDTO dto) {
//        return new SQL() {{
//            SELECT("*");
//            FROM("Apart_info");
//            WHERE("apart_info.tradeAmount <= #{favorites.tradeAmount}");
//            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
//        }}.toString();
//    }

    // 면적별 아파트 거래정보 조회 (이상)
    public static String selectByArea(ApartTransactionDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("Apart_info");
            WHERE("area >= #{area}");
            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
        }}.toString();
    }

    // 건축년도별 아파트 거래정보 조회 (이상)
    public static String selectByBuildYear(ApartTransactionDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("Apart_info");
            WHERE("buildYear >= #{buildYear}");
            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
        }}.toString();
    }

    // 해당 층 이상 아파트 거래정보 조회
    public static String selectByFloorMore(ApartTransactionDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("Apart_info");
            WHERE("floor >= #{floor}");
            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
        }}.toString();
    }

    // 해당 층 이하 아파트 거래정보 조회
    public static String selectByFloorLess(ApartTransactionDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("Apart_info");
            WHERE("floor <= #{floor}");
            //ORDER_BY("siGunGu", "roadName", "danjiName", "area", "floor", "buildYear");
        }}.toString();
    }

    //아파트 거래정보 등록 (모든 컬럼 입력)
    public static String insertApartInfo(ApartTransactionDTO dto) {
        return new SQL() {{
            INSERT_INTO("Apart_info");
            VALUES("apartInfoId", "#{apartInfoId}");
            VALUES("siGunGu", "#{siGunGu}");
            VALUES("roadName", "#{roadName}");
            VALUES("danjiName", "#{danjiName}");
            VALUES("area", "#{area}");
            VALUES("floor", "#{floor}");
            VALUES("buildYear", "#{buildYear}");
        }}.toString();
    }

    // 아파트 거래정보 수정
    public static String updateApartInfo(ApartTransactionDTO dto) {
        return new SQL() {{
            UPDATE("Apart_info");
            if (dto.getSiGunGu() != null)
                SET("siGunGu = #{siGunGu}");
            if (dto.getRoadName() != null)
                SET("roadName = #{roadName}");
            if (dto.getDanjiName() != null)
                SET("danjiName = #{danjiName}");
            if (dto.getArea() != null)
                SET("area = #{area}");
            if (dto.getFloor() != null)
                SET("floor = #{floor}");
            if (dto.getBuildYear() != null)
                SET("buildYear = #{buildYear}");
            WHERE("apartInfoId = #{apartInfoId}");
        }}.toString();
    }

    //해당 지역 아파트 거래정보 삭제
    public static String deleteApartInfoByRegion(ApartTransactionDTO dto) {
        return new SQL() {{
            DELETE_FROM("Apart_info");
            WHERE("siGunGu like CONCAT('%',#{siGunGu},'%')");
        }}.toString();
    }

    //해당 날짜 아파트 거래정보 삭제
    public static String deleteApartInfoByBuildYear(ApartTransactionDTO dto) {
        return new SQL() {{
            DELETE_FROM("Apart_info");
            WHERE("buildYear like CONCAT('%',#{buildYear},'%')");
        }}.toString();
    }
}

