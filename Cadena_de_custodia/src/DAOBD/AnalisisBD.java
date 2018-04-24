/*-  Javier Agredo 22/04/2018 - javiguittar@gmail.com -*/
package DAOBD;

import DAO.Analisis;
import Logica.Utils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class AnalisisBD extends TemplateBD {

    public int nuevoAnalisis(Analisis objAnalisis) {
        int result = 0;
        cn = dbCon.getConnection();
        //TODO: Hacer funcion para retornar maxID
        try {
            cs = cn.prepareCall("SELECT NUEVO_ANALISIS(?,?,?,?,?,?,?,?)");
            cs.setInt(1, objAnalisis.getIdPerito());
            cs.setInt(2, objAnalisis.getIdEvidencia());
            cs.setDate(3, Utils.getDateSql(objAnalisis.getFechaInicio()));
            cs.setDate(4, Utils.getDateSql(objAnalisis.getFechaFin()));
            cs.setString(5, objAnalisis.getNombre());
            cs.setString(6, objAnalisis.getHerramientas());
            cs.setString(7, objAnalisis.getResultado());
            cs.setString(8, objAnalisis.getObservaciones());
            
            rs  = cs.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AnalisisBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            close_connection_rs(cn);
        }

        return result;
    }

}
