/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/

package Logica;

import DAO.Perito;
import DAOBD.EmpresaBD;
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class EmpresaLog {
    private EmpresaBD empresaBD;    

    public EmpresaLog() {
        this.empresaBD = new EmpresaBD();
    }
    
    public boolean validarClaveEmpresa(String clave){
        return empresaBD.validarClaveEmpresa(clave);
    }   

    public ArrayList<Perito> lstPeritos(int idEmpresa, int idPersona) {
        
        return empresaBD.lstPeritos(idEmpresa, idPersona);
    }

}
