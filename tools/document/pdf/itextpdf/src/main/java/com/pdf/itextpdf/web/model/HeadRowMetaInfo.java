package com.pdf.itextpdf.web.model;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 11:33
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HeadRowMetaInfo {
    //     列中文名
    private String colName;
    //     列key
    private String colKey;
    //     列宽度
    private float width;
}