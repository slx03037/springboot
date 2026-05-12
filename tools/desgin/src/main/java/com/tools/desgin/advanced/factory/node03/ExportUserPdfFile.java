package com.tools.desgin.advanced.factory.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 16:18
 */
public class ExportUserPdfFile implements ExportFile{
    @Override
    public boolean export(String data) {
        System.out.println("导出用户Pdf文件");
        return true;
    }
}
