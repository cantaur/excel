package evening.bread.excel.service.member.impl;

import evening.bread.excel.mapper.member.MemberMapper;
import evening.bread.excel.model.Excel.MemberExcelDTO;
import evening.bread.excel.model.member.MemberDTO;
import evening.bread.excel.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    @Override
    public List<MemberDTO> selectMemberInfo() throws Exception {
        return memberMapper.selectMemberAll();
    }


    @Override
    public void insertMember(MemberDTO memberDTO) throws Exception {
        //db 조회해서 존재하지 않을 때 insert
        if(memberMapper.selectMemberCount(memberDTO.getEmail()) == 0){
            memberMapper.insertMember(memberDTO);
        }
    }

    @Override
    public void insertMemberList(List<MemberExcelDTO> memberExcelDTOList) throws Exception{
        //리스트의 갯수만큼 DB에 insert 시행
        for(MemberExcelDTO memberExcelDTO: memberExcelDTOList){
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setName(memberExcelDTO.getName());
            memberDTO.setEmail(memberExcelDTO.getEmail());
            this.insertMember(memberDTO);
        }
    }

}
