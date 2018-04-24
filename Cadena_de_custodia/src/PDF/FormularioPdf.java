/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PDF;

import DAO.Analisis;
import DAO.Caso;
import DAO.Evidencia;
import PDF.Formulario.LogicaForm;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.FileNotFoundException;

/**
 *
 * @author alexa
 */
public class FormularioPdf {

    private GenerarFormularioInscripcion objGenerarFormularioInscripcion;
    private boolean nuevaPagina;
    private String nombre_pdf;
    private LogicaForm logica;

    /**
     * Permite llevar la cuenta de secciones agregadas en Datos Familiares
     */
    private int sectionCounter;

    public FormularioPdf() {
        nombre_pdf = "Informe Forense";
        objGenerarFormularioInscripcion = new GenerarFormularioInscripcion();
        nuevaPagina = false;
        logica = new LogicaForm();
    }

   
    public boolean crearInformeForense(String nombreFormulario, Caso caso) {
        try {
            crearFormulario(nombreFormulario, caso);
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        } catch (DocumentException ex) {
            return false;
        }

    }

    /**
     * Metodo utilizado para la construccion del pdf, es el encargado de
     * realizar la delegacion a otros metodos para ir construyendo el
     * formulario. A cada metodo se le delega una parte del registro. Ademas de
     * lo anterior, este metodo es el encargado de construir el encabezado y pie
     * de pagina del pdf.
     *
     * @param nombre_pdf Nombre que se le va a asignar al pdf generado.
     * @param padre Datos referentes al padre del aspirante.
     * @param madre Datos referentes a la madre del aspirante.
     * @param acudiente Datos referentes al acudiente del aspirante.
     * @param aspirante Datos del aspirante inscrito.
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    private void crearFormulario(String nombreFormulario, Caso caso) throws FileNotFoundException, DocumentException {
        PdfPTable tabla;
        nombre_pdf = "INFORME_FORENSE_CASO_ "+caso.getReferencia();

        objGenerarFormularioInscripcion.crear_archivo(nombre_pdf);
        objGenerarFormularioInscripcion.nuevaPagina();
        objGenerarFormularioInscripcion.crear_encabezado();
        ingresarInfoCaso(caso);

        tabla = objGenerarFormularioInscripcion.crearTabla(1);
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("NOTA: CERTIFICO QUE LA INFORMACIÓN AQUI CONSIGNADA ES EXACTA Y AUTORIZO SU VERIFICACIÓN SIN RESTRICCIÓN ALGUNA.", "titulo", 0, 30, 0, 0, 0));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("____________________________________", "contenido", false, 120, 50, 0, 0));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Firma", "contenido", false, 210, 0, 0, 0));
        objGenerarFormularioInscripcion.insertarTabla(tabla);
        objGenerarFormularioInscripcion.cerrarDocumento();
    }

    /**
     * Recibe como parametro un objeto de tipo Aspirante, el cual contiene los
     * datos del aspirante. Una ves recibido este objeto, se inicia la escritura
     * de los datos del aspirante en el pdf. Este metodo realiza el llamado de
     * otros metodos para apoyarse en la escritura de los datos en el documento
     * pdf. Estos metodos son: agregarDatosPersonalesEstudiante(aspirante);
     * agregarDatosUbicacionEstudiante(aspirante);
     * agregarDatosReligionEstudiante(aspirante);
     * agregarColegioProcedenciaEstudiante(aspirante.colegio());
     * agregarHermanos(aspirante.getHermanos());
     *
     * @param aspirante Datos del aspirante inscrito.
     * @throws DocumentException
     */
    private void ingresarInfoCaso(Caso caso) throws DocumentException {
        PdfPTable tabla;

        tabla = objGenerarFormularioInscripcion.crearTabla(1);
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("CASO: " + caso.getReferencia(), "titulo", Element.ALIGN_JUSTIFIED));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Detalles: \n " + caso.getDetalles(), "contenido", Element.ALIGN_JUSTIFIED));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Duracion: \n " + caso.getDuracion(), "contenido", Element.ALIGN_JUSTIFIED));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Observaciones: \n " + caso.getObservaciones(), "contenido", Element.ALIGN_JUSTIFIED));
        objGenerarFormularioInscripcion.insertarTabla(tabla);

//        insertarEvidencia(tabla);        
        for (Evidencia ev : logica.getCasolog().listarEvidencia(caso.getId())) {
            insertarEvidencia(ev);
        }

    }

    /**
     * Recibe como parametro un objeto de tipo Aspirante, el cual contiene los
     * datos de un aspirante. Este metodo se encarga de la escritura en el
     * documento pdf de datos como; nombre, apellidos, edad, y fecha de
     * nacimiento del aspirante.
     *
     * @param aspirante Datos del aspirante inscrito.
     * @throws DocumentException
     */
    private void insertarEvidencia(Evidencia ev) throws DocumentException {
        PdfPTable tabla;
        tabla = objGenerarFormularioInscripcion.crearTabla(2);
//        tabla.addCell(objGenerarFormularioInscripcion.space());
//        tabla.addCell(objGenerarFormularioInscripcion.space());
//        tabla.addCell(objGenerarFormularioInscripcion.space());

        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Referencia", "tItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(ev.getReferencia(), "contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Nombre", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(ev.getNombre(), "contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Descripcion", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(ev.getDescripcionInforme(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Observaciones", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(ev.getObservaciones(), "Contenido", Element.ALIGN_LEFT));

        objGenerarFormularioInscripcion.insertarTabla(tabla);

        for (Analisis an : logica.getEvidencialog().lstAnalisis(ev.getId())) {
            insertarAnalisis(an);
        }

    }

    /**
     * Recibe como parametro un objeto de tipo Aspirante, el cual contiene todos
     * los datos del aspirante. Este metodo se encarga de hacer posible la
     * escritura en el documento pdf de datos como: el departamento, Municipio,
     * Barrio, Direccion de residencia y telefono del aspirante.
     *
     * @param aspirante Datos del aspirante inscrito.
     * @throws DocumentException
     */
    private void insertarAnalisis(Analisis analisis) throws DocumentException {
        PdfPTable tabla;
        tabla = objGenerarFormularioInscripcion.crearTabla(2);
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Fecha Inicio", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getFini(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Fecha Fin", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getFfin(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Nombre", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getNombre(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Herramientas", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getHerramientas(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Resultados", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getResultado(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Observaciones", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getObservaciones(), "Contenido", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda("Realizado por: ", "TItulo", Element.ALIGN_LEFT));
        tabla.addCell(objGenerarFormularioInscripcion.insertarTextoCelda(analisis.getPerito(), "Contenido", Element.ALIGN_LEFT));

        objGenerarFormularioInscripcion.insertarTabla(tabla);

    }

}
