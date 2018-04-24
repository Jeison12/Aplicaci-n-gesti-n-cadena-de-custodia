/*-  Javier Agredo 23/04/2018 - javiguittar@gmail.com -*/

package PDF.Formulario;

/**
 *
 * @author javier
 */
public class EvidenciaFisicaForm extends DAO.EvidenciaFisica{
    
    public EvidenciaFisicaForm(String ip, String marca, String modelo, String ram, String dd, String cpu, String funcion, String info, int idCaso, int idEncargado, String referencia, String nombre, String descripcion, String observaciones) {
        super(ip, marca, modelo, ram, dd, cpu, funcion, info, idCaso, idEncargado, referencia, nombre, descripcion, observaciones);
    }
    
}
