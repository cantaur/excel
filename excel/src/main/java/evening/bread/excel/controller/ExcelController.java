package evening.bread.excel.controller;

import evening.bread.excel.model.Excel.MemberExcelDTO;
import evening.bread.excel.model.member.MemberDTO;
import evening.bread.excel.service.excel.ExcelService;
import evening.bread.excel.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
public class ExcelController {

    ExcelService excelService;
    MemberService memberService;

    @Autowired
    public ExcelController(MemberService memberService,
                           ExcelService excelService){
        this.memberService = memberService;
        this.excelService = excelService;
    }




    @PostMapping("/excel/upload")
    public List<MemberExcelDTO> readExcel(@RequestParam("file") MultipartFile file)
            throws Exception {

        List<MemberExcelDTO> memberExcelDTOList = excelService.selectMemberExcel(file);
        for(MemberExcelDTO memberExcelDTO: memberExcelDTOList){
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setName(memberExcelDTO.getEmail());
            memberDTO.setEmail(memberExcelDTO.getEmail());
            memberService.insertMember(memberDTO);
        }
        return memberExcelDTOList;

    }
}
