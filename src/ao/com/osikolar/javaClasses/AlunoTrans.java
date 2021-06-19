package ao.com.osikolar.javaClasses;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ghil
 */
public class AlunoTrans {
    private IntegerProperty idTransferencia;
    private Aluno aluno;
    private StringProperty paraEscola;
    private StringProperty paraMunicipio;
    private Date dataTransferencia;
    private Provincia provincia;
    private Classe classe;
    private Curso curso;
    private Turma turma;
    private Sala sala;
    private Turno turno;
    private StringProperty obsAlunoTransferido;
    
    //Constructor
    public AlunoTrans(int idTransferencia, Aluno aluno, String paraEscola, String paraMunicipio, Date dataTransferencia, Provincia prvincia, Classe classe, Curso curso, Turma turma, Sala sala, Turno turno, String obsAlunoTransferido){
        this.idTransferencia = new SimpleIntegerProperty (idTransferencia);
        this.aluno = aluno;
        this.paraEscola = new SimpleStringProperty(paraEscola);
        this.paraMunicipio = new SimpleStringProperty(paraMunicipio);
        this.dataTransferencia = dataTransferencia;
        this.provincia = provincia;
        this.classe = classe;
        this.curso = curso;
        this.turma = turma;
        this.sala = sala;
        this.turno = turno;
        this.obsAlunoTransferido = new SimpleStringProperty(obsAlunoTransferido);
    }
    
    //Métodos do idTrasferencia
    public int getIdTransferencia(){
        return idTransferencia.get();
    }
    
    public void setIdTransferencia(int idTransferencia){
        this.idTransferencia = new SimpleIntegerProperty(idTransferencia);
    }
    
    public IntegerProperty idTransferencia(){
        return idTransferencia;
    }
    
     //Métodos do aluno
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    
     //Métodos de paraEscola
    public String getParaEscola(){
        return paraEscola.get();
    }
    
    public void setParaEscola(String paraEscola){
        this.paraEscola = new SimpleStringProperty(paraEscola);
    }
    
    public StringProperty paraEscola(){
        return paraEscola;
    }
    
     //Métodos de paraMunicipio
    public String getParaMunicipio(){
        return paraMunicipio.get();
    }
    
    public void setParaMunicipio(String paraMunicipio){
        this.paraMunicipio = new SimpleStringProperty(paraMunicipio);
    }
    
    public StringProperty paraMunicipio(){
        return paraMunicipio;
    }
    
     //Métodos dataTransferencia
    public Date getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(Date dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }
    
     //Métodos de provincia
    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
     //Métodos de classe
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
     //Métodos de curso
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    //Métodos do turma
    public Turma getTurma() {
        return turma;
    }
    
    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    //Métodos de sala
    public Sala getSala() {
        return sala;
    }
    
    public void setSala(Sala sala) {
        this.sala = sala;
    }

    //Métodos de turno
    public Turno getTurno() {
        return turno;
    }
    
    public void setTurno(Turno turno) {
        this.turno = turno;
    }
    
    
    
}
