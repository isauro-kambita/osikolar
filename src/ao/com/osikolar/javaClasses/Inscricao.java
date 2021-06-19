package ao.com.osikolar.javaClasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class Inscricao{
    private IntegerProperty idInscricao;
    private Date dataInscricao;
    private StringProperty modalidade;
    private IntegerProperty numero;
    private Aluno aluno;
    private Classe classe;
    private Curso curso;
    private Turma turma;
    private Sala sala;
    private Turno turno;
    private Usuario usuario;
    private StringProperty obsInscricao;
    
    public Inscricao(int idInscricao, Date dataInscricao, String modalidade, int numero, Aluno aluno, Classe classe, Curso curso, Turma turma, Sala sala, Turno turno, Usuario usuario, String obsInscricao){
        this.idInscricao = new SimpleIntegerProperty(idInscricao);
        this.dataInscricao = dataInscricao;
        this.modalidade = new SimpleStringProperty(modalidade);
        this.numero = new SimpleIntegerProperty(numero);
        this.aluno = aluno;
        this.classe = classe;
        this.curso = curso;
        this.turma = turma;
        this.sala = sala;
        this.turno = turno;
        this.usuario = usuario;
        this.obsInscricao = new SimpleStringProperty(obsInscricao);
    }
    
    //Métodos do idIncricao
    public int getIdInscricao(){
        return idInscricao.get();
    }
    
    public void setIdInscricao(int idInscricao){
        this.idInscricao = new SimpleIntegerProperty(idInscricao);
    }
    
    public IntegerProperty idInscricao(){
        return idInscricao;
    }
    
    //Métodos de dataInscricao
    public Date getDataInscricao(){
        return dataInscricao;
    }
    
    public void setDataInscricao(Date dataInscricao){
        this.dataInscricao = dataInscricao;
    }
    
    //Métodos de modalidade
    public String getModalidade(){
        return modalidade.get();
    }
    
    public void setModalidade(String modalidade){
        this.modalidade = new SimpleStringProperty(modalidade);
    }
    
    public StringProperty modalidade(){
        return modalidade;
    }
    
    //Métodos do numero
    public int getNumero(){
        return numero.get();
    }
    
    public void setNumero(int numero){
        this.numero = new SimpleIntegerProperty(numero);
    }
    
    public IntegerProperty numero(){
        return numero;
    }
    
    
    //Métodos do idAluno
    public Aluno getAluno(){
        return aluno;
    }
    
    public void setAluno(Aluno aluno){
        this.aluno = aluno;
    }
    
    //Métodos do idClasse
    public Classe getClasse(){
        return classe;
    }
    
    public void setClasse(Classe classe){
        this.classe = classe;
    }
    
    //Métodos do idCurso
    public Curso getCurso(){
        return curso;
    }
    
    public void setCurso(Curso curso){
        this.curso = curso;
    }
    
    //Métodos do idTurma
    public Turma getTurma(){
        return turma;
    }
    
    public void setTurma(Turma turma){
        this.turma = turma;
    }
    
    //Métodos do idSala
    public Sala getSala(){
        return sala;
    }
    
    public void setSala(Sala sala){
        this.sala = sala;
    }
    
    //Métodos do idTurno
    public Turno getTurno(){
        return turno;
    }
    
    public void setTurno(Turno turno){
        this.turno = turno;
    }
    
    //Métodos do idUsuario
    public Usuario getUsuario(){
        return usuario;
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    //Métodos de Obs
    public String getObsInscricao(){
        return obsInscricao.get();
    }
    
    public void setObsInscricao(String obsInscricao){
        this.obsInscricao = new SimpleStringProperty(obsInscricao);
    }
    
    public StringProperty obsInscricao(){
        return obsInscricao;
    }
    

    
  
    
    //Método da busca de inscrições
    public static void gerarInformacaoInscricao(Connection connection, ObservableList <Inscricao> listaInscricao){
        try {
            String sql = "SELECT I.idInscricao, I.dataInscricao, I.modalidade, I.numero, A.idAluno, A.nomeAluno, A.generoAluno, " +
" A.dataAluno, A.pai, A.mae, A.naturalidade, A.municipio, P.idProvincia, P.provincia, A.cedulaBi, A.dataArquivo, A.estadoAluno, " +
" A.anoLectivo, A.obsAluno, Cl.idClasse, Cl.classe, Cr.idCurso, Cr.curso, Tr.idTurma, Tr.turma, Sl.idSala, Sl.sala, Tn.idTurno, " +
" Tn.turno, U.idUsuario, U.perfil, U.login, U.senha, F.idFuncionario, F.nomeFuncionario, " +
" F.generoFuncionario, F.funcao, F.grauFormacao, F.estadoFormacao, F.especialidade, F.dataFuncionario, F.classeGrau, " +
" F.estado, F.biFuncionario, F.obsFuncionario, I.obsInscricao " +
" FROM tblinscricao AS I " +
" INNER JOIN tblaluno AS A ON (I.idAluno=A.idAluno) " +
" INNER JOIN tblprovincia AS P ON (A.idProvincia=P.idProvincia) " +
" INNER JOIN tblclasse AS Cl ON (I.idClasse=Cl.idClasse) " +
" INNER JOIN tblcurso AS Cr ON (I.idCurso=Cr.idCurso) " +
" INNER JOIN tblturma AS Tr ON (I.idTurma=Tr.idTurma) " +
" INNER JOIN tblsala AS Sl ON (I.idSala=Sl.idSala)  INNER JOIN tblturno AS Tn ON (I.idTurno=Tn.idTurno) " +
" INNER JOIN tblusuario AS U ON (I.idUsuario=U.idUsuario) " +
" INNER JOIN tblfuncionario AS F ON (U.idFuncionario=F.idFuncionario) ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                listaInscricao.add(
                        new Inscricao(
                            rs.getInt("idInscricao"),
                            rs.getDate("dataInscricao"),
                            rs.getString("modalidade"),
                            rs.getInt("numero"),
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
                                        ),
                            new Classe(
                                        rs.getInt("idClasse"),
                                        rs.getString("classe")
                                ),
                            new Curso(
                                        rs.getInt("idCurso"),
                                        rs.getString("curso")
                                ),
                            new Turma(
                                        rs.getInt("idTurma"),
                                        rs.getString("turma")
                                ),
                            new Sala(
                                        rs.getInt("idSala"),
                                        rs.getString("sala")
                                ),
                            new Turno(
                                        rs.getInt("idTurno"),
                                        rs.getString("turno")
                                ),
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
                                        ),
                            rs.getString("obsInscricao")));
            }
        } catch (SQLException e) {
        }
 
    }
    
    //método para inserir dados na tabela inscricao
    public int registarInscricao(Connection connection){
        String sql = "INSERT INTO tblinscricao (idInscricao, dataInscricao, modalidade, numero, idAluno, idClasse, idCurso, idTurma, idSala, idTurno, idUsuario, obsInscricao) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, idInscricao.get());
            pst.setDate(2, Date.valueOf(dataInscricao.toLocalDate()));
            pst.setString(3, modalidade.get());
            pst.setInt(4, numero.get());
            pst.setInt(5, aluno.getIdAluno());
            pst.setInt(6, classe.getIdClasse());
            pst.setInt(7, curso.getIdCurso());
            pst.setInt(8, turma.getIdTurma());
            pst.setInt(9, sala.getIdSala());
            pst.setInt(10, turno.getIdTurno());
            pst.setInt(11, usuario.getCodigoUsuario());
            pst.setString(12, obsInscricao.get());
            
            return pst.executeUpdate();
            
        } catch (Exception e) {
            return 0;
        } 
    }
    
    //método deletar
    public int deletar(Connection connection){
        try {
            String sql = "DELETE FROM tblinscricao WHERE idInscricao = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1, idInscricao.get());
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    //método actualizar
    public int actualizar(Connection connection){
        String sql = "UPDATE tblinscricao SET dataInscricao=?, modalidade=?, numero=?, idAluno=?, idClasse=?, idCurso=?, idTurma=?, idSala=?, idTurno=?, idUsuario=?, obsInscricao=? WHERE idInscricao=?";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(dataInscricao.toLocalDate()));
            pst.setString(2, modalidade.get());
            pst.setInt(3, aluno.getIdAluno());
            pst.setInt(4, classe.getIdClasse());
            pst.setInt(5, curso.getIdCurso());
            pst.setInt(6, turma.getIdTurma());
            pst.setInt(7, sala.getIdSala());
            pst.setInt(8, turno.getIdTurno());
            pst.setInt(9, turno.getIdTurno());
            pst.setInt(10, usuario.getCodigoUsuario());
            pst.setString(11, obsInscricao.get());
            pst.setInt(12, idInscricao.get());
            
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
