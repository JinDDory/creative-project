package persistence.sql;

import dto.MemberDTO;
import org.apache.ibatis.jdbc.SQL;

public class MemberSql {

    // 회원 전체 조회
    public static String selectAll() {
        return new SQL() {{
            SELECT("*");
            FROM("member");
            ORDER_BY("user_id");
        }}.toString();
    }

    // 회원 아이디로 조회
    public static String selectMemberById(MemberDTO dto) {
        return new SQL() {{
            SELECT("*");
            FROM("member");
            WHERE("user_id = #{userId}");
        }}.toString();
    }

    // 회원추가
    public static String insertMember(MemberDTO dto) {
        return new SQL() {{
            INSERT_INTO("member");
            VALUES("user_id", "#{userId}");
        }}.toString();
    }

    // 회원 아이디 수정 불가능

    // 회원삭제
    public static String deleteMember(MemberDTO dto) {
        return new SQL() {{
            DELETE_FROM("member");
            WHERE("user_id = #{userId}");
        }}.toString();
    }
}
