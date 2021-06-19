package ao.com.osikolar.javaClasses;

import java.sql.Connection;
import java.sql.ResultSet;
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
public class Provincia {
    private IntegerProperty idProvincia;
    private StringProperty provincia;

    public Provincia(int idProvincia, String provincia) {
        this.idProvincia = new SimpleIntegerProperty(idProvincia);
        this.provincia = new SimpleStringProperty(provincia);
    }
    
    //Métodos da idProvincia
    public int getIdProvincia(){
        return idProvincia.get();
    }
    
    public void seIdProvincia(int idProvincia){
        this.idProvincia = new SimpleIntegerProperty(idProvincia);
    }
    
    public IntegerProperty idProvincia(){
        return idProvincia;
    }
    
    //Métodos de provincia
    public String getProvincia(){
        return provincia.get();
    }
    
    public void setProvincia(String provincia){
        this.provincia = new SimpleStringProperty(provincia);
    }
    
    public StringProperty provincia(){
        return provincia;
    }
    
    @Override
    public String toString(){
        return provincia.get();
    }
    
    public static void gerarProvincia(Connection connection, ObservableList<Provincia> listaProvincia){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idProvincia, provincia FROM tblprovincia");
            while(rs.next()){
                listaProvincia.add(
                        new Provincia(
                                rs.getInt("idProvincia"),
                                rs.getString("provincia"))
                );
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
