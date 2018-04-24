/*-  Javier Agredo 21/04/2018 - javiguittar@gmail.com -*/

package Logica;

import DAO.Analisis;
import DAO.Evidencia;
import DAO.EvidenciaFisica;
import DAOBD.EvidenciaBD;
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class EvidenciaLog {

    private Evidencia objEvidencia;
    private EvidenciaFisica objEvidenciaFisica;
    
    private EvidenciaBD evidenciaBD;

    public EvidenciaLog() {
          evidenciaBD = new EvidenciaBD();
    }

    public Evidencia getObjEvidencia() {
        return objEvidencia;
    }

    public void setObjEvidencia(Evidencia objEvidencia) {
        this.objEvidencia = objEvidencia;
    }

    public EvidenciaFisica getObjEvidenciaFisica() {
        return objEvidenciaFisica;
    }

    public void setObjEvidenciaFisica(EvidenciaFisica objEvidenciaFisica) {
        this.objEvidenciaFisica = objEvidenciaFisica;
    }

    public boolean nuevaEvidencia() {
        int result = evidenciaBD.nuevaEvidencia(objEvidencia);
        if(result>0){
            objEvidencia.setId(result);
            return true;
        }
        return false;
    }

    public ArrayList<Analisis> lstAnalisis(int id) {
        return evidenciaBD.lstAnalisis(id);
    }
    
    
    
    
    
}
