/*-  Javier Agredo 11/04/2018 - javiguittar@gmail.com -*/
package DAO;

import java.util.Date;

/**
 *
 * @author javier
 */
public class Caso {

    private int id;
    private String referencia;
    private Date fechaSuceso;
    private String duracion;
    private String detalles;
    private String area;
    private String dependencia;
    private String observaciones;

    private String rol;

    public Caso() {
    }

    
    
    public Caso(int id, String referencia, String detalles, String rol) {
        this.id = id;
        this.referencia = referencia;
        this.detalles = detalles;
        this.rol = rol;
    }

    public Caso(int id, String referencia, Date fechaSuceso, String duracion, String detalles, String area, String dependencia, String observaciones, String rol) {
        this.id = id;
        this.referencia = referencia;
        this.fechaSuceso = fechaSuceso;
        this.duracion = duracion;
        this.detalles = detalles;
        this.area = area;
        this.dependencia = dependencia;
        this.observaciones = observaciones;
        this.rol = rol;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Date getFechaSuceso() {
        return fechaSuceso;
    }

    public void setFechaSuceso(Date fechaSuceso) {
        this.fechaSuceso = fechaSuceso;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Caso{" + "id=" + id + ", referencia=" + referencia + ", detalles=" + detalles + ", rol=" + rol + '}';
    }

    
    
}
