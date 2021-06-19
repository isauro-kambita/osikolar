package ao.com.osikolar.javaClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Ghil
 */
public class Sala {
    private IntegerProperty idSala;
    private StringProperty sala;

    public Sala(int idSala, String sala) {
        this.idSala = new SimpleIntegerProperty(idSala);
        this.sala = new SimpleStringProperty(sala);
    }
    
    //Métodos do idSala
    public int getIdSala(){
        return idSala.get();
    }
    
    public void setIdSala(int idSala){
        this.idSala = new SimpleIntegerProperty(idSala);
    }
    
    public IntegerProperty idSala(){
        return idSala;
    }
    
    //Métodos da sala
    public String getSala(){
        return sala.get();
    }
    
    public void setSala(String sala){
        this.sala = new SimpleStringProperty(sala);
    }
    
    public StringProperty sala(){
        return sala;
    } 
    
    @Override
    public String toString(){
        return sala.get();
    }
    
    public static void gerarSala(Connection connection, ObservableList <Sala> listaSala){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idSala, sala FROM tblsala");
            while(rs.next()){
                listaSala.add(new
                        Sala(
                             rs.getInt("idSala"),
                             rs.getString("sala")
                            ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}
