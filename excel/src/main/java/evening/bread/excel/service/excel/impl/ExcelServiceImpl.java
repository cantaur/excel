package evening.bread.excel.service.excel.impl;

import evening.bread.excel.model.Excel.MemberExcelDTO;
import evening.bread.excel.service.excel.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService {


    private Sheet excelProcess(MultipartFile file) throws Exception{
        //확장자 검사 commons.io 라이브러리의 확장자를 얻어오는 FilenameUtils 클래스를 통해 엑셀 확장자 여부 판단
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new Exception("Excel 파일만 업로드가 가능합니다.");
        }
        Workbook workbook = null;
        //엑셀 파일의 데이타를 읽어 각각 확장자별로 sheet를 만들기 위한 최상위 객체 생성
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        //시트 객체를 만듬
        Sheet worksheet = workbook.getSheetAt(0);
        return worksheet;
    }


    @Override
    public List<MemberExcelDTO> selectMemberExcel(MultipartFile file) throws Exception{
        Sheet worksheet = this.excelProcess(file);

        List<MemberExcelDTO> memberExcelDTOList = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            //행의 갯수만큼 반복문을 돌며 데이터를 가져옴
            Row row = worksheet.getRow(i);

            MemberExcelDTO memberExcelDTO = new MemberExcelDTO();

            memberExcelDTO.setNum((int) row.getCell(0).getNumericCellValue()); //실수 데이터 가져오기
            memberExcelDTO.setName(row.getCell(1).getStringCellValue()); //문자열 데이터 가져오기
            memberExcelDTO.setEmail(row.getCell(2).getStringCellValue()); //논리 데이터 가져오기

            memberExcelDTOList.add(memberExcelDTO);
        }

        return memberExcelDTOList;
    }
}
