/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/

package DAOBD;

import DB.DBConection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author javier
 */
public class TemplateBD {
    protected Connection cn;
    protected CallableStatement cs;
    protected ResultSet rs;
    protected DBConection dbCon;
    
    protected boolean estadoConsulta;

    public TemplateBD() {
        dbCon = new DBConection();
    }
    
    

    protected void close_connection_rs(Connection cn) {
        try {
            cs.close();
            rs.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("(TemplateDAO-Exception) SQLException at " + this.getClass().getName());
        } catch (Exception n) {
            //System.out.println("(TemplateDAO-Exception) NullPointerException at " + this.getClass().getName());
        }
    }

    protected void close_connection(Connection cn) {
        try {
            cs.close();
            cn.close();
        } catch (SQLException ex) {
            //Logger.getLogger(TemplateDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
