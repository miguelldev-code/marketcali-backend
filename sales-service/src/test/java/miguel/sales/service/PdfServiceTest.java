package miguel.sales.service;

import com.lowagie.text.DocumentException;
import miguel.sales.model.Sale;
import miguel.sales.model.SaleItem;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class PdfServiceTest {

    @Test
    void generateInvoicePdf() throws IOException, DocumentException {
        PdfService pdfService = new PdfService();

        SaleItem item = SaleItem.builder()
                .productName("Test Product")
                .quantity(2)
                .unitPrice(new BigDecimal("10.00"))
                .subTotal(new BigDecimal("20.00"))
                .build();

        Sale sale = Sale.builder()
                .id(1L)
                .customerId(100L)
                .saleDate(LocalDateTime.now())
                .totalAmount(new BigDecimal("20.00"))
                .items(Collections.singletonList(item))
                .build();

        byte[] pdfBytes = pdfService.generateInvoicePdf(sale);

        assertNotNull(pdfBytes);
        assertTrue(pdfBytes.length > 0);

        // Optional: Write to file for manual inspection
        // try (FileOutputStream fos = new FileOutputStream("test_invoice.pdf")) {
        //     fos.write(pdfBytes);
        // }
    }
}
