/*-  Javier Agredo 17/04/2018 - javiguittar@gmail.com -*/

package DAO;

/**
 *
 * @author javier
 */
public class Evidencia {
    private int id;
    private int idCaso;
    private int idEncargado;
    private String referencia;
    private String nombre;
    private String descripcion;
    private String observaciones;

    public Evidencia(int idCaso, int idEncargado, String referencia, String nombre, String descripcion, String observaciones) {
        this.idCaso = idCaso;
        this.idEncargado = idEncargado;
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }
    
     public Evidencia(int idEv,int idCaso, int idEncargado, String referencia, String nombre, String descripcion, String observaciones) {
         this.id = idEv;
        this.idCaso = idCaso;
        this.idEncargado = idEncargado;
        this.referencia = referencia;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(int idCaso) {
        this.idCaso = idCaso;
    }

    public int getIdEncargado() {
        return idEncargado;
    }

    public void setIdEncargado(int idEncargado) {
        this.idEncargado = idEncargado;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcionInforme() {
        return descripcion;
    }
  
    
    
}
