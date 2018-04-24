/*-  Javier Agredo 12/04/2018 - javiguittar@gmail.com -*/

package DB;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author javier
 */
public class DBConection {
    private String urlDatabase = "jdbc:postgresql://";//localhost:5432/Entrevista"
    private String host;
    private String port;
    private String bdName;
    private String user;
    private String pass;
    
    public DBConection() {
        this.host = "localhost";
        this.port = "5432";
        this.bdName = "Forensic";
        this.user = "postgres";
        this.pass = "postgres";
        createUrlDB();
    }

    public DBConection(String host, String port, String bdName, String user, String pass) {
        this.host = host;
        this.port = port;
        this.bdName = bdName;
        this.user = user;
        this.pass = pass;
        createUrlDB();
    }
    
    private void createUrlDB(){
        urlDatabase += this.host+":"+this.port+"/"+this.bdName;
    }   
    
    public Connection getConnection(){
        Connection conection = null;
        
        try{
            Class.forName("org.postgresql.Driver");
            conection = DriverManager.getConnection(urlDatabase, this.user, this.pass);
        }catch(Exception e){
            System.out.println("URL: "+urlDatabase);
            System.out.println("Ocurrio un error: " + e.getMessage());            
        }        
        return conection;
    }
}
