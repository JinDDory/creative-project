package persistence.sql;

import dto.AdminDTO;
import dto.MemberDTO;
import org.apache.ibatis.jdbc.SQL;

public class AdminSql {

    // 전체 조회
    public static String selectAll() {
        return new SQL() {{
            SELECT("*");
            FROM("admin");
            ORDER_BY("user_id");
        }}.toString();
    }

    // 아이디로 조회
    public static String selectAdminById(AdminDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("admin");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    // 관리자 추가
    public static String insertAdmin(AdminDTO dto) {
        return new SQL() {{
            INSERT_INTO("admin");
            VALUES("user_id", "#{userId}");
        }}.toString();
    }

    // 관리자 삭제
    public static String deleteAdmin(AdminDTO dto) {
        return new SQL() {{
            DELETE_FROM("admin");
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
