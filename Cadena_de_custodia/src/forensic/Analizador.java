/*-  Javier Agredo 9/04/2018 - javiguittar@gmail.com -*/
package forensic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javier
 */
public class Analizador {


    private String[] comando;
    private boolean noHayRespuesta;

    public String crearImagen(String archivo, String rutaFinal) {
        comando = new String[]{"dd", "if="+archivo, "of="+rutaFinal};   
        return ejecutar();
    }

    public boolean compararHashDeFicheros(String fichero1, String fichero2){
        String hashF1 = getHash(fichero1);
        String hashF2 = getHash(fichero2);
        if(noHayRespuesta)return false;
        return hashF1.compareTo(hashF2)==0;
    }
    
    public String getHash(String fichero) {
        comando = new String[]{"openssl", "md5", fichero};
        return ejecutar();
    }

    public String getHash(String directorio, String fichero) {
        comando = new String[]{"openssl", "md5", directorio + fichero};
        return ejecutar();
    }

    private String ejecutar() {
        String respuesta = execute_command(comando);
        if (respuesta.length() > 0) {
            return getHashFromString(respuesta);
        } else {
            noHayRespuesta = true;
            return "";
        }
    }

    private String execute_command(String[] command) {
        String strContent = "";

        try {
            Process process = Runtime.getRuntime().exec(command);
            InputStream input = process.getInputStream();
            BufferedInputStream bf = new BufferedInputStream(input);

            byte content[] = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = bf.read(content)) != -1) {
                strContent += new String(content, 0, bytesRead);
            }

        } catch (IOException ex) {
            Logger.getLogger(Forense.class.getName()).log(Level.SEVERE, null, ex);
        }
        return strContent;
    }

    private String getHashFromString(String cadena) {
        String hash = cadena.split("=")[1];
        return hash.trim();
    }
}
