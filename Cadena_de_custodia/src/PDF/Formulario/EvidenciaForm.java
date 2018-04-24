/*-  Javier Agredo 23/04/2018 - javiguittar@gmail.com -*/

package PDF.Formulario;

/**
 *
 * @author javier
 */
public class EvidenciaForm extends DAO.Evidencia {
    
    public EvidenciaForm(int idCaso, int idEncargado, String referencia, String nombre, String descripcion, String observaciones) {
        super(idCaso, idEncargado, referencia, nombre, descripcion, observaciones);
    }
    
}
