/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/
package DAOBD;

import DAO.Analisis;
import DAO.Caso;
import DAO.CasoEvidencia;
import DAO.Evidencia;
import DAO.Perito;
import DAO.Solicitud;
import Logica.Utils;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class CasoBD extends TemplateBD {

    private ArrayList lstCasos;
    private ArrayList lstEvidenciaCaso;

    public CasoBD() {
        super();
    }

    public boolean existeReferencia(String ref) {
        cn = dbCon.getConnection();
        estadoConsulta = true;
        try {
            cs = cn.prepareCall("SELECT COUNT(CAS_ID) FROM CASO WHERE CAS_REFERENCIA = ?");
            cs.setString(1, ref);
            rs = cs.executeQuery();

            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    estadoConsulta = false;
                }
            }

        } catch (SQLException e) {
            System.out.println(" error en el metodo existeReferencia" + e.toString());
        } finally {
            close_connection_rs(cn);
        }
        return estadoConsulta;
    }

    public boolean registrarCaso(Caso objCaso, int id_persona) {
        cn = dbCon.getConnection();
        try {
            cs = cn.prepareCall("SELECT NUEVO_CASO(?,?,?,?,?,?,?,?)");
            cs.setInt(1, id_persona);
            cs.setString(2, objCaso.getReferencia());
            java.sql.Date fecha = new Date(objCaso.getFechaSuceso().getTime());
            cs.setDate(3, fecha);
            cs.setString(4, objCaso.getDuracion());
            cs.setString(5, objCaso.getDetalles());
            cs.setString(6, objCaso.getArea());
            cs.setString(7, objCaso.getDependencia());
            cs.setString(8, objCaso.getObservaciones());

            rs = cs.executeQuery();
            if (rs.next()) {
                estadoConsulta = true;
                objCaso.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(" error en el metodo registrarCasoBD " + e.toString());
            estadoConsulta = false;
        } finally {
            close_connection(cn);
        }
        return estadoConsulta;
    }

    public ArrayList<Caso> listaCasos(int id_persona) {
        cn = dbCon.getConnection();
        lstCasos = new ArrayList();
        try {
            cs = cn.prepareCall("SELECT C.CAS_ID, CAS_REFERENCIA, IN_DETALLES,ROL_NOMBRE FROM CASO C "
                    + "	JOIN PERSONA_CASO PC "
                    + "		ON C.CAS_ID = PC.CAS_ID "
                    + "	JOIN ROL R "
                    + "		ON PC.ROL_ID = R.ROL_ID "
                    + "	WHERE PC.PER_ID = " + id_persona);
            rs = cs.executeQuery();
            while (rs.next()) {
                lstCasos.add(new Caso(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }
        return lstCasos;
    }

    public ArrayList<CasoEvidencia> listaEvidenciaCaso(int idCaso) {
        cn = dbCon.getConnection();
        lstEvidenciaCaso = new ArrayList();
        try {
            cs = cn.prepareCall("SELECT C.CAS_ID, E.EV_ID, REFERENCIA, NOMBRE, TOTALANALISIS, PER_NOMBRE, PER_APELLIDOS FROM CASO C\n"
                    + "	JOIN EVIDENCIA E\n"
                    + "		ON C.CAS_ID = E.CAS_ID\n"
                    + "	LEFT JOIN (SELECT COUNT(EV_ID) AS TOTALANALISIS, EV_ID FROM ANALISIS GROUP BY EV_ID )AS SUB \n"
                    + "	ON E.EV_ID = SUB.EV_ID\n"
                    + "	LEFT JOIN (SELECT EV.EV_ID, PER_NOMBRE, PER_APELLIDOS FROM ANALISIS A\n"
                    + "			JOIN EVIDENCIA EV ON A.EV_ID = EV.EV_ID\n"
                    + "			JOIN PERSONA P ON A.PER_ID = P.PER_ID			\n"
                    + "			WHERE EV.CAS_ID = " + idCaso + " "
                    + "			ORDER BY A.ID_ANALISIS DESC			\n"
                    + "			LIMIT 1) AS PERITO\n"
                    + "	ON E.EV_ID = PERITO.EV_ID\n"
                    + "	WHERE C.CAS_ID = " + idCaso);
            rs = cs.executeQuery();

            while (rs.next()) {
                lstEvidenciaCaso.add(new CasoEvidencia(rs.getInt("EV_ID"), rs.getString("REFERENCIA"), rs.getString("NOMBRE"), rs.getInt("TOTALANALISIS"), rs.getString("PER_NOMBRE"), rs.getString("PER_APELLIDOS")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }
        return lstEvidenciaCaso;
    }

    public int getIdCaso(String refCaso) {
        cn = dbCon.getConnection();
        int idCaso = 0;
        try {
            cs = cn.prepareCall("SELECT CAS_ID FROM CASO WHERE CAS_REFERENCIA = ?");
            cs.setString(1, refCaso);
            rs = cs.executeQuery();

            if (rs.next()) {
                idCaso = rs.getInt(1);
            }

        } catch (SQLException e) {
            idCaso = -1;
            System.out.println(" error en el metodo existeReferencia" + e.toString());
        } finally {
            close_connection_rs(cn);
        }
        return idCaso;
    }

    public String rolEnCaso(int idCaso, int idPersona) {
        String rol = "";
        cn = dbCon.getConnection();
        try {
            cs = cn.prepareCall("SELECT * FROM PERSONA_CASO PC "
                    + "JOIN ROL R ON PC.ROL_ID = R.ROL_ID "
                    + "WHERE PC.CAS_ID = ? AND PC.PER_ID = ?");
            cs.setInt(1, idCaso);
            cs.setInt(2, idPersona);
            rs = cs.executeQuery();
            if (rs.next()) {
                rol = rs.getString(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
            return rol;
        }
    }

    public int asignarCaso(int idPersona, int idCaso, int rol) {
        cn = dbCon.getConnection();
        int idSolicitud = 0;
        try {
//            cs = cn.prepareCall("INSERT INTO PERSONA_CASO VALUES (?,?,?)");
            cs = cn.prepareCall("SELECT AGREGARPERITOACASO(?,?,?)");
            cs.setInt(1, idPersona);
            cs.setInt(2, idCaso);
            cs.setInt(3, rol);

            rs = cs.executeQuery();
            if (rs.next()) {
                idSolicitud = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection(cn);
        }
        return idSolicitud;

    }

    public ArrayList<Perito> listarPeritos(int idCaso) {
        ArrayList peritos = new ArrayList();
        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT PC.PER_ID, PER_USUARIO, PER_NOMBRE, PER_APELLIDOS, ROL_NOMBRE FROM PERSONA_CASO PC \n"
                    + "	JOIN PERSONA P ON PC.PER_ID = P.PER_ID\n"
                    + "	JOIN ROL ON ROL.ROL_ID = PC.ROL_ID\n"
                    + "	WHERE CAS_ID = " + idCaso);
            rs = cs.executeQuery();

            while (rs.next()) {
                peritos.add(new Perito(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3) + " " + rs.getString(4),
                        rs.getString(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }

        return peritos;
    }

    public ArrayList<Evidencia> listaEvidencia(int idCaso) {
        ArrayList lista = new ArrayList();
        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT * FROM EVIDENCIA WHERE CAS_ID = " + idCaso);
            rs = cs.executeQuery();

            while (rs.next()) {
                lista.add(new Evidencia(rs.getInt("ev_id"), idCaso, rs.getInt("per_id"), rs.getString("referencia"), rs.getString("nombre"), rs.getString("descripcion"), rs.getString("observaciones")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }

        return lista;
    }

    public Caso getCaso(int idCaso) {
        Caso caso = new Caso();
        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT C.CAS_ID, CAS_REFERENCIA, IN_FECHA, IN_DURACION, IN_DETALLES, IN_AREA, IN_DEPENDENCIA, CAS_OBSERVACIONES \n"
                    + ",PER_NOMBRE, PER_APELLIDOS\n"
                    + "FROM CASO C\n"
                    + "JOIN PERSONA_CASO PC ON C.CAS_ID = PC.CAS_ID\n"
                    + "JOIN PERSONA P ON PC.PER_ID = P.PER_ID\n"
                    + "WHERE C.CAS_ID = " + idCaso);
            rs = cs.executeQuery();

            if (rs.next()) {
                caso = new Caso(rs.getInt("CAS_ID"), rs.getString("CAS_REFERENCIA"), rs.getDate("IN_FECHA"), rs.getString("IN_DURACION"), rs.getString("IN_DETALLES"), rs.getString("IN_AREA"), rs.getString("IN_DEPENDENCIA"), rs.getString("CAS_OBSERVACIONES"), rs.getString("PER_NOMBRE") + rs.getString("PER_APELLIDOS"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }

        return caso;
    }

    public int tieneSolicitudes(int id_persona) {
        int sol = 0;

        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT COUNT(IDCASO) AS TOTAL FROM SOLICITUD\n"
                    + "WHERE ID_ROL = 2 AND ESTADO =0  AND IDPERSONA = " + id_persona);
            rs = cs.executeQuery();

            if (rs.next()) {
                sol = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }
        System.out.println("Solicitudes: " + sol);
        return sol;
    }

    public ArrayList<Solicitud> lstSolicitudes(int idPersona) {
        System.out.println("Lisando solicitudes a :" + idPersona);
        ArrayList lista = new ArrayList();
        cn = dbCon.getConnection();

        try {
            cs = cn.prepareCall("SELECT ID_SOLICITUD, S.IDCASO, CAS_REFERENCIA, IN_DETALLES FROM SOLICITUD S\n"
                    + "JOIN CASO C ON S.IDCASO = C.CAS_ID\n"
                    + "WHERE S.ESTADO = 0 AND  S.IDPERSONA =  " + idPersona);
            rs = cs.executeQuery();

            while (rs.next()) {
                lista.add(new Solicitud(rs.getInt("ID_SOLICITUD"), rs.getInt("IDCASO"), rs.getString("CAS_REFERENCIA"), rs.getString("IN_DETALLES")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection_rs(cn);
        }

        return lista;
    }

    public int aceptarSolicitud(int idsolicitud) {
        cn = dbCon.getConnection();
        int idCaso = 0;
        try {
            cs = cn.prepareCall("SELECT ACEPTARSOLICITUD(?)");
            cs.setInt(1, idsolicitud);

            rs = cs.executeQuery();
            if (rs.next()) {
                idCaso = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CasoBD.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close_connection(cn);
        }
        return idCaso;
    }

}
