package evening.bread.excel.service.member;

import evening.bread.excel.model.Excel.MemberExcelDTO;
import evening.bread.excel.model.member.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> selectMemberInfo() throws Exception;

    void insertMember(MemberDTO memberDTO) throws Exception;

    void insertMemberList(List<MemberExcelDTO> memberExcelDTOList) throws Exception;
}
