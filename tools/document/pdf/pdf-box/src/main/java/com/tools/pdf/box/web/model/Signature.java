package com.tools.pdf.box.web.model;

import lombok.Data;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 11:27
 */
@Data
public class Signature {
    private int x;
   private int y;
    private int width;
    private int height;
    private String imagePath;

}
