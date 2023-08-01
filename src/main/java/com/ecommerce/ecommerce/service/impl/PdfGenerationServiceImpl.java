package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.service.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class PdfGenerationServiceImpl implements PdfGenerationService {

    private final TemplateEngine templateEngine;
    @Override
    public byte[] generatePdfReport(String html) throws Exception {
        Context context = new Context();
        String processedHtml = templateEngine.process(html, context);

        try(ByteArrayOutputStream outputStream=new ByteArrayOutputStream()){
            ITextRenderer renderer=new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(outputStream);

            return outputStream.toByteArray();
        }
    }

}
