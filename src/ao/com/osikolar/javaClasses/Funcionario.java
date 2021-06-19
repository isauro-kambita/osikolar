package ao.com.osikolar.javaClasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class Funcionario {

    private IntegerProperty idFuncionario;
    private StringProperty nomeFuncionario;
    private StringProperty generoFuncionario;
    private StringProperty funcao;
    private StringProperty grauFormacao;
    private StringProperty estadoFormacao;
    private StringProperty especialidade;
    private Date dataFuncionario;
    private StringProperty classeGrau;
    private StringProperty estado;
    private StringProperty biFuncionario;
    private StringProperty obsFuncionario;

    public Funcionario(int idFuncionario, String nomeFuncionario, String generoFuncionario, String funcao, String grauFormacao, String estadoFormacao, String especialidade, Date dataFuncionario, String classeGrau, String estado, String biFuncionario, String obsFuncionario) {
        this.idFuncionario = new SimpleIntegerProperty(idFuncionario);
        this.nomeFuncionario = new SimpleStringProperty(nomeFuncionario);
        this.generoFuncionario = new SimpleStringProperty(generoFuncionario);
        this.funcao = new SimpleStringProperty(funcao);
        this.grauFormacao = new SimpleStringProperty(grauFormacao);
        this.estadoFormacao = new SimpleStringProperty(estadoFormacao);
        this.especialidade = new SimpleStringProperty(especialidade);
        this.dataFuncionario = dataFuncionario;
        this.classeGrau = new SimpleStringProperty(classeGrau);
        this.estado = new SimpleStringProperty(estado);
        this.biFuncionario = new SimpleStringProperty(biFuncionario);
        this.obsFuncionario = new SimpleStringProperty(obsFuncionario);
    }

    //Métodos do idFuncionario
    public int getIdFuncionario() {
        return idFuncionario.get();
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = new SimpleIntegerProperty(idFuncionario);
    }

    public IntegerProperty idFuncionario() {
        return idFuncionario;
    }

    //Métodos do nome
    public String getNomeFuncionario() {
        return nomeFuncionario.get();
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = new SimpleStringProperty(nomeFuncionario);
    }

    public StringProperty nomeFuncionario() {
        return nomeFuncionario;
    }

    //Métodos do genero
    public String getGeneroFuncionario() {
        return generoFuncionario.get();
    }

    public void setGeneroFuncionario(String generoFuncionario) {
        this.generoFuncionario = new SimpleStringProperty(generoFuncionario);
    }

    public StringProperty generoFuncionario() {
        return generoFuncionario;
    }

    //Métodos da funcao
    public String getFuncao() {
        return funcao.get();
    }

    public void setFuncao(String funcao) {
        this.funcao = new SimpleStringProperty(funcao);
    }

    public StringProperty funcao() {
        return funcao;
    }

    //Métodos do grauFormação
    public String getGrauFormacao() {
        return grauFormacao.get();
    }

    public void setGrauFromacao(String grauFormacao) {
        this.grauFormacao = new SimpleStringProperty(grauFormacao);
    }

    public StringProperty grauFormacao() {
        return grauFormacao;
    }

    //Métodos do estadoFormacao
    public String getEstadoFormacao() {
        return estadoFormacao.get();
    }

    public void setEstadoFormacao(String estadoFormacao) {
        this.estadoFormacao = new SimpleStringProperty(estadoFormacao);
    }

    public StringProperty estadoFormacao() {
        return estadoFormacao;
    }

    //Métodos da especialidade
    public String getEspecialidade() {
        return especialidade.get();
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = new SimpleStringProperty(especialidade);
    }

    public StringProperty especialidade() {
        return especialidade;
    }

    //Métodos da aniversário
    public Date getDataFuncionario() {
        return dataFuncionario;
    }

    public void setDataFuncionario(Date dataFuncionario) {
        this.dataFuncionario = dataFuncionario;
    }

    //Métodos da classeGrau
    public String getClasseGrau() {
        return classeGrau.get();
    }

    public void setClasseGrau(String classeGrau) {
        this.classeGrau = new SimpleStringProperty(classeGrau);
    }

    public StringProperty classeGrau() {
        return classeGrau;
    }

    //Métodos do estatus
    public String getEstado() {
        return estado.get();
    }

    public void setEstado(String estado) {
        this.estado = new SimpleStringProperty(estado);
    }

    public StringProperty estado() {
        return estado;
    }

    //Métodos do biFuncionario
    public String getBiFuncionario() {
        return biFuncionario.get();
    }

    public void setBiFuncionario(String biFuncionario) {
        this.biFuncionario = new SimpleStringProperty(biFuncionario);
    }

    public StringProperty biFuncionario() {
        return biFuncionario;
    }

    //Métodos da obs
    public String getObsFuncionario() {
        return obsFuncionario.get();
    }

    public void setObsFuncionario(String obsFuncionario) {
        this.obsFuncionario = new SimpleStringProperty(obsFuncionario);
    }

    public StringProperty obsFuncionario() {
        return obsFuncionario;
    }

    public static void gerarFuncionario(Connection connection, ObservableList<Funcionario> listaFuncionario) {
        try {
            String sql = "select idFuncionario, nomeFuncionario, generoFuncionario, funcao, grauFormacao, estadoFormacao, especialidade, "
                    + "dataFuncionario, classeGrau, estado, biFuncionario, obsFuncionario from tblfuncionario";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listaFuncionario.add(
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
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int inserirFuncionario(Connection connection) {
        String sql = "INSERT INTO tblfuncionario (nomeFuncionario, generoFuncionario, funcao, grauFormacao, estadoFormacao, especialidade, dataFuncionario, classeGrau, estado, biFuncionario, obsFuncionario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);

            pst.setString(1, nomeFuncionario.get());
            pst.setString(2, generoFuncionario.get());
            pst.setString(3, funcao.get());
            pst.setString(4, grauFormacao.get());
            pst.setString(5, estadoFormacao.get());
            pst.setString(6, especialidade.get());
            pst.setDate(7, Date.valueOf(dataFuncionario.toLocalDate()));
            pst.setString(8, classeGrau.get());
            pst.setString(9, estado.get());
            pst.setString(10, biFuncionario.get());
            pst.setString(11, obsFuncionario.get());
            return pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }
    }

    //Actualizar
    public int actualizar(Connection connection) {
        String sql = "UPDATE tblfuncionario SET nomeFuncionario=?, generoFuncionario=?, funcao=?, grauFormacao=?, estadoFormacao=?, especialidade=?, dataFuncionario=?, classeGrau=?, estado=?, biFuncionario=?, obsFuncionario=? WHERE idFuncionario=?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nomeFuncionario.get());
            pst.setString(2, generoFuncionario.get());
            pst.setString(3, funcao.get());
            pst.setString(4, grauFormacao.get());
            pst.setString(5, estadoFormacao.get());
            pst.setString(6, especialidade.get());
            pst.setDate(7, Date.valueOf(dataFuncionario.toLocalDate()));
            pst.setString(8, classeGrau.get());
            pst.setString(9, estado.get());
            pst.setString(10, biFuncionario.get());
            pst.setString(11, obsFuncionario.get());
            pst.setInt(12, idFuncionario.get());
            
            return pst.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //Deletar
    public int deletarFuncionario(Connection connection){
        String sql = "DELETE FROM tblfuncionario WHERE idFuncionario=?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, idFuncionario.get());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
