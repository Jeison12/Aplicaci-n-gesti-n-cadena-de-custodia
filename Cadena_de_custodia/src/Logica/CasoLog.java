/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/
package Logica;

import DAO.Caso;
import DAO.CasoEvidencia;
import DAO.Evidencia;
import DAO.Perito;
import DAO.Solicitud;
import DAOBD.CasoBD;
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class CasoLog {

    private CasoBD casobd;
    private Caso objCaso;
    private ArrayList lstCasos;

    public CasoLog() {
        casobd = new CasoBD();
        objCaso = new Caso();
        lstCasos = new ArrayList();
    }

    private String nombreCaso() {
        return "";
    }
    
    public Caso getCaso(int idCaso){
        return casobd.getCaso(idCaso);
    }

    public boolean existeReferencia(String text) {
        return casobd.existeReferencia(text);
    }

    public boolean registrarCaso(int idPersona) {
        return casobd.registrarCaso(objCaso, idPersona);
    }

    public Caso getObjCaso() {
        return objCaso;
    }

    public void setObjCaso(Caso objCaso) {
        this.objCaso = objCaso;
    }

    public ArrayList<Caso> listarCasos(int id_persona) {
        if(lstCasos.isEmpty()){
            lstCasos.addAll(casobd.listaCasos(id_persona));
        }
        return lstCasos;
    }

    public int getIdCaso(String refCaso) {
        return casobd.getIdCaso(refCaso);
    }

    public ArrayList<CasoEvidencia> listarEvidenciaCaso(int idCaso) {
        return casobd.listaEvidenciaCaso(idCaso);
    }
    
    public ArrayList<Evidencia> listarEvidencia(int idCaso) {
        return casobd.listaEvidencia(idCaso);
    }

    public String rolEnCaso(int idCaso, int idPersona) {
        return casobd.rolEnCaso(idCaso, idPersona);
    }

    public int asignarCaso(int idPersona, int idCaso, int rol) {
        return casobd.asignarCaso(idPersona, idCaso, rol);
    }

    public ArrayList<Perito> listarPeritos(int idCaso) {
        return casobd.listarPeritos(idCaso);
    }

    public int tieneSolicitudes(int id_persona) {
        return casobd.tieneSolicitudes(id_persona);
    }

    public ArrayList<Solicitud> lstSolicitudes(int idPersona) {
        return casobd.lstSolicitudes(idPersona);
    }

    public int aceptarSolicitud(int idsolicitud) {
        return casobd.aceptarSolicitud(idsolicitud);
    }

}
