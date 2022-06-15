package evening.bread.excel.mapper.member;

import evening.bread.excel.model.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {
    int selectMemberCount(@Param("email") String email) throws DataAccessException;

    List<MemberDTO> selectMemberAll() throws DataAccessException;

    void insertMember(MemberDTO memberDTO) throws DataAccessException;

}
