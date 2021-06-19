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
public class Usuario {
    private IntegerProperty codigoUsuario;
    private Funcionario funcionario;
    private StringProperty perfil;
    private StringProperty login;
    private StringProperty password;
    
    public Usuario(int codigoUsuario, Funcionario funcionario, String perfil, String login, String password){
        this.codigoUsuario = new SimpleIntegerProperty(codigoUsuario);
        this.funcionario = funcionario; 
        this.perfil = new SimpleStringProperty(perfil);
        this.login = new SimpleStringProperty(login);
        this.password = new SimpleStringProperty(password);
        
    }
    
    //Métodos do codigoUsuario
    public int getCodigoUsuario(){
        return codigoUsuario.get();
    }
    
    public void setCodigoUsuario(int codigoUsuario){
        this.codigoUsuario = new SimpleIntegerProperty(codigoUsuario);
    }
    
    public IntegerProperty codigoUsuario(){
        return codigoUsuario;
    }
    
    
    //Métodos do idFuncionario
    public Funcionario getFuncionario(){
        return funcionario;
    }
    
    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }
    
    
    //Métodos do peril
    public String getPerfil(){
        return perfil.get();
    }
    
    public void setPerfil(String perfil){
        this.perfil = new SimpleStringProperty(perfil);
    }
    
    public StringProperty perfil(){
        return perfil;
    }
    
    //Métodos do login
    public String getLogin(){
        return login.get();
    }
    
    public void setLogin(String login){
        this.login = new SimpleStringProperty(login);
    }
    
    public StringProperty login(){
        return login;
    }
    
    //Métodos do password
    public String getPassword(){
        return password.get();
    }
    
    public void setPassword(String password){
        this.password = new SimpleStringProperty(password);
    }
    
    public StringProperty password(){
        return password;
    }
    
    @Override
    public String toString(){
        return funcionario.getNomeFuncionario();
    }
    
    
    
    
    //Select do usuario
   public static void gerarUsuario(Connection connection, ObservableList <Usuario> listaUsuario){
        try {
            String sql = "SELECT U.idUsuario, U.perfil, U.login, U.senha, F.idFuncionario, F.nomeFuncionario, F.generoFuncionario, F.funcao, F.grauFormacao, F.estadoFormacao, F.especialidade, F.dataFuncionario, F.classeGrau, F.estado, F.biFuncionario, F.obsFuncionario FROM tblusuario AS U INNER JOIN tblfuncionario AS F ON (U.idFuncionario=F.idFuncionario)";
        Statement pst = connection.createStatement();
        ResultSet rs = pst.executeQuery(sql);
        while(rs.next()){
            listaUsuario.add(
                        new Usuario(
                            rs.getInt("idUsuario"),
                                new Funcionario(
                                        rs.getInt("idFuncionario"),
                                        rs.getString("nomeFuncionario"),
                                        rs.getString("generoFuncionario"),
                                        rs.getString("funcao"),
                                        rs.getString("grauFormacao"),
                                        rs.getString("estadoFormacao"),
                                        rs.getString("especialidade"),
                                        rs.getDate("dataFuncionario"),
                                        rs.getString("classeGrau"),
                                        rs.getString("estado"),
                                        rs.getString("biFuncionario"),
                                        rs.getString("obsFuncionario")
                                ),
                            rs.getString("perfil"),
                            rs.getString("login"),
                            rs.getString("senha")
                            ));
            
            }
            
      }catch(SQLException e){
          e.printStackTrace();
      } 
    }
    
}

