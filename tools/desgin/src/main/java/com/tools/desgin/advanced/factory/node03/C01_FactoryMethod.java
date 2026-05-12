package com.tools.desgin.advanced.factory.node03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shenlx
 * @description
 * @date 2024/8/14 16:24
 */
public class C01_FactoryMethod {
    public static void main(String[]agrs) throws ParseException {
        String data="";

        ExportFactory factory=new ExportWordFactory();
        ExportFile factory1 = factory.factory("user-word");
        factory1.export(data);
        factory=new ExportPdfFactory();
        ExportFile factory2 = factory.factory("log-pdf");
        factory2.export(data);

        String date="2024-07-02 00:00:00";
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        System.out.println(parse.getTime());
    }
}
