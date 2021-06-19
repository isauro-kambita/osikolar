package ao.com.osikolar.javaClasses;

import java.io.IOException;
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
public class Curso {
    private IntegerProperty idCurso;
    private StringProperty curso;

    public Curso(Integer idCurso, String curso) {
        this.idCurso = new SimpleIntegerProperty (idCurso);
        this.curso = new SimpleStringProperty(curso);
    }
    
    
    //Métodos do IdCurso
    public int getIdCurso(){
        return idCurso.get();
    }
    
    public void setIdCurso(int idCurso){
        this.idCurso = new SimpleIntegerProperty(idCurso);
    }
    
    public IntegerProperty idCurso(){
        return idCurso;
    }
    
    //Métodos do Curso
    public String getCurso(){
        return curso.get();
    }
    
    public void setCurso(String curso){
        this.curso = new SimpleStringProperty(curso);
    }
    
    public StringProperty curso(){
        return curso;
    }
    
    @Override
    public String toString(){
        return curso.get();
    }
    
    public static void gerarCurso(Connection connection, ObservableList <Curso> listaCurso){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idCurso, curso FROM tblcurso");
            while(rs.next()){
                listaCurso.add(
                       new Curso(
                            rs.getInt("idCurso"),
                            rs.getString("curso")
                       ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
