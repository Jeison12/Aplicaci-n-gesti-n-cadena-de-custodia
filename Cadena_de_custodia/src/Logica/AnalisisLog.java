/*-  Javier Agredo 22/04/2018 - javiguittar@gmail.com -*/

package Logica;

import DAO.Analisis;
import DAOBD.AnalisisBD;

/**
 *
 * @author javier
 */
public class AnalisisLog {
    private Analisis objAnalisis;
    private AnalisisBD analisisBD;

    public AnalisisLog() {
        analisisBD = new AnalisisBD();
    }
        
    public boolean nuevoAnalisis() {
        int result = analisisBD.nuevoAnalisis(objAnalisis);
        if(result>0){
            objAnalisis.setIdAnalisis(result);
            return true;
        }
        return false;
    }

    public Analisis getObjAnalisis() {
        return objAnalisis;
    }

    public void setObjAnalisis(Analisis objAnalisis) {
        this.objAnalisis = objAnalisis;
    }
    
    

}
