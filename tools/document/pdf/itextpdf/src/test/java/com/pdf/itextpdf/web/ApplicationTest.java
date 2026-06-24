package com.pdf.itextpdf.web;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.pdf.itextpdf.web.model.HeadRowMetaInfo;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 11:34
 */
@SpringBootTest
public class ApplicationTest {
    /**
     * 自定义头部信息
     *
     * @return
     */
    public static List<HeadRowMetaInfo> headInfos() {
        List<HeadRowMetaInfo> infos = new ArrayList<>();
        infos.add(new HeadRowMetaInfo("货物或应税劳务、服务名称", "commodityName", 80));
        infos.add(new HeadRowMetaInfo("规格型号", "model", 80));
        infos.add(new HeadRowMetaInfo("单位", "pushUnitName", 80));
        infos.add(new HeadRowMetaInfo("数量", "orderNum", 80));
        infos.add(new HeadRowMetaInfo("单价", "orderPriceNoTax", 80));
        infos.add(new HeadRowMetaInfo("不含税金额", "orderAmount", 80));
        infos.add(new HeadRowMetaInfo("税额", "taxAmt", 80));
        infos.add(new HeadRowMetaInfo("含税金额", "orderAmountTax", 80));
        infos.add(new HeadRowMetaInfo("税率", "taxRate", 80));
        return infos;
    }

    public static PdfPTable generatePdfPTable(float totalWidth, Font font, List<Map<String, Object>> data, List<HeadRowMetaInfo> headRowMetaInfos) throws DocumentException {
        //     多少列
        PdfPTable table = new PdfPTable(headRowMetaInfos.size());
        //     表宽度
        table.setTotalWidth(totalWidth);

        //     设置每列的宽度
        List<Float> flist = headRowMetaInfos.stream().map(HeadRowMetaInfo::getWidth).collect(Collectors.toList());
        float[] farr = new float[flist.size()];
        for (int i = 0; i < flist.size(); i++) {
            farr[i] = flist.get(i);
        }
        table.setWidths(farr);

        Map<Integer, String> indexToKeyMap = new HashMap<>();
        //     根据表头信息插入表头
        for (int i = 0; i < headRowMetaInfos.size(); i++) {
            table.addCell(new Phrase(headRowMetaInfos.get(i).getColName(), font));
            indexToKeyMap.put(i, headRowMetaInfos.get(i).getColKey());
        }

        //     添加行数据
        for (Map<String, Object> dataItem : data) {
            for (int i = 0; i < headRowMetaInfos.size(); i++) {
                if (dataItem.get(indexToKeyMap.get(i)) != null) {
                    String str = dataItem.get(indexToKeyMap.get(i)).toString();
                    table.addCell(new Phrase(str, font));
                } else {
                    table.addCell("-");
                }
            }
        }

        //     计算表格在页面上的位置并添加到页面
        //     注意：这里的坐标可能需要根据实际情况调整
        table.setLockedWidth(true);
        return table;
    }

    public static byte[] copy(List<byte[]> files) throws DocumentException, IOException {
        //     创建文档对象
        Document document = new Document();
        //     创建PdfCopy对象
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfCopy copy = new PdfCopy(document, bos);
        //     设置只读
        copy.setEncryption(null, null, PdfWriter.ALLOW_PRINTING, PdfWriter.STANDARD_ENCRYPTION_128);


        //     打开文档
        document.open();
        PdfReader reader;
        int n;
        //     循环遍历所有PDF文件
        for (byte[] file : files) {
            reader = new PdfReader(file);
            //     获取每个PDF文件的页数
            n = reader.getNumberOfPages();
            for (int page = 0; page < n; ) {
                //     向PdfCopy添加每一页
                copy.addPage(copy.getImportedPage(reader, ++page));
            }
            //     关闭PdfReader
            reader.close();
        }

        //     关闭文档，否则输出流不会刷新
        document.close();

        byte[] bytes = bos.toByteArray();

        //     关闭流
        bos.close();
        return bytes;
    }

    public static void main(String[] args) {
        try {
            //     创建一个新的     PDF     文档
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream("D:\\home\\adjusted_table_position.pdf"));
            document.open();

            //     添加文本内容
            document.add(new Paragraph("Test     PDF     with     Table"));

            //     创建表格
            PdfPTable table = new PdfPTable(2);
            table.addCell("Name");
            table.addCell("Age");
            table.addCell("Alice");
            table.addCell("25");
            table.addCell("Bob");
            table.addCell("30");

            //     设置表格之前的间距
            table.setSpacingBefore(20f);

            //     设置表格之后的间距
            table.setSpacingAfter(20f);

            //     设置表格的总宽度
            table.setTotalWidth(300f);

            //     将表格添加到     PDF
            document.add(table);

            document.close();

            System.out.println("PDF 文件生成成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
