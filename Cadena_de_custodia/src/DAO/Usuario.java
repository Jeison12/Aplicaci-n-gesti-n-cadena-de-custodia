/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/
package DAO;

/**
 *
 * @author javier
 */
public class Usuario {

    private int id_persona;
    private String usuario;
    private String pass;
    private String nombre;
    private String apellidos;

    public Usuario() {
    }

    public Usuario(int id_persona, String nombre, String apellidos) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

}
