package com.tools.excel.easyexcel.batch.web.service;

import com.alibaba.excel.ExcelWriter;
import com.tools.excel.easyexcel.batch.web.model.User;
import com.tools.excel.easyexcel.batch.web.utils.AbstractExport;
import com.tools.excel.easyexcel.batch.web.utils.ExportQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 14:43
 */
@Service
@Slf4j
public class ExportImpl extends AbstractExport {
    @Autowired
    private
    ExportQueue exportQueue;

    @Override
    public void export(User sysUser) throws InterruptedException {
        //导出
        log.info("导出文件方法执行～～～～～～～～～");
//                        export(response,pageSize,t,k,fileName);
        LinkedList<User> queue = exportQueue.add(sysUser);
        log.info("导出队列：" + queue);
        //休眠时间稍微设置大点，模拟导出处理时间
        Thread.sleep(20000);
        //导出成功后移除当前导出用户
        User nextSysUser = exportQueue.getNextSysUser();
        log.info("移除后获取下一个排队的用户: " + nextSysUser.getName());
    }

    @Override
    public void export(HttpServletResponse response, int pageSize, Object o, Class k, String fileName) throws Exception {
        super.export(response, pageSize, o, k, fileName);
    }

    @Override
    public ExcelWriter getExcelWriter(HttpServletResponse response, String fileName) throws IOException {
        return super.getExcelWriter(response, fileName);
    }

    @Override
    public void complexFillWithTable(Object o, String fileName, HttpServletResponse response) {
    }

    @Override
    public int countExport(Object o) {
        return 0;
    }

    @Override
    public List<Object> getExportDetail(Object o) {
        return null;
    }
}
