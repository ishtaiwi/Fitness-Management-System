package userManagement;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import fitness.helper.PDFReportGenerator;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class generateReportTC {
    private String filePath;
    private String title;
    private List<String[]> reportData = new ArrayList<>();

    @Given("the report file path is {string}")
    public void theReportFilePathIs(String filePath) {
        this.filePath = filePath;
    }

    @Given("the report title is {string}")
    public void theReportTitleIs(String title) {
        this.title = title;
    }

    @Given("the report data contains:")
    public void theReportDataContains(DataTable dataTable) {
        List<List<String>> rawData = dataTable.asLists(String.class);
        reportData = new ArrayList<>();
        for (List<String> row : rawData) {
            reportData.add(row.toArray(new String[0]));
        }    }

    @When("I generate the PDF report")
    public void iGenerateThePDFReport()  {
        PDFReportGenerator generator = new PDFReportGenerator();
        generator.generateReport(filePath, title, reportData);
    }

    @Then("the PDF file {string} should exist")
    public void thePDFFileShouldExist(String filePath) {
        File file = new File(filePath);
        Assert.assertTrue("PDF file does not exist!", file.exists());
    }

    @Then("the PDF file should contain the text {string}")
    public void thePDFFileShouldContainTheText(String expectedText) throws IOException {
        try (PdfReader reader = new PdfReader(filePath);
             PdfDocument pdfDocument = new PdfDocument(reader)) {
            StringBuilder content = new StringBuilder();
            for (int i = 1; i <= pdfDocument.getNumberOfPages(); i++) {
                content.append(PdfTextExtractor.getTextFromPage(pdfDocument.getPage(i)));
            }
            Assert.assertTrue("PDF does not contain the expected text!", content.toString().contains(expectedText));
        }
    }
}
