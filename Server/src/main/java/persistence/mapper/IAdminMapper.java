package persistence.mapper;

import dto.AdminDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.sql.AdminSql;

import java.util.List;
import java.util.NoSuchElementException;

public interface IAdminMapper {

    @Results(id = "Results", value = {
            @Result(property = "userId", column = "user_id"),
    })

    // 전체조회
    @SelectProvider(type = AdminSql.class, method = "selectAll")
    List<AdminDTO> selectAll();

    // 아이디로 조회
    @SelectProvider(type = AdminSql.class, method = "selectAdminById")
    AdminDTO selectAdminById(AdminDTO dto);

    // 관리자 생성
    @InsertProvider(type = AdminSql.class, method = "insertAdmin")
    void insertAdmin(AdminDTO dto) throws Exception;

    // 관리자 삭제
    @DeleteProvider(type = AdminSql.class, method = "deleteAdmin")
    void deleteAdmin(AdminDTO dto) throws Exception;
}
