package com.tools.document.excel.easypoi.basic.web.service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;

public interface IUserService {
    void upload(InputStream inputStream) throws Exception;

    void downloadExcel(ServletOutputStream outputStream) throws IOException;

    void fillExcelTemplate(ServletOutputStream outputStream) throws IOException;
}
