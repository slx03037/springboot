package com.tools.excel.easyexcel.batch.web;

import com.tools.excel.easyexcel.batch.web.model.User;
import com.tools.excel.easyexcel.batch.web.service.ExportImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 14:46
 */
@SpringBootTest
@Slf4j
public class ApplicationTest {
    @Autowired
    private
    ExportImpl export;

    @Test
    public void exportFile() {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread thread1 = Thread.currentThread();
                User sysUser = new User();
                sysUser.setName(thread1.getName());
                export.export(sysUser);
            }
        }).start();
    }
}
