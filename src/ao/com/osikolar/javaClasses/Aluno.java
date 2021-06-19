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
public class Aluno {
    private IntegerProperty idAluno;
    private StringProperty nomeAluno;
    private StringProperty generoAluno;
    private Date dataAluno;
    private StringProperty pai;
    private StringProperty mae;
    private StringProperty natural;
    private StringProperty municipio;
    private Provincia provincia;
    private StringProperty cedulaBi;
    private Date dataArquivo;
    private StringProperty estadoAluno;
    private IntegerProperty anoLectivo;
    private StringProperty obsAluno;

    public Aluno(int idAluno, String nomeAluno, String generoAluno, Date dataAluno, String pai, String mae, String natural, String municipio, Provincia provincia, String cedulaBi, Date dataArquivo, String estadoAluno, int anoLectivo, String obsAluno) {
        this.idAluno = new SimpleIntegerProperty(idAluno);
        this.nomeAluno = new SimpleStringProperty(nomeAluno);
        this.generoAluno = new SimpleStringProperty(generoAluno);
        this.dataAluno = dataAluno;
        this.pai = new SimpleStringProperty(pai);
        this.mae = new SimpleStringProperty(mae);
        this.natural = new SimpleStringProperty(natural);
        this.municipio = new SimpleStringProperty(municipio);
        this.provincia = provincia;
        this.cedulaBi = new SimpleStringProperty(cedulaBi);
        this.dataArquivo = dataArquivo;
        this.estadoAluno = new SimpleStringProperty(estadoAluno);
        this.anoLectivo = new SimpleIntegerProperty(anoLectivo);
        this.obsAluno = new SimpleStringProperty(obsAluno);
        
    }

    //Métodos do idAluno
    public int getIdAluno() {
        return idAluno.get();
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = new SimpleIntegerProperty(idAluno);
    }
    
    public IntegerProperty idAluno(){
        return idAluno;
    }
    
    //Métodos do nome
    public String getNomeAluno() {
        return nomeAluno.get();
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = new SimpleStringProperty(nomeAluno);
    }
    
    public StringProperty nomeAluno(){
        return nomeAluno;
    }
    
    //Métodos do genero
    public String getGeneroAluno() {
        return generoAluno.get();
    }

    public void setGeneroAluno(String generoAluno) {
        this.generoAluno = new SimpleStringProperty(generoAluno);
    }
    
    public StringProperty generoAluno(){
        return generoAluno;
    }
    
    //Métodos do aniversario
    public Date getDataAluno() {
        return dataAluno;
    }

    public void setDataAluno(Date dataAluno) {
        this.dataAluno = dataAluno;
    }
    
    
    //Métodos do pai
    public String getPai() {
        return pai.get();
    }

    public void setPai(String pai) {
        this.pai = new SimpleStringProperty(pai);
    }
    
    public StringProperty pai(){
        return pai;
    }
    
    
    //Métodos da mae
    public String getMae() {
        return mae.get();
    }

    public void setMae(String mae) {
        this.mae = new SimpleStringProperty(mae);
    }
    
    public StringProperty mae(){
        return mae;
    }
    
    //Métodos do natural
    public String getNatural() {
        return natural.get();
    }

    public void setNatural(String natural) {
        this.natural = new SimpleStringProperty(natural);
    }
    
    public StringProperty natural(){
        return natural;
    }
    
    //Métodos do municipio
    public String getMunicipio() {
        return municipio.get();
    }

    public void setMunicipio(String municipio) {
        this.municipio = new SimpleStringProperty(municipio);
    }
    
    public StringProperty municipio(){
        return municipio;
    }
    
    //Métodos da provinci
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    //Métodos da cebulaBi
    public String getCedulaBi() {
        return cedulaBi.get();
    }

    public void setCedulaBi(String cedulaBi) {
        this.cedulaBi = new SimpleStringProperty(cedulaBi);
    }
    
    public StringProperty cedulaBi(){
        return cedulaBi;
    }
    
    //Métodos do dataArquivo
    public Date getDataArquivo() {
        return dataArquivo;
    }

    public void setDataArquivo(Date dataArquivo) {
        this.dataArquivo = dataArquivo;
    }
    
    //Métodos do status
    public String getEstadoAluno() {
        return estadoAluno.get();
    }

    public void setEstadoAluno(String estadoAluno) {
        this.estadoAluno = new SimpleStringProperty(estadoAluno);
    }
    
    public StringProperty estadoAluno(){
        return estadoAluno;
    }
    
    //Métodos do anoLectivo
    public int getAnoLectivo() {
        return anoLectivo.get();
    }

    public void setAnoLectivo(int anoLectivo) {
        this.anoLectivo = new SimpleIntegerProperty(anoLectivo);
    }
    
    public IntegerProperty anoLectivo(){
        return anoLectivo;
    }

    
    //Métodos da obs
    public String getObsAluno() {
        return obsAluno.get();
    }

    public void setObsAluno(String obsAluno) {
        this.obsAluno = new SimpleStringProperty(obsAluno);
    }
    
    public StringProperty obsAluno(){
        return obsAluno;
    }
    
    @Override
    public String toString(){
        return getIdAluno() + " - " + getNomeAluno();
    }
    
    
    public static void gerarAluno(Connection connection, ObservableList <Aluno> listaAluno){
        try {
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery("SELECT A.idAluno, A.nomeAluno, A.generoAluno, A.dataAluno, A.pai, A.mae, A.naturalidade, A.municipio, A.cedulaBi, A.dataArquivo, A.estadoAluno, A.anoLectivo, A.obsAluno, P.idProvincia, P.provincia FROM tblaluno AS A INNER JOIN tblprovincia AS P ON (A.idProvincia=P.idProvincia)");
            while(rs.next()){
                listaAluno.add(
                        new Aluno(
                        rs.getInt("idAluno"),
                        rs.getString("nomeAluno"),
                        rs.getString("generoAluno"),
                        rs.getDate("dataAluno"),
                        rs.getString("pai"),
                        rs.getString("mae"),
                        rs.getString("naturalidade"),
                        rs.getString("municipio"),
                            new Provincia(
                            rs.getInt("idProvincia"),
                            rs.getString("provincia")
                            ),
                        rs.getString("cedulaBi"),
                        rs.getDate("dataArquivo"),
                        rs.getString("estadoAluno"),
                        rs.getInt("anoLectivo"),
                        rs.getString("obsAluno")  
                                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int salvar(Connection connection){
        String sql = "insert into tblaluno (nomeAluno, generoAluno, dataAluno, pai, mae, naturalidade, municipio, idProvincia, cedulaBi, dataArquivo, estadoAluno, anoLectivo, obsAluno) values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nomeAluno.get().toUpperCase());
            pst.setString(2, generoAluno.get().toUpperCase());
            pst.setDate(3, Date.valueOf(dataAluno.toLocalDate()));
            pst.setString(4, pai.get().toUpperCase());
            pst.setString(5, mae.get().toUpperCase());
            pst.setString(6, natural.get().toUpperCase());
            pst.setString(7, municipio.get().toUpperCase());
            pst.setInt(8, provincia.getIdProvincia());
            pst.setString(9, cedulaBi.get().toUpperCase());
            pst.setDate(10, Date.valueOf(dataArquivo.toLocalDate()));
            pst.setString(11, estadoAluno.get());
            pst.setInt(12, anoLectivo.get());
            pst.setString(13, obsAluno.get());
            
            return pst.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        
    }
    
    
    //Update
    public int actualizar(Connection connection){
        try {
            String sql = "UPDATE tblaluno SET nomeAluno=?, generoAluno=?, dataAluno=?, pai=?, mae=?, naturalidade=?, municipio=?, idProvincia=?, cedulaBi=?, dataArquivo=?, estadoAluno=?, anoLectivo=?, obsAluno=? WHERE idAluno=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nomeAluno.get().toUpperCase());
            pst.setString(2, generoAluno.get().toUpperCase());
            pst.setDate(3, Date.valueOf(dataAluno.toLocalDate()));
            pst.setString(4, pai.get().toUpperCase());
            pst.setString(5, mae.get().toUpperCase());
            pst.setString(6, natural.get().toUpperCase());
            pst.setString(7, municipio.get().toUpperCase());
            pst.setInt(8, provincia.getIdProvincia());
            pst.setString(9, cedulaBi.get().toUpperCase());
            pst.setDate(10, Date.valueOf(dataArquivo.toLocalDate()));
            pst.setString(11, estadoAluno.get());
            pst.setInt(12, anoLectivo.get());
            pst.setString(13, obsAluno.get());
            pst.setInt(14, idAluno.get());
            
            return pst.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int deletar(Connection connection){
        String sql = "DELETE FROM tblaluno WHERE idAluno=?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, idAluno.get());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    
    
}
