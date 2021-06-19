package ao.com.osikolar.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Ghil
 */
public class ConnectionLayer {
    public static Connection connection;
    public static String url = "jdbc:mysql://localhost/osikolar";
    public static String user = "root";
    public static String password = "";
    
    public static Connection getConnection(){
        return connection;
    }

    
    public static void connectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            Alert erroDriver = new Alert(AlertType.ERROR);
            erroDriver.setTitle("MENSAGEM DE DRIVER");
            erroDriver.setContentText("Temos problemas em localizar o driver.");
            erroDriver.setHeaderText("DRIVER NÃO LOCALIZADO");
            erroDriver.show();
        }
        catch (SQLException e){
            Alert erroDriver = new Alert(AlertType.ERROR);
            erroDriver.setTitle("MENSAGEM DO BANCO DE DADOS");
            erroDriver.setContentText("O sistema achou problema em localizar o banco de dados.");
            erroDriver.setHeaderText("BANCO DE DADOS NÃO LOCALIZADO");
            erroDriver.show();            
        }
    }
   }
