package ao.com.osikolar.javaClasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

/**
 *
 * @author Ghil
 */
public class Classe {
    private IntegerProperty idClasse;
    private StringProperty classe;

    public Classe(int idClasse, String classe) {
        this.idClasse = new SimpleIntegerProperty(idClasse);
        this.classe = new SimpleStringProperty(classe);
    }
    
    //Métodos do idClasse
    public int getIdClasse(){
        return idClasse.get();
    }
    
    public void setIdClasse(int idClasse){
        this.idClasse = new SimpleIntegerProperty(idClasse);
    }
    
    public IntegerProperty idClasse(){
        return idClasse;
    }
    
    //Métodos da classe
    public String getClasse(){
        return classe.get();
    }
    
    public void setClasse(String classe){
        this.classe = new SimpleStringProperty(classe);
    }
    
    public StringProperty classe(){
        return classe;
    }
    
    @Override
    public String toString(){
        return classe.get();
    }
    
    public static void gerarClasse(Connection connection, ObservableList<Classe> listaClasse){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT idClasse, classe FROM tblClasse");
            while(rs.next()){
                listaClasse.add(
                        new Classe(
                            rs.getInt("idClasse"),
                            rs.getString("classe")
                            ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
    
    
}
