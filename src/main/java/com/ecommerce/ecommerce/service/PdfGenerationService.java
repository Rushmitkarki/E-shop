package com.ecommerce.ecommerce.service;

public interface PdfGenerationService {

    byte[] generatePdfReport(String html) throws Exception;
}
