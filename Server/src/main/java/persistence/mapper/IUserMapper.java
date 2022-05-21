package persistence.mapper;

import dto.UserDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.sql.UserSql;

import java.util.List;
import java.util.NoSuchElementException;

public interface IUserMapper {

    @SelectProvider(type = UserSql.class, method = "selectAll")
    @Results(id = "Results", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userPassword", column = "user_password"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userEmail", column = "user_email"),
    })
    List<UserDTO> selectAll();


    @SelectProvider(type = UserSql.class, method = "selectUserById")
    UserDTO selectUserById(UserDTO dto) throws NoSuchElementException;

    @InsertProvider(type = UserSql.class, method = "insertUser")
    void insertUser(UserDTO dto) throws DuplicateMemberException;

    @UpdateProvider(type = UserSql.class, method = "updateUser")
    void updateUser(UserDTO dto);

    @DeleteProvider(type = UserSql.class, method = "deleteUser")
    void deleteUser(UserDTO dto) throws NoSuchElementException;
}
