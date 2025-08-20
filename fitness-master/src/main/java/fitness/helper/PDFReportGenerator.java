/**
 * Utility class for generating PDF reports with tabular data.
 */
package fitness.helper;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Generates PDF reports with a title and tabular data using the iText library.
 */
public class PDFReportGenerator {

    private final Logger logger = Logger.getLogger(PDFReportGenerator.class.getName());
    /**
     * Generates a PDF report with the specified title and data.
     *
     * @param filePath The file path where the PDF report will be saved.
     * @param title    The title of the report.
     * @param data     The data to include in the report. The first row is treated as headers.
     */
    public void generateReport(String filePath, String title, List<String[]> data) {
        try {
            // Create directories if they do not exist
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            // Initialize the PDF writer and document
            PdfWriter writer = new PdfWriter(file);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add the title to the document
            document.add(new Paragraph(title)
                    .setBold()
                    .setFontSize(18)
                    .setMarginBottom(20));

            // Add tabular data if available
            if (data != null && !data.isEmpty()) {
                String[] headers = data.get(0); // First row as headers
                Table table = new Table(headers.length);

                // Add header cells
                for (String header : headers) {
                    table.addHeaderCell(header);
                }

                // Add data rows
                for (int i = 1; i < data.size(); i++) {
                    String[] row = data.get(i);
                    for (String cell : row) {
                        table.addCell(cell);
                    }
                }

                document.add(table);
            } else {
                // Add a message if no data is available
                document.add(new Paragraph("No data available to display."));
            }

            // Close the document
            document.close();

            logger.severe("PDF Report generated successfully at: " + filePath);
        } catch (IOException e) {
            logger.severe("Error while generating PDF report: " + e.getMessage());
        }
    }
}
