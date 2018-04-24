/*-  Javier Agredo 18/04/2018 - javiguittar@gmail.com -*/

package Logica;

import java.util.Date;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author javier
 */
public class Utils {
    
    public static boolean vallidarTamanioMinimo(JTextField txt, int tamanio) {
        return (txt.getText().trim().length() >= tamanio);
    }
    public static boolean vallidarTamanioMinimo(JTextArea txt, int tamanio) {
        return (txt.getText().trim().length() >= tamanio);
    }
    
    /**
     * 
     * @param cadena en formato dd/mm/aaaa
     * @return 
     */
    public static Date stringToDate(String cadena){
        if(cadena.isEmpty())return new Date();
        int dia,mes,anio;
        String fecha[] = cadena.split("/");
        dia = Integer.parseInt(fecha[0]);
        mes = Integer.parseInt(fecha[1]);
        anio = Integer.parseInt(fecha[2]);
        return new Date(dia,mes,anio+1900);
    }
    
    public static String dateToString(java.sql.Date date){        
        return "12/05/2018";
    }
    
    public static void mostrarMensaje(String msj) {
        System.out.println(msj);
    }

    public static java.sql.Date getDateSql(Date date) {
        return new java.sql.Date(date.getTime());        
    }
    
    
    public boolean validarCampos(JTextField... args){
        for (JTextField txt : args) {
            
        }
        return true;
    }

}
