package com.tools.pdf.box.web.service;

import com.tools.pdf.box.web.model.Signature;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author shenlx
 * @description
 * @date 2026/6/24 11:25
 */
@Service
public class SignatureService {
    public void addSignatureToPdf(String inputPdfPath, String outputPdfPath, Signature signature) throws IOException {
        try (PDDocument document = PDDocument.load(new File(inputPdfPath))) {
            PDPageContentStream contentStream = new PDPageContentStream(document, document.getPage(0));
            PDImageXObject pdImage = PDImageXObject.createFromFile(signature.getImagePath(), document);
            contentStream.drawImage(pdImage, signature.getX(), signature.getY(), signature.getWidth(), signature.getHeight());
            contentStream.close();

            document.save(outputPdfPath);
        }
    }
}
