package persistence.sql;

import dto.MemberDTO;
import dto.UserDTO;
import org.apache.ibatis.jdbc.SQL;

public class UserSql {

    public static String selectAll() {
        return new SQL() {{
            SELECT("*");
            FROM("user");
            ORDER_BY("user_id", "user_name");
        }}.toString();
    }

    // 아이디로 조회
    public static String selectUserById(UserDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("user");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    //TODO 모든 컬럼이 NOTNULL인데 한 컬럼이라도 null로 들어올 경우 에러 발생하는지 안하는지 확인해보기
    public static String insertUser(UserDTO dto) {
        return new SQL() {{
            INSERT_INTO("user");
            VALUES("user_id", "#{userId}");
            VALUES("user_password", "#{userPassword}");
            VALUES("user_name", "#{userName}");
            VALUES("user_email", "#{userEmail}");
        }}.toString();
    }


    public static String updateUser(UserDTO dto) {
        return new SQL() {{
            UPDATE("user");
            // 아이디는 변경 불가
            if (dto.getUserPassword() != null)
                SET("user_password = #{userPassword}");
            if (dto.getUserName() != null)
                SET("user_name = #{userName}");
            if (dto.getUserEmail() != null)
                SET("user_email = #{userEmail}");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    public static String deleteUser(UserDTO dto) {
        return new SQL() {{
            DELETE_FROM("user");
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
