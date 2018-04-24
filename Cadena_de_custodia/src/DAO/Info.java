/*-  Javier Agredo 22/04/2018 - javiguittar@gmail.com -*/
package DAO;

/**
 *
 * @author javier
 */
public class Info {

    private int idPersona;
    private int idCaso;
    private int idEvidencia;

    private String personaNombre;
    private String casoRef;
    private String evidenciaNombre;
    private String evidenciaRef;
    private String rol;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getCasoRef() {
        return casoRef;
    }

    public void setCasoRef(String casoRef) {
        this.casoRef = casoRef;
    }

    public String getEvidenciaNombre() {
        return evidenciaNombre;
    }

    public void setEvidenciaNombre(String evidenciaNombre) {
        this.evidenciaNombre = evidenciaNombre;
    }

    public String getEvidenciaRef() {
        return evidenciaRef;
    }

    public void setEvidenciaRef(String evidenciaRef) {
        this.evidenciaRef = evidenciaRef;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
