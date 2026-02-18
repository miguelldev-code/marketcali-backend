package miguel.sales.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import miguel.sales.model.Sale;
import miguel.sales.model.SaleItem;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] generateInvoicePdf(Sale sale) throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, out);

        document.open();

        // Title
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Color.BLACK);
        Paragraph title = new Paragraph("Invoice", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        
        document.add(Chunk.NEWLINE);

        // Sale Details
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.BLACK);
        document.add(new Paragraph("Sale ID: " + sale.getId(), contentFont));
        document.add(new Paragraph("Date: " + sale.getSaleDate(), contentFont));
        document.add(new Paragraph("Customer ID: " + sale.getCustomerId(), contentFont));
        
        document.add(Chunk.NEWLINE);

        // Table
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{3, 3, 2, 2});

        // Table Header
        addTableHeader(table);

        // Table Rows
        for (SaleItem item : sale.getItems()) {
            addTableRow(table, item);
        }

        document.add(table);
        
        document.add(Chunk.NEWLINE);

        // Total
        Paragraph total = new Paragraph("Total Amount: $" + sale.getTotalAmount(), titleFont);
        total.setAlignment(Element.ALIGN_RIGHT);
        document.add(total);

        document.close();

        return out.toByteArray();
    }

    private void addTableHeader(PdfPTable table) {
        Object[][] headers = {{"Product", Element.ALIGN_CENTER}, {"Quantity", Element.ALIGN_CENTER}, {"Price", Element.ALIGN_CENTER}, {"Subtotal", Element.ALIGN_CENTER}};

        for (Object[] header : headers) {
            PdfPCell headerCell = new PdfPCell(new Phrase((String) header[0]));
            headerCell.setHorizontalAlignment((Integer) header[1]);
            headerCell.setBackgroundColor(Color.LIGHT_GRAY);
            headerCell.setPadding(5);
            table.addCell(headerCell);
        }
    }

    private void addTableRow(PdfPTable table, SaleItem item) {
        table.addCell(item.getProductName());
        table.addCell(String.valueOf(item.getQuantity()));
        table.addCell("$" + item.getUnitPrice());
        table.addCell("$" + item.getSubTotal());
    }
}
