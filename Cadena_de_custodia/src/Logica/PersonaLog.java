/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/
package Logica;

import DAO.Persona;
import DAOBD.PersonaBD;

/**
 *
 * @author javier
 */
public class PersonaLog {

    private Persona objPersona;
    private PersonaBD personaBD;

    public PersonaLog() {
        objPersona = new Persona();
        personaBD = new PersonaBD();
    }

    public boolean registrar() {
        return personaBD.registrar(objPersona);
    }
    
    public boolean existeEnBd(String usuario) {
        return personaBD.existeUsuario(usuario);
    }
    

    public Persona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(Persona objPersona) {
        this.objPersona = objPersona;
    }

    

}
