package evening.bread.excel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import evening.bread.excel.common.utils.ExcelUtil;
import evening.bread.excel.common.utils.JacksonUtils;
import evening.bread.excel.model.Excel.MemberExcelDTO;
import evening.bread.excel.model.member.MemberDTO;
import evening.bread.excel.service.excel.ExcelService;
import evening.bread.excel.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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



    /**
     * method POST
     * excel 파일 db insert
     * @param file
     * @return List<MemberExcelDTO>
     * @throws Exception
     */
    @PostMapping("/excel/upload")
    public List<MemberExcelDTO> readExcel(@RequestParam("file") MultipartFile file)
            throws Exception {

        List<MemberExcelDTO> memberExcelDTOList = excelService.selectMemberExcel(file);
        memberService.insertMemberList(memberExcelDTOList);

        return memberExcelDTOList;

    }


    @GetMapping("/excel/download")
    public void excelDownload(HttpServletResponse response) throws Exception {

        List<MemberDTO> memberDTOList = memberService.selectMemberInfo();
        List<HashMap<String, Object>> memberMapList = new ArrayList<>();
        for(MemberDTO memberDTO : memberDTOList){
            HashMap<String, Object> memberDtoMap = (HashMap<String, Object>) JacksonUtils.objectToMap(memberDTO);
            memberMapList.add(memberDtoMap);
        }
        log.debug("memberMapList"+memberMapList);


        SXSSFWorkbook workbook = ExcelUtil.createExcel(memberMapList);

        OutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook != null) { workbook.close(); }
                if(outputStream != null) { outputStream.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }// catch
        }

        log.debug("workbook"+workbook);












    }





}
