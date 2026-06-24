package com.tools.excel.easyexcel.async.web.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 13:31
 */
@Data
public class ExportSo implements Serializable {

    private static final long serialVersionUID = -5863762424044881914L;

    /**
     * 导出参数
     */
    private JSONObject exportParam;

    /**
     * 业务类型编码
     */
    private String businessTypeCode;
}
