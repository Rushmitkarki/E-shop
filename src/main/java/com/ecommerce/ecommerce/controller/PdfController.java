package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.service.PdfGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileOutputStream;

@RequiredArgsConstructor
@Controller
@RequestMapping("/buyer/bill")
public class PdfController {

    public static String uploadDir = System.getProperty("user.dir") + "/bill/";
    private final PdfGenerationService pdfGenerationService;

    @GetMapping("/download/pdf")
    public String generatePdfReport(Model model) throws Exception {

        String html="bill.html";
        byte[] pdf=pdfGenerationService.generatePdfReport(html);
        String path=uploadDir+System.currentTimeMillis()+".pdf";
        FileOutputStream fos=new FileOutputStream(path);
        fos.write(pdf);
        fos.close();

        return "redirect:/buyer/bill/checkout";
    }
}
