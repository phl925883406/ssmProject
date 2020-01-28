package com.imooc.myo2o.service;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

public interface ExcelExportService {

    /**
    * @ Description:
    * @ Param:
    * @ return:
    * @ Author: phl
    * @ Date: 2019/12/18
    * @ version 1.0
    */
    public void makeWorkBook(Map<String,Object> model, Workbook workBook);
}
