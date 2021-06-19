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
public class Turno {
    private IntegerProperty idTurno;
    private StringProperty turno;
    
    public Turno(int idTurno, String turno){
        this.idTurno = new SimpleIntegerProperty(idTurno);
        this.turno = new SimpleStringProperty(turno);
    }
    
    //Métodos do idTurno
    public int getIdTurno(){
        return idTurno.get();
    }
    
    public void setIdTurno(int idTurno){
        this.idTurno = new SimpleIntegerProperty(idTurno);
    }
    
    public IntegerProperty idTurno(){
        return idTurno;
    }
    
    //Métodos do turno
    public String getTurno(){
        return turno.get();
    }
    
    public void setTurno(String turno){
        this.turno = new SimpleStringProperty(turno);
    }
    
    public StringProperty turno(){
        return turno;
    }
    
    @Override
    public String toString(){
        return turno.get();
    }
    
    public static void gerarTurno(Connection connection, ObservableList <Turno> listaTurno){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idTurno, turno FROM tblturno");
            while(rs.next()){
                listaTurno.add(
                        new Turno(
                            rs.getInt("idTurno"),
                            rs.getString("turno")
                    ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
