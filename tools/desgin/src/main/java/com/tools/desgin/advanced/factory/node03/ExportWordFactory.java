package com.tools.desgin.advanced.factory.node03;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 16:21
 */
public class ExportWordFactory implements ExportFactory{
    @Override
    public ExportFile factory(String type) {
        if ("user-word".equals(type)){
            return new ExportUserWordFile();
        }else if("log-word".equals(type)){
            return new ExportLogWordFile();
        }else {
            throw new RuntimeException("没有找到对象");
        }
    }
}
