/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/
package DAOBD;

import DAO.Persona;
import DB.DBConection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class PersonaBD extends TemplateBD {

    boolean estado;

    public PersonaBD() {
        dbCon = new DBConection();
    }

    
    
    public boolean registrar(Persona objPersona) {
        cn = dbCon.getConnection();
        try {
            cs = cn.prepareCall("SELECT NUEVA_PERSONA(?,?,?,?,?,?,?,?)");
            cs.setString(1, objPersona.getUsuario());
            cs.setString(2, objPersona.getPass());
            cs.setString(3, objPersona.getNombres());
            cs.setString(4, objPersona.getApellidos());
            cs.setString(5, objPersona.getIdentificacion());
            cs.setString(6, objPersona.getCelular());
            cs.setString(7, objPersona.getTelefono());
            cs.setString(8, objPersona.getEmail());           
            
            rs = cs.executeQuery();
            if(rs.next()){
                objPersona.setId(rs.getInt(1));
                estado = true;                
            }
        } catch (SQLException e) {
            System.out.println(" error en el metodo registrarPersonaBD " + e.toString());
        } finally {
            close_connection(cn);
        }
        return estado;
    }
    
     public boolean existeUsuario(String usuario) {
        cn = dbCon.getConnection();
        estado = true;
        try {
            cs = cn.prepareCall("SELECT COUNT(PER_ID) FROM PERSONA WHERE PER_USUARIO = ?");        
            cs.setString(1, usuario);            
            rs = cs.executeQuery();
            
            if(rs.next()){
                if(rs.getInt(1)==0){
                    estado = false;
                }
            }
            
        } catch (SQLException e) {
            System.out.println(" error en el metodo existeUsuario " + e.toString());
        } finally {
            close_connection_rs(cn);
        }
        return estado;
    }

}
