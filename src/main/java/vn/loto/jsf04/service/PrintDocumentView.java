package vn.loto.jsf04.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

@Named
@ViewScoped
public class PrintDocumentView implements Serializable {
    private static final long serialVersionUID = 1L;
    @Getter @Setter
    private PDFOptions pdfOpt;

    private static final float LOGO_WIDTH_PERCENTAGE = 25f; // Percentage of page width for logo

    @PostConstruct
    public void init(){
        customizationOptions();
    }
    public void customizationOptions(){
        pdfOpt = new PDFOptions();
        pdfOpt.setFacetBgColor("#f7e4c6");
        pdfOpt.setFacetFontColor("#000000");
        pdfOpt.setFacetFontStyle("BOLD");
        pdfOpt.setCellFontSize("11");
        pdfOpt.setFontName("HELVETICA");
        pdfOpt.setOrientation(PDFOrientationType.LANDSCAPE);

    }

    public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
        Document pdf = (Document) document;
        pdf.open();
        pdf.setPageSize(PageSize.A4.rotate());

        String logoPath = "/img/logo.png"; // Adjust the path as per your image location

        URL logoUrl = getClass().getResource(logoPath);
        if (logoUrl != null) {
            Image logoImage = Image.getInstance(logoUrl);

            // Calculate the width of the logo as a percentage of the page width
            float pageWidth = pdf.getPageSize().getWidth();
            float logoWidth = (LOGO_WIDTH_PERCENTAGE / 100) * pageWidth;

            logoImage.scaleToFit(logoWidth, Float.MAX_VALUE); // Set the width of the logo

            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(80);
            headerTable.setWidths(new float[]{6, 4});
            headerTable.setSpacingBefore(30.0f);

            PdfPCell logoCell = new PdfPCell(logoImage);
            logoCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(logoCell);

            PdfPCell companyInfoCell = new PdfPCell();
            companyInfoCell.setBorder(Rectangle.NO_BORDER);
            Paragraph companyName = new Paragraph("SDBM COMPANY");
            companyName.getFont().setSize(18); // Set font size
            companyName.getFont().setColor(Color.RED);

            Font addressFont = new Font(Font.HELVETICA, 12, Font.ITALIC);
            Font phoneFont = new Font(Font.HELVETICA, 12, Font.ITALIC);

            Paragraph companyAdresse = new Paragraph("Adresse: 123 Rue A, 59200 Tourcoing", addressFont);
            Paragraph companyPhone = new Paragraph("Numero: 0123456789", phoneFont);

            companyInfoCell.addElement(companyName);
            companyInfoCell.addElement(companyAdresse);
            companyInfoCell.addElement(companyPhone);

            headerTable.addCell(companyInfoCell);

            pdf.add(headerTable);
        } else {
            throw new IOException("Logo image not found");
        }
    }

}
