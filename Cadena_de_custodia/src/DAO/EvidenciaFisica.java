/*-  Javier Agredo 21/04/2018 - javiguittar@gmail.com -*/
package DAO;

/**
 *
 * @author javier
 */
public class EvidenciaFisica extends Evidencia {

    private String ip;
    private String marca;
    private String modelo;
    private String ram;
    private String dd;
    private String cpu;
    private String funcion;
    private String info;

    public EvidenciaFisica(int idCaso, int idEncargado, String referencia, String nombre, String descripcion, String observaciones) {
        super(idCaso, idEncargado, referencia, nombre, descripcion, observaciones);
    }

    public EvidenciaFisica(String ip, String marca, String modelo, String ram, String dd, String cpu, String funcion, String info, int idCaso, int idEncargado, String referencia, String nombre, String descripcion, String observaciones) {
        super(idCaso, idEncargado, referencia, nombre, descripcion, observaciones);
        this.ip = ip;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.dd = dd;
        this.cpu = cpu;
        this.funcion = funcion;
        this.info = info;
    }

    @Override
    public String getDescripcionInforme() {
        return super.getDescripcionInforme()+info(); 
    }
    
    public String info(){
        return "\n" + "Ip: " + ip + "\nMarca: " + marca + "\nModelo: " + modelo + "Ram: "  + ram + "DD: " + dd + "\nCPU: " + cpu + "\nFuncion: " + funcion + "\nInformacion manejada: " + info + '}';
    }

    

}
