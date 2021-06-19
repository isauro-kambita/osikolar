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
public class Turma {
    private IntegerProperty idTurma;
    private StringProperty turma;
    
    public Turma(int idTurma, String turma){
        this.idTurma = new SimpleIntegerProperty(idTurma);
        this.turma = new SimpleStringProperty(turma);
    }
    
    //Métodos da idTurma
    public int getIdTurma(){
        return idTurma.get();
    }
    
    public void setIdTurma(int idTurma){
        this.idTurma = new SimpleIntegerProperty(idTurma);
    }
    
    public IntegerProperty idTurma(){
        return idTurma;
    }
    
    //Métodos de turma
    public String getTurma(){
        return turma.get();
    }
    
    public void setTurma (String turma){
        this.turma = new SimpleStringProperty(turma);
    }
    
    public StringProperty turma(){
        return turma;
    }
                                                         
    @Override
    public String toString(){
        return turma.get();
    }
    
    public static void gerarTurma(Connection connection, ObservableList <Turma> listaTurma){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idTurma, turma FROM tblTurma");
            while(rs.next()){
                listaTurma.add(
                        new Turma(
                                rs.getInt("idTurma"),
                                rs.getString("turma")
        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
