package com.tools.pdf.box.web.controller;

import com.tools.pdf.box.web.model.Signature;
import com.tools.pdf.box.web.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 11:25
 */
@RestController
@RequestMapping("/api/signature")
public class SignatureController {
    @Autowired
    private SignatureService signatureService;


    @PostMapping("/add")
    public ResponseEntity<String> addSignature(@RequestParam("inputPdf") MultipartFile inputPdf,
                                               @RequestParam("image") MultipartFile image,
                                               @RequestParam("x") int x,
                                               @RequestParam("y") int y,
                                               @RequestParam("width") int width,
                                               @RequestParam("height") int height) throws IOException {

        String tempInputPdfPath = "temp_input.pdf";
        String tempOutputPdfPath = "temp_output.pdf";
        String tempImagePath = "temp_image.png";

        inputPdf.transferTo(new File(tempInputPdfPath));
        image.transferTo(new File(tempImagePath));

        Signature signature = new Signature();
        signature.setX(x);
        signature.setY(y);
        signature.setWidth(width);
        signature.setHeight(height);
        signature.setImagePath(tempImagePath);

        signatureService.addSignatureToPdf(tempInputPdfPath, tempOutputPdfPath, signature);

        Files.copy(Paths.get(tempOutputPdfPath), Paths.get("path/to/your/static/folder/" + tempOutputPdfPath));

        return ResponseEntity.ok().body("Signature added successfully!");
    }
}
