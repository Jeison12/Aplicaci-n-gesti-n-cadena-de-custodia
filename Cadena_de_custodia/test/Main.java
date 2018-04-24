
import Logica.CasoLog;
import PDF.FormularioPdf;

/*-  Javier Agredo 23/04/2018 - javiguittar@gmail.com -*/

/**
 *
 * @author javier
 */
public class Main {
    public static void main(String[] args) {
        FormularioPdf formulario = new FormularioPdf();
        CasoLog log = new CasoLog();
        int idCaso = 1;
        formulario.crearInformeForense("Informe final caso ("+idCaso+")", log.getCaso(idCaso));
    }
}
