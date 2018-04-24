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
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author alexa
 */
public class GenerarFormularioInscripcion {
    private String rutaFormulario = "/home/javier/Documentos/UNICAUCA/ForenseDOCS/";

    private Paragraph paragrafo;
    private Document documento;
    private FileOutputStream archivoPdf;

    private static final Font titulo = new Font(Font.FontFamily.TIMES_ROMAN, 13, Font.BOLD);
    private static final Font contenido = new Font(Font.FontFamily.TIMES_ROMAN, 11);
    private static final Font contNombresApellidos = new Font(Font.FontFamily.TIMES_ROMAN, 15);

    public GenerarFormularioInscripcion() {

    }

    public void crear_archivo(String nombre_pdf) throws FileNotFoundException, DocumentException {

        documento = new Document(PageSize.A4,85,85,85,85);
        documento.setMargins(0, 0, 36, 0);
        paragrafo = new Paragraph();
        archivoPdf = new FileOutputStream(rutaFormulario + nombre_pdf + ".pdf");
        
        PdfWriter wr = PdfWriter.getInstance(documento, archivoPdf);
        FooterPage event = new FooterPage();
        wr.setPageEvent(event);
        
        documento.open();

    }

    public void cerrarDocumento() {

        this.documento.close();
    }

//    public void escribir_paragrafo(String text) throws DocumentException {
//        documento.add(new Phrase(text));
//    }
    /**
     * Realiza la creacion del encabezado en el documento pdf con informacion de
     * la institucion.
     */
    public void crear_encabezado() {
        PdfPTable header = new PdfPTable(2);
        int margin_top = -10;

        try {
            header.setWidths(new int[]{3, 21});
        } catch (DocumentException ex) {
            System.out.println("crear_encabezado -> crear_encabezado() 1");
        }
        header.setTotalWidth(475);
        header.setLockedWidth(true);

//        Image logo = null;
//        try {
//            logo = Image.getInstance(Ruta.rutaImagenFormulario);
//        } catch (BadElementException ex) {
//            return;
//        } catch (IOException ex) {
//            return;
//        }
//        PdfPCell cellImg = new PdfPCell(logo);
//        cellImg.setBorder(0);
//        cellImg.setPaddingLeft(0);
//        cellImg.setPaddingTop(0);
//        cellImg.setPaddingBottom(0);
//        header.addCell(cellImg);
        // add text
        PdfPCell text = new PdfPCell();
        text.setPaddingBottom(0);
        text.setPaddingLeft(10);
        text.setBorder(Rectangle.BOTTOM);
        text.setBorderWidth(0);
        text.setBorderColor(BaseColor.BLACK);//LIGHT_GRAY);
        Paragraph texto = new Paragraph("SEMINARIO MENOR ARQUIDIOCESANO", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD));
        texto.setAlignment(Element.ALIGN_CENTER);
        text.addElement(texto);

        texto = new Paragraph("AUTOPISTA NORTE TEL: 8235157-8231356", new Font(Font.FontFamily.HELVETICA, 10));
        texto.setAlignment(Element.ALIGN_CENTER);
        text.addElement(texto);
        texto = new Paragraph("Email: smenor1@hotmail.com", new Font(Font.FontFamily.HELVETICA, 8));

        texto.setAlignment(Element.ALIGN_CENTER);
        text.addElement(texto);

        text.setPaddingTop(margin_top);

        header.addCell(text);
        try {
            this.documento.add(header);
        } catch (DocumentException ex) {
            System.out.println("Gen_File_PDF_Final -> crear_encabezado()  2");
        }
    }

    /**
     * Crea una celda y dentro de ella un parrafo vacio, esta celda es utilizada
     * para representar un espacio en una tabla. Al terminar la creacion retorna
     * la celda creada para ser ingresada en una tabla.
     *
     * @return Retorna una celda vacia de tipo PdfCell.
     * @throws DocumentException
     */
    public PdfPCell space() throws DocumentException {
        PdfPCell celda = new PdfPCell(new Paragraph(""));
        celda.setBorderWidth(0);
        return celda;
    }

    /**
     * Recibe como parametro el numero de columas que va a tener una tabla al
     * momento de su creacion. Al terminar la creacion de la tabla la retorna.
     *
     * @param columnas Numero de columnas que va a contener la tabla.
     * @return retorna una tabla de tipo PdfTable.
     */
    public PdfPTable crearTabla(int columnas) {

        return new PdfPTable(columnas);
    }

    /**
     * Recibe como parametro el numero de columnas, el ancho de la primera y
     * segunda columna de la tabla. Una ves recibidos estos parametros, crea la
     * tabla y la retorna.
     *
     * @param columnas Numero de columnas que va a contener la tabla.
     * @param col1 Tamanio de la primera columan de la tabla.
     * @param col2 Tamanio de la segunda columan de la tabla.
     * @return retorna una tabla de tipo PdfTable.
     * @throws DocumentException
     */
    public PdfPTable crearTabla(int columnas, float col1, float col2) throws DocumentException {
        PdfPTable tabla = new PdfPTable(columnas);
        tabla.setWidths(new float[]{col1, col2});
        return tabla;
    }

    /**
     * recibe como parametro el numero de columnas de la tabla y el tamanio para
     * las columnas 1,2,3. Una ves recibidos estos parametros se realiza la
     * creacion de la tabla y se la retorna.
     *
     * @param columnas numero de columnas que contiene la tabla a crear.
     * @param col1 Tamanio de la columna primera de la tabla.
     * @param col2 Tamanio de la columna segunda de la tabla.
     * @param col3 Tamanio de la columna tercera de la tabla.
     * @return retorna una tabla de tipo PdfTable.
     * @throws DocumentException
     */
    public PdfPTable crearTabla(int columnas, float col1, float col2, float col3) throws DocumentException {
        PdfPTable tabla = new PdfPTable(columnas);
        tabla.setWidths(new float[]{col1, col2, col3});
        return tabla;
    }

    /**
     * Recibe como parametros la cantidad de columnas que va a tener la tabla al
     * momento de su creacion, ademas de esto tambien recibe el tamanio de las
     * columnas 1,2,3,4,5 y 6. Una ves recibidos estos parametros se realiza la
     * creacion de la tabla y se la retorna.
     *
     * @param columnas numero de columnas que va a contener la tabla.
     * @param col1 tamanio de la primera columna de la tabla.c
     * @param col2 tamanio de la segunda columna de la tabla.
     * @param col3 tamanio de la tercera columna de la tabla.
     * @param col4 tamanio de la cuarta columna de la tabla.
     * @param col5 tamanio de la quinta columna de la tabla.
     * @param col6 tamanio de la sexta columna de la tabla.
     * @return retorna una tabla de tipo PdfTable.
     * @throws DocumentException
     */
    public PdfPTable crearTabla(int columnas, float col1, float col2, float col3, float col4, float col5, float col6) throws DocumentException {
        PdfPTable tabla = new PdfPTable(columnas);
        tabla.setWidths(new float[]{col1, col2, col3, col4, col5, col6});
        return tabla;
    }

    /**
     * Recibe como parametro el numero de columnas que va a contener la tabla al
     * momento de su creacio, ademas de esto tambien recibe los tamanios de las
     * columnas 1,2,3 y 4. Una ves recibidos estos parametros se realiza la
     * creacion de la tabla y se la retorna.
     *
     * @param columnas numero de columnas que va a tener la tabla.
     * @param col1 tamanio de la primera columna de la tabla.
     * @param col2 tamanio de la segunda columna de la tabla.
     * @param col3 tamanio de la tercera columna de la tabla.
     * @param col4 tamanio de la cuarta columna de la tabla.
     * @return retorna una tabla de tipo PdfTable.
     * @throws DocumentException
     */
    public PdfPTable crearTabla(int columnas, float col1, float col2, float col3, float col4) throws DocumentException {
        PdfPTable tabla = new PdfPTable(columnas);
        tabla.setWidths(new float[]{col1, col2, col3, col4});
        return tabla;
    }

    /**
     * Recibe como parametro un objeto de tipo PdfTable. Una ves recibido este
     * parametro, se realiza la escritura de esta tabla en el documento pdf.
     *
     * @param tabla tabla de tipo PdfTable
     * @throws DocumentException
     */
    public void insertarTabla(PdfPTable tabla) throws DocumentException {

        this.documento.add(tabla);
    }

    /**
     * Recibe como parametros el texto, tipo de fuente, bordes de la celda,
     * espacios internos del texto en la celda y el tamanio de la celda. Una ves
     * recibidos estos parametros, se realiza la creacion de la celda, agregando
     * el contenido y especificaciones dadas en los parametros. Terminada la
     * creacion de la celda y su contenido, se retorna la celda.
     *
     * @param texto texto a escribir en el documento pdf.
     * @param fuente indica el tipo de fuente a utilizar en el texto.
     * @param bordeSup Indica si la celda debe tener borde superior.
     * @param bordeInf Indica si la celda debe tener borde inferior.
     * @param bordeIzq Indica si la celda debe tener borde izquierdo.
     * @param bordeDer Indica si la celda debe tener borde derecho.
     * @param paddingSup Espacio en la parte superior del texto.
     * @param paddingInf Espacio en la parte inferior del texto.
     * @param paddingIzq Espacio en la parte izquierda del texto.
     * @param paddingDer Espacio en la parte derecha del texto.
     * @param tamCelda tamanio de la celda.
     * @return celda de tipo PdfCell.
     * @throws DocumentException
     */
    public PdfPCell insertarTextoCelda(String texto, String fuente, float bordeSup, float bordeInf, float bordeIzq, float bordeDer, float paddingSup, float paddingInf, float paddingIzq, float paddingDer, float tamCelda) throws DocumentException {
        PdfPCell celda = new PdfPCell();
        Paragraph parrafo = null;

        switch (fuente) {
            case "titulo":
                parrafo = new Paragraph(texto, titulo);
                break;
            case "contenido":
                parrafo = new Paragraph(texto, contenido);
                break;
            case "nombresAp":
                parrafo = new Paragraph(texto, contNombresApellidos);
                break;
        }

        celda.setBorderWidthTop(bordeSup);
        celda.setBorderWidthBottom(bordeInf);
        celda.setBorderWidthLeft(bordeIzq);
        celda.setBorderWidthRight(bordeDer);

        celda.setPaddingTop(paddingSup);
        celda.setPaddingBottom(paddingInf);
        celda.setPaddingLeft(paddingIzq);
        celda.setPaddingRight(paddingDer);
        celda.setFixedHeight(tamCelda);

        celda.addElement(parrafo);

        return celda;
    }

    /**
     * Reibe como parametro el texto, fuente, alineacion del texto. Una ves
     * reibidos estos parametros, realiza la creacion de la celda con los
     * parametros recibidos. terminada la creacion de la celda se retorna.
     *
     * @param texto texto a escribir en el documento pdf.
     * @param fuente tipo de fuente a agregar al texto.
     * @param alineacion posicion en la que se va a colocar el texto.
     * @return retorna una celda de tipo PdfCell.
     * @throws DocumentException
     */
    public PdfPCell insertarTextoCelda(String texto, String fuente, int alineacion) throws DocumentException {

        PdfPCell celda = new PdfPCell();
        Paragraph parrafo = null;
fuente = fuente.toLowerCase();
        switch (fuente) {
            case "titulo":
                parrafo = new Paragraph(texto, titulo);
                break;
            case "contenido":
                parrafo = new Paragraph(texto, contenido);
                break;
        }

        parrafo.setAlignment(alineacion);
        celda.addElement(parrafo);
        celda.setBorderWidth(0);
        return celda;
    }

    /**
     *
     * Recibe como parametros el texto, fuente, si se debe subrayar el texto o
     * no, y el espacio del texto dentro de la celda. Una ves recibidos estos
     * parametros, se realiza la creacion de la celda. Terminada la creacion de
     * la celda se retorna.
     *
     * @param texto texto a escribir en el documento pdf.
     * @param fuente tipo de fuente a utilizar en el texto.
     * @param subrayar indica si se debe subrayar un texto.
     * @param padIzquierdo espacio izquierdo del texto en la celda.
     * @param padArriba espacio superior del texto en la celda.
     * @param padDerecho espacio derecho del texto en la celda.
     * @param padAbajo espacio inferior del texto en la celda.
     * @return retorna una celda de tipo PdfCell.
     * @throws DocumentException
     */
    public PdfPCell insertarTextoCelda(String texto, String fuente, boolean subrayar, int padIzquierdo, int padArriba, int padDerecho, int padAbajo) throws DocumentException {

        PdfPCell celda = new PdfPCell();

        Paragraph parrafo = null;

        switch (fuente) {
            case "titulo":
                parrafo = new Paragraph(texto, titulo);
                break;
            case "contenido":
                parrafo = new Paragraph(texto, contenido);
                break;
            case "nombresAp":
                parrafo = new Paragraph(texto, contNombresApellidos);
                break;
        }

        celda.addElement(parrafo);
        celda.setBorderWidthTop(0);
        celda.setBorderWidthLeft(0);
        celda.setBorderWidthRight(0);

        celda.setPaddingLeft(padIzquierdo);
        celda.setPaddingRight(padDerecho);
        celda.setPaddingTop(padArriba);
        celda.setPaddingBottom(padAbajo);
        if (!subrayar) {
            celda.setBorderWidthBottom(0);
        }
        return celda;
    }

    /**
     * Recibe como parametros el texto, fuente, espacios del texto dentro de la
     * celda y un atributo que permite colocar una ubicacion del texto
     * horizontalmente en el documento. Una ves recibidos estos parametros, se
     * realiza la creacion de la celda, terminada la creacion de esta celda se
     * retorna.
     *
     * @param texto texto a escribir en el documento pdf.
     * @param fuente tipo de fuente a utilizar en el texto.
     * @param padIzquierdo espacio izquierdo del texto dentro de la celda.
     * @param padArriba espacio superior del texto dentro de la celda.
     * @param padDerecho espacio derecho del texto dentro de la celda.
     * @param padAbajo espacio inferior del texto dentro de la celda.
     * @param alineacion ubicacion del texto.
     * @return retorna una celda de tipo PdfCell.
     * @throws DocumentException
     */
    public PdfPCell insertarTextoCelda(String texto, String fuente, int padIzquierdo, int padArriba, int padDerecho, int padAbajo, int alineacion) throws DocumentException {

        PdfPCell celda = new PdfPCell();

        Paragraph parrafo = null;

        switch (fuente) {
            case "titulo":
                parrafo = new Paragraph(texto, titulo);
                break;
            case "contenido":
                parrafo = new Paragraph(texto, contenido);
                break;
        }

        paragrafo.setAlignment(alineacion);
        celda.addElement(parrafo);
        celda.setBorderWidth(0);
        celda.setPaddingLeft(padIzquierdo);
        celda.setPaddingRight(padDerecho);
        celda.setPaddingTop(padArriba);
        celda.setPaddingBottom(padAbajo);

        return celda;
    }

    /**
     * Crear una nueva pagina en el documento pdf.
     */
    public void nuevaPagina() {
        this.documento.newPage();
    }
}
