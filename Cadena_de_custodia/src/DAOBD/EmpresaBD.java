/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/
package DAOBD;

import DAO.Perito;
import DB.DBConection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class EmpresaBD extends TemplateBD {

    public EmpresaBD() {
        dbCon = new DBConection();
    }

    public boolean validarClaveEmpresa(String clave) {
        System.out.println("Validando clave empresa: " + clave);
        cn = dbCon.getConnection();
        estadoConsulta = false;
        try {
            cs = cn.prepareCall("SELECT COUNT(EM_ID) FROM EMPRESA WHERE EM_CLAVE = ?");
            cs.setString(1, clave);
            rs = cs.executeQuery();

            if (rs.next()) {
                estadoConsulta = (rs.getInt(1) != 0);
            }

        } catch (SQLException e) {
            System.out.println(" error en el metodo validarClaveEmpresa " + e.toString());
        } finally {
            close_connection_rs(cn);
        }
        return estadoConsulta;
    }

    public ArrayList<Perito> lstPeritos(int idEmpresa, int idPersona) {
        ArrayList peritos = new ArrayList();

        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT P.PER_ID,PER_USUARIO,PER_NOMBRE, PER_APELLIDOS,EM_NOMBRE  FROM PERSONA P\n"
                    + "	JOIN TRABAJA T ON P.PER_ID = T.PER_ID\n"
                    + "	JOIN EMPRESA E ON T.EM_ID = E.EM_ID\n"
                    + "	WHERE E.EM_ID = ? AND P.PER_ID != ?");
            cs.setInt(1, idEmpresa);
            cs.setInt(2, idPersona);
            
            rs = cs.executeQuery();
            while (rs.next()) {
                peritos.add(new Perito(rs.getInt("PER_ID"), rs.getString("PER_NOMBRE") + " " + rs.getString("PER_APELLIDOS"), rs.getString("PER_USUARIO")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            close_connection_rs(cn);
        }

        return peritos;
    }
}
