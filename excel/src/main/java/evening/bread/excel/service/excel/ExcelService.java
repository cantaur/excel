package evening.bread.excel.service.excel;

import evening.bread.excel.model.Excel.MemberExcelDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {

    List<MemberExcelDTO> selectMemberExcel(MultipartFile file) throws Exception;

}
