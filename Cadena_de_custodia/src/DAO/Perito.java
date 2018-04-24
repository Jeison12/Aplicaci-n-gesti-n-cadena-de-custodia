/*-  Javier Agredo 22/04/2018 - javiguittar@gmail.com -*/

package DAO;

/**
 *
 * @author javier
 */
public class Perito {
    private int idPersona;
    private String nombreCompleto;
    private String rol;
    private String usuario;

    public Perito(int idPersona, String nombreCompleto) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombreCompleto;
    }

    public Perito(int idPersona, String nombreCompleto, String usuario) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
    }
    
    

    public Perito(int idPersona,  String usuario, String nombreCompleto, String rol) {
        this.idPersona = idPersona;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
        this.usuario = usuario;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    
    @Override
    public String toString() {
        return nombreCompleto;
    }
    
    
}
