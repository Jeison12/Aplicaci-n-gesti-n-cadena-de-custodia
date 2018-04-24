/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/

package DAOBD;

import DAO.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class UsuarioBD extends TemplateBD{

    private Usuario usuario;    
    
    public UsuarioBD() {
        super();        
    }
    
    public Usuario validarUsuario(String user, String pass){
        usuario = null;//?
        
        cn = dbCon.getConnection();
        
        try {
            cs = cn.prepareCall("SELECT PER_ID, PER_NOMBRE, PER_APELLIDOS FROM PERSONA WHERE PER_USUARIO = ? AND PER_PASS = ? ");
            cs.setString(1, user);
            cs.setString(2, pass);
            
            rs = cs.executeQuery();
            
            if(rs.next()){                
                usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3));
                estadoConsulta = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }  
    
}
