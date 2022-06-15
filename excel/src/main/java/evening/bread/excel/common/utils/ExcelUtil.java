package evening.bread.excel.common.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class ExcelUtil {

    /*
     * 데이터가 담긴 list를 받아 Excel 생성 후 반환
     */
    public static SXSSFWorkbook createExcel(List<HashMap<String, Object>> list){
        // 워크북
        SXSSFWorkbook workbook = null;

        // 행
        Row headerRow = null;
        Row row = null;

        // 셀
        Cell headerCell = null;
        Cell cell = null;

        // 셀 스타일
        CellStyle headerCellStyle = null;
        CellStyle cellStyle = null;

        // 셀 헤더 카운트
        int cellIndex = 0;

        // 행 카운트
        int rowIndex = 0;

        // 엑셀 헤더 정보 구성
        String[] headerInfo = {"number", "text", "date"};

        try {
            // 워크북 생성
            workbook = new SXSSFWorkbook();
            workbook.setCompressTempFiles(true);

            // 시트 생성
            SXSSFSheet sheet = (SXSSFSheet)workbook.createSheet("시트명");

            // 헤더 행 생성
            headerRow = sheet.createRow(rowIndex++);

            // 헤더 적용
            for(String head : headerInfo) {
                headerCell = headerRow.createCell(cellIndex++);
                headerCell.setCellValue(head);

            }

            // 바디 적용
            cellIndex = 0; // 셀 카운트 초기화
            for(HashMap<String, Object> rowData : list){
                row = sheet.createRow(rowIndex++);

                for(String headData : headerInfo) {
                    String cellData = (String) rowData.get(headData); // HashMap 객체에 Key에 해당하는 값얻기
                    cell = row.createCell(cellIndex++); // 셀 생성
                    cell.setCellValue(cellData); // 셀에 값 대입
                }
                cellIndex = 0; // 셀 카운트 초기화
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(workbook != null) {
                    workbook.close();
                    workbook.dispose();
                }// end if
            } catch (IOException e) {
                e.printStackTrace();
            }// catch
        }// catch

        return workbook;
    }





}
