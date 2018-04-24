/*-  Javier Agredo 22/04/2018 - javiguittar@gmail.com -*/
package DAO;

import java.util.Date;

/**
 *
 * @author javier
 */
public class Analisis {

    private int idAnalisis;
    private int idEvidencia;
    private int idPerito;
    private String nombre;
    private Date fechaInicio;
    private Date fechaFin;
    private String herramientas;
    private String resultado;
    private String observaciones;
    
    private String fini;
    private String ffin;
    private String perito;

    public Analisis(int idAnalisis, int idEvidencia, int idPerito, String nombre, Date fechaInicio, Date fechaFin, String herramientas, String resultado, String observaciones) {
        this.idAnalisis = idAnalisis;
        this.idEvidencia = idEvidencia;
        this.idPerito = idPerito;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.herramientas = herramientas;
        this.resultado = resultado;
        this.observaciones = observaciones;
    }

    
    
    public Analisis(int idEvidencia, int idPerito, String nombre, Date fechaInicio, Date fechaFin, String herramientas, String resultado, String observaciones) {
        this.idEvidencia = idEvidencia;
        this.idPerito = idPerito;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.herramientas = herramientas;
        this.resultado = resultado;
        this.observaciones = observaciones;
    }

    public Analisis(String nombre,  String herramientas, String resultado, String observaciones, String fini, String ffin, String perito) {
        this.nombre = nombre;
        this.herramientas = herramientas;
        this.resultado = resultado;
        this.observaciones = observaciones;
        this.fini = fini;
        this.ffin = ffin;
        this.perito = perito;
    }
    
    

    public int getIdAnalisis() {
        return idAnalisis;
    }

    public void setIdAnalisis(int idAnalisis) {
        this.idAnalisis = idAnalisis;
    }

    public int getIdEvidencia() {
        return idEvidencia;
    }

    public void setIdEvidencia(int idEvidencia) {
        this.idEvidencia = idEvidencia;
    }

    public int getIdPerito() {
        return idPerito;
    }

    public void setIdPerito(int idPerito) {
        this.idPerito = idPerito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHerramientas() {
        return herramientas;
    }

    public void setHerramientas(String herramientas) {
        this.herramientas = herramientas;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFini() {
        return fini;
    }

    public void setFini(String fini) {
        this.fini = fini;
    }

    public String getFfin() {
        return ffin;
    }

    public void setFfin(String ffin) {
        this.ffin = ffin;
    }

    public String getPerito() {
        return perito;
    }

    public void setPerito(String perito) {
        this.perito = perito;
    }

    
}
