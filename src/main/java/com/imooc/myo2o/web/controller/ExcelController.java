package com.imooc.myo2o.web.controller;

import com.imooc.myo2o.entity.Role;
import com.imooc.myo2o.service.ExcelExportService;
import com.imooc.myo2o.service.RoleService;
import com.imooc.myo2o.util.execl.ExcelView;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;


@Controller
public class ExcelController {


    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView export() {
        ModelAndView mv = new ModelAndView();

        ExcelView ev = new ExcelView(exportService());

        ev.setFileName("所有角色.xls");

        List<Role> list = roleService.list();

        mv.addObject("list", list);
        mv.setView(ev);
        return mv;

    }


    @SuppressWarnings("unchecked")
    private ExcelExportService exportService() {
        return (Map<String, Object> model, Workbook workbook) -> {
            List<Role> list = (List<Role>) model.get("list");

            Sheet sheet=workbook.createSheet("所有角色");

            Row title= sheet.createRow(0);

            title.createCell(0).setCellValue("编号");
            title.createCell(1).setCellValue("名称");
            title.createCell(2).setCellValue("备注");


            for (int i = 0; i < list.size(); i++) {
                Role role1=list.get(i);

                int index=i+1;

                Row row=sheet.createRow(index);

                row.createCell(0).setCellValue(role1.getId());
                row.createCell(1).setCellValue(role1.getName());
                row.createCell(2).setCellValue(role1.getDesc());
            }
        };

    }
}
