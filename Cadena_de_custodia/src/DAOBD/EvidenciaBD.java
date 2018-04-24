/*-  Javier Agredo 21/04/2018 - javiguittar@gmail.com -*/
package DAOBD;

import DAO.Analisis;
import DAO.Evidencia;
import Logica.Utils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.security.krb5.internal.rcache.DflCache;

/**
 *
 * @author javier
 */
public class EvidenciaBD extends TemplateBD {

    public int nuevaEvidencia(Evidencia objEvidencia) {
        cn = dbCon.getConnection();
        int result = -2;
        try {
            /*NUEVA_EVIDENCIA(IN ID_PER INTEGER,IN ID_CASO INTEGER,
            EREF TEXT, NOM TEXT, DES TEXT, OBS TEXT, FECHA DATE)*/
            cs = cn.prepareCall("SELECT NUEVA_EVIDENCIA(?,?,?,?,?,?,?)");
            cs.setInt(1, objEvidencia.getIdEncargado());
            cs.setInt(2, objEvidencia.getIdCaso());
            cs.setString(3, objEvidencia.getReferencia());
            cs.setString(4, objEvidencia.getNombre());
            cs.setString(5, objEvidencia.getDescripcion());
            cs.setString(6, objEvidencia.getObservaciones());
            cs.setDate(7, Utils.getDateSql(new Date()));

            rs = cs.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvidenciaBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }
        return result;
    }

    public ArrayList<Analisis> lstAnalisis(int idEvidencia) {
        ArrayList lista = new ArrayList();
        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT AN_FECHAINICIO, AN_FECHAFIN, AN_NOMBRE, AN_HERRAMIENTAS,\n"
                    + "	AN_RESULTADOS, AN_OBSERVACIONES, PER_NOMBRE, PER_APELLIDOS\n"
                    + "	FROM ANALISIS A\n"
                    + "JOIN PERSONA P ON A.PER_ID = P.PER_ID\n"
                    + " WHERE EV_ID = " + idEvidencia);

            rs = cs.executeQuery();
            while (rs.next()) {
                lista.add(new Analisis(rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), Utils.dateToString(rs.getDate(1)), Utils.dateToString(rs.getDate(2)),
                rs.getString(7)+rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvidenciaBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }

        return lista;
    }

}
