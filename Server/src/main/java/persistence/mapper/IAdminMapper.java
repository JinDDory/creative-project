package persistence.mapper;

import dto.AdminDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.sql.AdminSql;

import java.util.List;
import java.util.NoSuchElementException;

public interface IAdminMapper {

    @SelectProvider(type = AdminSql.class, method = "selectAll")
    @Results(id = "Results", value = {
            @Result(property = "userId", column = "user_id"),
    })
    List<AdminDTO> selectAll();

    @SelectProvider(type = AdminSql.class, method = "selectAdminById")
    AdminDTO selectAdminById(AdminDTO dto);

    @InsertProvider(type = AdminSql.class, method = "insertAdmin")
    void insertAdmin(AdminDTO dto) throws DuplicateMemberException;

//    @UpdateProvider(type = AdminSql.class, method = "updateAdmin")
//    void updateAdmin(AdminDTO dto);

    @DeleteProvider(type = AdminSql.class, method = "deleteAdmin")
    void deleteAdmin(AdminDTO dto) throws NoSuchElementException;
}
