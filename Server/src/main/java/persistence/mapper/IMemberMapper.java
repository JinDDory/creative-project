package persistence.mapper;

import dto.MemberDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import persistence.sql.MemberSql;

import java.util.List;
import java.util.NoSuchElementException;

public interface IMemberMapper {


    @SelectProvider(type = MemberSql.class, method = "selectAll")
    @Results(id = "Results", value = {
            @Result(property = "userId", column = "user_id"),
    })
    List<MemberDTO> selectAll();

    @SelectProvider(type = MemberSql.class, method = "selectMemberById")
    MemberDTO selectMemberById(MemberDTO dto);

    @InsertProvider(type = MemberSql.class, method = "insertMember")
    void insertMember(MemberDTO dto) throws DuplicateMemberException;

//    @UpdateProvider(type = MemberSql.class, method = "updateMember")
//    void updateMember(MemberDTO dto);

    @DeleteProvider(type = MemberSql.class, method = "deleteMember")
    void deleteMember(MemberDTO dto) throws NoSuchElementException;
}
