package com.imooc.myo2o.util.execl;

import com.imooc.myo2o.service.ExcelExportService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class ExcelView extends AbstractXlsView {


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ExcelExportService getExcelExportService() {
        return excelExportService;
    }

    public void setExcelExportService(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    private String fileName=null;

    private ExcelExportService excelExportService=null;

    public ExcelView(String viewName, ExcelExportService excelExportService) {
       this.setBeanName(viewName);
    }

    public ExcelView(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {


        if (excelExportService==null){
            throw new RuntimeException("服务接口不能为null");
        }

        if (!StringUtils.isEmpty(fileName)){
            String reqCharSet=httpServletRequest.getCharacterEncoding();
            reqCharSet=reqCharSet==null?"UTF-8":reqCharSet;
            fileName=new String(fileName.getBytes(reqCharSet),"ISO8859-1");

            httpServletResponse.setHeader("Content-disposition", "attachment;fileName="+fileName);
        }
        excelExportService.makeWorkBook(model,workbook);
    }
}
