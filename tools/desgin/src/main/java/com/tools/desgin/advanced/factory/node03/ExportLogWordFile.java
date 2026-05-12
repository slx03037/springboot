package com.tools.desgin.advanced.factory.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 16:17
 */
public class ExportLogWordFile implements ExportFile{
    @Override
    public boolean export(String data) {
        System.out.println("导出日志word文件");
        return true;
    }
}
