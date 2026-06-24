package com.tools.excel.easyexcel.async.web.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.tools.excel.easyexcel.async.web.common.ExportSo;
import com.tools.excel.easyexcel.async.web.common.model.Student;
import com.tools.excel.easyexcel.async.web.service.ExportBaseService;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:30
 */
@Service("studentExportService")
public class StudentExportServiceImpl implements ExportBaseService {
    @Override
    public void export(ExportSo exportSo, ExcelWriter excelWriter, WriteSheet writeSheet) {
        // 1、根据条件(exportSo.getExportParam())分页查询数据库学生信息
        List<Student> studentList = getstudentList();
        // 2、写入excel
        excelWriter.write(studentList, writeSheet);
    }

    private List<Student> getstudentList() {
        List<Student> studentList = Lists.newArrayList();
        Student student1 = new Student();
        student1.setId(1);
        student1.setStuName("张三");
        student1.setStuCode("1001");
        student1.setGender("男");
        student1.setCityName("北京");
        studentList.add(student1);
        Student student2 = new Student();
        student2.setId(2);
        student2.setStuName("历史");
        student2.setStuCode("1002");
        student2.setGender("女");
        student2.setCityName("长沙");
        studentList.add(student2);
        return studentList;
    }
}
