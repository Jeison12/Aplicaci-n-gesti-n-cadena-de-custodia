/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensic;

/**
 *
 * @author javier
 */
public class Forense {

    private static String forense = "/home/javier/Documentos/UNICAUCA/2018-1/Forense/";
    private static String file = "'Esterilizando un disco de almacenamiento.mp4'";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Analizador analizador = new Analizador();

        System.out.println(analizador.getHash("/home/javier/Documentos/", "Inventario.zip"));
        System.out.println(analizador.compararHashDeFicheros("/home/javier/Documentos/Inventario.zip","/home/javier/Documentos/Inventario.zip"));
    }
}
