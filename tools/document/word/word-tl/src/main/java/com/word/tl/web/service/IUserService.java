package com.word.tl.web.service;

import com.deepoove.poi.XWPFTemplate;

import java.io.IOException;

public interface IUserService {
    XWPFTemplate generateWordXWPFTemplate() throws IOException;

    XWPFTemplate generateWordXWPFTemplateMD() throws IOException;
}
