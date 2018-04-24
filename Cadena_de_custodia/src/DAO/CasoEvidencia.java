/*-  Javier Agredo 21/04/2018 - javiguittar@gmail.com -*/
package DAO;

/**
 *
 * @author javier
 */
public class CasoEvidencia {
//    private int idCaso;

    private int idEv;
    private String referencia;
    private String nombre;
    private int totalAnalisis;
    //Ultima persona que analiza
    private String personaNombre;
    private String personaApellidos;

    public CasoEvidencia() {
    }

    public CasoEvidencia(int idEv, String referencia, String nombre, int totalAnalisis, String personaNombre, String personaApellidos) {
        this.idEv = idEv;
        this.referencia = referencia;
        this.nombre = nombre;
        this.totalAnalisis = totalAnalisis;
        this.personaNombre = personaNombre;
        this.personaApellidos = personaApellidos;
    }

    public int getIdEv() {
        return idEv;
    }

    public void setIdEv(int idEv) {
        this.idEv = idEv;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTotalAnalisis() {
        return totalAnalisis;
    }

    public void setTotalAnalisis(int totalAnalisis) {
        this.totalAnalisis = totalAnalisis;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getPersonaApellidos() {
        return personaApellidos;
    }

    public void setPersonaApellidos(String personaApellidos) {
        this.personaApellidos = personaApellidos;
    }

    public String perito() {
        if (personaNombre == null || personaNombre.isEmpty()) {
            return "";
        }
        return personaNombre + " " + personaApellidos;
    }

}
