/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author alexa
 */
public class FooterPage extends PdfPageEventHelper {

    private PdfTemplate template;
    private Image total;

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        template = writer.getDirectContent().createTemplate(30, 16);
        try {
            total = Image.getInstance(template);
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        addFooter(writer);
    }

    private void addFooter(PdfWriter writer) {
        PdfPTable piePagina = new PdfPTable(3);
        try {
            
            piePagina.setWidths(new int[]{23, 4, 1});
            piePagina.setTotalWidth(530);
            
//            piePagina.setTotalWidth(800);
            piePagina.setLockedWidth(true);
            piePagina.getDefaultCell().setFixedHeight(40);
            piePagina.getDefaultCell().setBorder(Rectangle.TOP);
            piePagina.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
//            piePagina.getDefaultCell().setPaddingBottom(-10);
            // Agregar copyright
            piePagina.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            piePagina.addCell(new Phrase("        "
                    + "                "
                    + "\"Ustedes son la sal de la tierra y la luz del mundo\"  Mt 5:13", new Font(Font.FontFamily.HELVETICA, 10)));
            
            // Agregar pagina actual
            piePagina.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            piePagina.addCell(new Phrase(String.format("Página %d de", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // Agregar el total de paginas
            PdfPCell contTotalPaginas = new PdfPCell(total);
            contTotalPaginas.setBorder(Rectangle.TOP);
            contTotalPaginas.setBorderColor(BaseColor.LIGHT_GRAY);
            piePagina.addCell(contTotalPaginas);

            // Escribir en la pagina
            PdfContentByte lienzo = writer.getDirectContent();
            lienzo.beginMarkedContentSequence(PdfName.ART);
            piePagina.writeSelectedRows(0, -1, 40, 30, lienzo);
            lienzo.endMarkedContentSequence();
        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
        int tamanioTotal = String.valueOf(writer.getPageNumber()).length();
        int anchoTotal = tamanioTotal * 5;
        ColumnText.showTextAligned(template, Element.ALIGN_LEFT,
                new Phrase(String.valueOf(writer.getPageNumber() - 1), new Font(Font.FontFamily.HELVETICA, 13)),
                anchoTotal, 1, 0);
    }

}
