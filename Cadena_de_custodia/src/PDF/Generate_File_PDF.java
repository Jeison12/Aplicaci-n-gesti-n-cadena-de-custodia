/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Andrés Zúñiga Garzón (hazgarzon@gmail.com)
 */
public class Generate_File_PDF {

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
    private static final Font smallBold_red = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.RED);
    private static final Font medBold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);

    //Variable para crear el documento,reservar el espacio en memoria para el archivo
    private Document documento;
    //Variable para asignar un nombre al documento
    private FileOutputStream ficheroPdf;
    //Variable para dibujar tablas
    private PdfPTable tabla;
    private PdfPTable tabla_cuerpo;
    private PdfPTable tabla_encabezado;
    private Paragraph paragrafo;
    private Paragraph paragrafo_title;
    private Image logo;
    private Image marca_agua;

    public Generate_File_PDF() {
    }

    public PdfPTable getTabla_cuerpo() {
        return tabla_cuerpo;
    }

    public void setTabla_cuerpo(PdfPTable tabla_cuerpo) {
        this.tabla_cuerpo = tabla_cuerpo;
    }

    public PdfPTable getTabla_encabezado() {
        return tabla_encabezado;
    }

    public void setTabla_encabezado(PdfPTable tabla_encabezado) {
        this.tabla_encabezado = tabla_encabezado;
    }

    public String getPath() {
        return Ruta.ruta_boletines;
    }

    public void setTabla(PdfPTable tabla) {
        this.tabla = tabla;
    }

    //==========================================================================
    public void crear_archivo(String nombre_pdf) throws FileNotFoundException, DocumentException {
        documento = new Document(PageSize.LEGAL);
        paragrafo = new Paragraph();
        ficheroPdf = new FileOutputStream(Ruta.ruta_boletines + nombre_pdf +".pdf");
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        this.tabla_cuerpo = new PdfPTable(5);
        tabla_cuerpo.setWidthPercentage(100);
    }

    public void crear_archivo_final(String nombre_pdf) throws FileNotFoundException, DocumentException {
        documento = new Document(PageSize.LEGAL);
        paragrafo = new Paragraph();
        ficheroPdf = new FileOutputStream(Ruta.ruta_boletines + nombre_pdf +".pdf");
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        this.tabla_cuerpo = new PdfPTable(4);
        tabla_cuerpo.setWidthPercentage(100);
    }
    
    public void crear_archivo(String nombre_pdf, int columnas) throws FileNotFoundException, DocumentException {
        documento = new Document(PageSize.LEGAL);
        paragrafo = new Paragraph();
        ficheroPdf = new FileOutputStream(Ruta.ruta_boletines + nombre_pdf + ".pdf");
        PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(20);
        documento.open();
        this.tabla_cuerpo = new PdfPTable(columnas);
        tabla_cuerpo.setWidthPercentage(100);
        this.tabla_encabezado = new PdfPTable(columnas);
        tabla_encabezado.setWidthPercentage(100);
    }

    public void escribir_texto(String texto) throws DocumentException {
        documento.add(new Paragraph(texto));
    }

    public void escribir_texto(Paragraph texto) throws DocumentException {
        documento.add(texto);
    }

    public Paragraph escribir_paragrafo(String text, int alineacion) {
        paragrafo = new Paragraph(text, smallBold);
        paragrafo.setAlignment(alineacion);
        paragrafo.setLeading(-18);
        return paragrafo;
    }

    public Paragraph escribir_paragrafo(String text) {
        paragrafo = new Paragraph(text, smallBold);
        paragrafo.setIndentationRight(2);
        return paragrafo;
    }

    public Paragraph escribir_paragrafo_red(String text) {
        paragrafo = new Paragraph(text, smallBold_red);
        paragrafo.setIndentationRight(2);
        return paragrafo;
    }

    public Paragraph escribir_paragrafo_titulo(String text, int alineacion) {
        paragrafo_title = new Paragraph(text, medBold);
        paragrafo_title.setAlignment(alineacion);
        return paragrafo_title;
    }

    //public void dibujar_tabla(int columnas,ArrayList<Cusa> lst_cursas) throws DocumentException{
    public void dibujar_tabla(int columnas) throws DocumentException {
        documento.addAuthor(" ");
        tabla = new PdfPTable(columnas);
        tabla.setWidthPercentage(100);
        for (int i = 0; i < 15; i++) {

        }
        documento.add(tabla);
    }

    public void cabecera_tabla() throws DocumentException {
        this.tabla_cuerpo.setWidthPercentage(100);
        this.tabla_cuerpo.addCell(escribir_paragrafo("Área"));
        this.tabla_cuerpo.addCell(escribir_paragrafo("Logros"));
        this.tabla_cuerpo.addCell(escribir_paragrafo("Nota"));
        this.tabla_cuerpo.addCell(escribir_paragrafo("Valoración"));
        this.tabla_cuerpo.addCell(escribir_paragrafo("Resumen"));
        this.documento.add(this.tabla_cuerpo);
    }

    public void cabecera_tabla_areas(ArrayList<String> lst_contenido) throws DocumentException {
        this.tabla_cuerpo.setWidthPercentage(100);
        this.tabla_cuerpo.addCell(add_celda_horizontal("No."));
        this.tabla_cuerpo.addCell(add_celda_horizontal("Apellidos y nombres"));
        for (int i = 0; i < lst_contenido.size(); i++) {
            this.tabla_cuerpo.addCell(add_celda_vertical(lst_contenido.get(i)));
        }
        this.tabla_cuerpo.addCell(add_celda_horizontal("Promedio"));
        this.tabla_cuerpo.addCell(add_celda_vertical("Puesto"));
        this.documento.add(this.tabla_cuerpo);
    }

    public void add_celda(String contenido) {
        this.tabla_cuerpo.addCell(contenido);
    }

    public PdfPCell add_celda_vertical(String contenido) {
        PdfPCell cell = new PdfPCell(escribir_paragrafo(contenido));
        cell.setRotation(90);
        cell.setBorder(0);
        //tabla_encabezado.addCell(cell);
        return cell;
    }

    public PdfPCell add_celda_horizontal(String contenido) throws DocumentException {
        PdfPCell cell = new PdfPCell(escribir_paragrafo(contenido, 4));
        cell.setBorder(0);
        //tabla_encabezado.addCell(cell);
        return cell;
    }

    public void escribir_tabla_cuerpo() throws DocumentException {
        this.documento.add(this.tabla_cuerpo);
    }

    public void escribir_tabla_encabezado() throws DocumentException {
        this.documento.add(this.tabla_encabezado);
    }

    public void dibujar_tabla(int columnas, ArrayList<String> contenido) throws DocumentException {
        documento.addAuthor(" ");
        tabla = new PdfPTable(columnas);
        tabla.setWidthPercentage(100);
        tabla.getDefaultCell().setBorder(0);
        float[] medida_celdas = {1f, 3f, 1f, 3f};
        tabla.setWidths(medida_celdas);
        for (int i = 0; i < contenido.size(); i++) {
            tabla.addCell(escribir_paragrafo(contenido.get(i)));
        }
        documento.add(tabla);
    }

    public void add_celda_vertical_aux(String contenido) {
        PdfPCell cell = new PdfPCell(escribir_paragrafo(contenido));
        cell.setRotation(90);
        tabla.addCell(cell);
        //return cell;
    }

    public void dibujar_tabla_sin_borde(int columnas) throws DocumentException {
        documento.addAuthor(" ");
        tabla_encabezado = new PdfPTable(columnas);
        tabla_encabezado.setWidthPercentage(100);
        tabla_encabezado.getDefaultCell().setBorder(0);

        documento.add(tabla);
    }

    public void dibujar_tablaX2(int columnas, ArrayList<String> contenido) throws DocumentException {
        documento.addAuthor(" ");
        tabla = new PdfPTable(columnas);
        tabla.setWidthPercentage(100);
        tabla.getDefaultCell().setBorder(0);
        float[] medida_celdas = {0.5f, 4f};
        tabla.setWidths(medida_celdas);
        for (int i = 0; i < contenido.size(); i++) {
            tabla.addCell(escribir_paragrafo(contenido.get(i)));
        }
        documento.add(tabla);
    }

    public void dibujar_tablaXN(int columnas, ArrayList<String> contenido) throws DocumentException {
        documento.addAuthor(" ");
        tabla = new PdfPTable(columnas);
        tabla.setWidthPercentage(100);
        tabla.getDefaultCell().setBorder(0);
//        float[] medida_celdas = {0.5f, 4f};
//        tabla.setWidths(medida_celdas);
        tabla.addCell(add_celda_horizontal("No"));
        tabla.addCell(add_celda_horizontal("Apellidos y nombres"));
        for (int i = 0; i < contenido.size(); i++) {
            //tabla.addCell(escribir_paragrafo(contenido.get(i)));
            tabla.addCell(add_celda_vertical(contenido.get(i)));
        }
        tabla.addCell(add_celda_horizontal("Promedio"));
        tabla.addCell(add_celda_vertical("Puesto"));
        documento.add(tabla);
    }

    public void tamanio_tabla_cuerpo(int tamanio) {
        this.tabla_cuerpo.setWidthPercentage(tamanio);
    }

    public void colocar_logo(String nombre_imagen) throws BadElementException, IOException, DocumentException {
        this.logo = Image.getInstance(nombre_imagen);
        this.logo.scaleAbsolute(560, 100);
        this.logo.setAlignment(Chunk.ALIGN_LEFT);
        this.documento.add(logo);
    }

    public void close_document() {
        this.documento.close();
    }

    public void nueva_hoja() {
        this.documento.newPage();
    }
}
