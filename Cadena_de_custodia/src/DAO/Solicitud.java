/*-  Javier Agredo 23/04/2018 - javiguittar@gmail.com -*/

package DAO;

/**
 *
 * @author javier
 */
public class Solicitud {
    private int idSolicitud;
    private int idCaso;
    private String ref;
    private String detalles;

    public Solicitud(int idSolicitud, int idCaso, String ref, String detalles) {
        this.idSolicitud = idSolicitud;
        this.idCaso = idCaso;
        this.ref = ref;
        this.detalles = detalles;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    
    
    
    

}
