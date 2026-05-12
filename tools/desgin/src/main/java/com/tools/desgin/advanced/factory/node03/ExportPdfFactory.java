package com.tools.desgin.advanced.factory.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 16:21
 */
public class ExportPdfFactory implements ExportFactory{
    @Override
    public ExportFile factory(String type) {
        if ("user-pdf".equals(type)){
            return new ExportUserPdfFile();
        }else if("log-pdf".equals(type)){
            return new ExportLogPdfFile();
        }else {
            throw new RuntimeException("没有找到对象");
        }
    }
}
