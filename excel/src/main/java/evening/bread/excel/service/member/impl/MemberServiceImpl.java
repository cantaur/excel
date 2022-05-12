package evening.bread.excel.service.member.impl;

import evening.bread.excel.mapper.member.MemberMapper;
import evening.bread.excel.model.member.MemberDTO;
import evening.bread.excel.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper){
        this.memberMapper = memberMapper;
    }

    @Override
    public void insertMember(MemberDTO memberDTO) throws Exception {
        memberMapper.insertMember(memberDTO);

    }




}
