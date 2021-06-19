package ao.com.osikolar.layouts;

import ao.com.osikolar.dal.ConnectionLayer;
import static ao.com.osikolar.dal.ConnectionLayer.getConnection;
import ao.com.osikolar.javaClasses.Aluno;
import ao.com.osikolar.javaClasses.Classe;
import ao.com.osikolar.javaClasses.Curso;
import ao.com.osikolar.javaClasses.Inscricao;
import ao.com.osikolar.javaClasses.Sala;
import ao.com.osikolar.javaClasses.Turma;
import ao.com.osikolar.javaClasses.Turno;
import ao.com.osikolar.javaClasses.Usuario;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Ghil
 */
public class FXMLInsAlunoController implements Initializable {
    
    //Columns
    @FXML private TableColumn <Aluno, String> clcId;
    @FXML private TableColumn <Aluno, String> clcNome;
    @FXML private TableColumn <Aluno, String> clcGenero;
    @FXML private TableColumn <Aluno, Date> clcAniversario;
    @FXML private TableColumn <Aluno, String> clcEstado;
    @FXML private TableColumn <Aluno, Number> clcAnoLectivo;
    @FXML private TableColumn <Aluno, String> clcMunicipio;
    @FXML private TableColumn <Aluno, String> clcProvincia;
    @FXML private TableColumn <Aluno, Number> clcCedulaBi;
    
    //Clumns of table Inscricao
    @FXML private TableColumn <Inscricao, Number> clId;
    @FXML private TableColumn <Inscricao, String> clModalidade;
    @FXML private TableColumn <Inscricao, Date> clDataInscricao;
    @FXML private TableColumn <Inscricao, String> clNomeAluno;
    @FXML private TableColumn <Inscricao, String> clClasse;
    @FXML private TableColumn <Inscricao, String> clCurso;
    @FXML private TableColumn <Inscricao, String> clTurma;
    @FXML private TableColumn <Inscricao, String> clSala;
    @FXML private TableColumn <Inscricao, String> clTurno;
    @FXML private TableColumn <Inscricao, Number> clNumero;
    @FXML private TableColumn <Inscricao, String> clUsuario;
    
    //GUI elements
    @FXML TableView <Aluno> tbViewAluno;
    @FXML TableView <Inscricao> tbViewInscricao;
    
    //Combo boxes
    @FXML private ComboBox <Classe> cboClasse; 
    @FXML private ComboBox <Curso> cboCurso;
    @FXML private ComboBox <Turma> cboTurma;
    @FXML private ComboBox <Sala> cboSala;
    @FXML private ComboBox <Turno> cboTurno;
    @FXML private ComboBox <Usuario> cboUsuario;
  
    //Fields
    @FXML private TextField txtIdInscricao;
    @FXML private DatePicker dpkDataInscricao;
    @FXML private RadioButton rbnMatricula;
    @FXML private RadioButton rbnConfirmacao;
    @FXML private TextField txtNumero;
    @FXML private ComboBox <Aluno> cboAluno;
    @FXML private TextArea txtObs;
    
    //TuggleGroups
    @FXML private ToggleGroup tgpModalidade;
    
    //Observable lists
    ObservableList <Inscricao> listaInscricao;
    ObservableList <Aluno> listaAluno;
    ObservableList <Classe> listaClasse;
    ObservableList <Curso> listaCurso;
    ObservableList <Turma> listaTurma;
    ObservableList <Sala> listaSala;
    ObservableList <Turno> listaTurno;
    ObservableList <Usuario> listaUsuario;
    
    //Connection
    private ConnectionLayer conexao;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnProcurar;
    @FXML
    private Button btnSalvar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            conexao = new ConnectionLayer();
            ConnectionLayer.connectar();
            
            listaInscricao = FXCollections.observableArrayList();
            listaAluno = FXCollections.observableArrayList();
            listaClasse = FXCollections.observableArrayList();
            listaCurso = FXCollections.observableArrayList();
            listaTurma = FXCollections.observableArrayList();
            listaSala = FXCollections.observableArrayList();
            listaTurno = FXCollections.observableArrayList();
            listaUsuario = FXCollections.observableArrayList();
            
            Inscricao.gerarInformacaoInscricao(conexao.getConnection(), listaInscricao);
            Aluno.gerarAluno(conexao.getConnection(), listaAluno);
            Classe.gerarClasse(conexao.getConnection(), listaClasse);
            Curso.gerarCurso(conexao.getConnection(), listaCurso);
            Turma.gerarTurma(conexao.getConnection(), listaTurma);
            Sala.gerarSala(conexao.getConnection(), listaSala);
            Turno.gerarTurno(conexao.getConnection(), listaTurno);
            Usuario.gerarUsuario(conexao.getConnection(), listaUsuario);
            
            tbViewInscricao.setItems(listaInscricao);
            tbViewAluno.setItems(listaAluno);
            cboClasse.setItems(listaClasse);
            cboCurso.setItems(listaCurso);
            cboTurma.setItems(listaTurma);
            cboSala.setItems(listaSala);
            cboTurno.setItems(listaTurno);
            cboUsuario.setItems(listaUsuario);
            cboAluno.setItems(listaAluno);
            
        //Values to the table Inscricao
        clId.setCellValueFactory(new PropertyValueFactory <> ("idInscricao"));
        clModalidade.setCellValueFactory(new PropertyValueFactory <> ("modalidade"));
        clDataInscricao.setCellValueFactory(new PropertyValueFactory <> ("dataInscricao"));
        clNomeAluno.setCellValueFactory(new PropertyValueFactory <> ("aluno"));
        clClasse.setCellValueFactory(new PropertyValueFactory <> ("classe"));
        clCurso.setCellValueFactory(new PropertyValueFactory <> ("curso"));
        clTurma.setCellValueFactory(new PropertyValueFactory <> ("turma"));
        clSala.setCellValueFactory(new PropertyValueFactory <> ("sala"));
        clTurno.setCellValueFactory(new PropertyValueFactory <> ("turno"));
        clNumero.setCellValueFactory(new PropertyValueFactory <> ("numero"));
        clUsuario.setCellValueFactory(new PropertyValueFactory <> ("usuario"));
        gerarSelecao();
        btnActualizar.setDisable(true);
        btnDeletar.setDisable(true);
            
            //Values to the table Aluno
        clcId.setCellValueFactory(new PropertyValueFactory <> ("idAluno"));
        clcNome.setCellValueFactory(new PropertyValueFactory <> ("nomeAluno"));
        clcGenero.setCellValueFactory(new PropertyValueFactory <> ("generoAluno"));
        clcAniversario.setCellValueFactory(new PropertyValueFactory <> ("dataAluno"));
        clcEstado.setCellValueFactory(new PropertyValueFactory <> ("estadoAluno"));
        clcAnoLectivo.setCellValueFactory(new PropertyValueFactory <> ("anoLectivo"));
        clcMunicipio.setCellValueFactory(new PropertyValueFactory <> ("municipio"));
        clcProvincia.setCellValueFactory(new PropertyValueFactory <> ("provincia"));
        clcCedulaBi.setCellValueFactory(new PropertyValueFactory <> ("cedulaBi"));
            
            
        } catch (Exception e) {
        }
        
    } 
    
    
    public void accionarRegInscricao(){
        String modalidade = String.valueOf(tgpModalidade);
        if(rbnConfirmacao.isSelected()){
            modalidade = "CONFIRMAÇAO";
        }else if(rbnMatricula.isSelected()){
            modalidade = "MATRÍCULA";
        }else{
            Alert mensagem = new Alert(AlertType.ERROR);
            mensagem.setTitle("MODALIDADE");
            mensagem.setContentText("Informe a modalidade.");
            mensagem.setHeaderText("ESCOLHA DA MODALIDADE");
            mensagem.show();
        }

        Inscricao ins = new Inscricao(
                0,
                Date.valueOf(dpkDataInscricao.getValue()),
                modalidade,
                Integer.valueOf(txtNumero.getText()),
                cboAluno.getSelectionModel().getSelectedItem(),
                cboClasse.getSelectionModel().getSelectedItem(),
                cboCurso.getSelectionModel().getSelectedItem(),
                cboTurma.getSelectionModel().getSelectedItem(),
                cboSala.getSelectionModel().getSelectedItem(),
                cboTurno.getSelectionModel().getSelectedItem(),
                cboUsuario.getSelectionModel().getSelectedItem(),
                txtObs.getText()
                
        );
      conexao = new ConnectionLayer();
      conexao.connectar();
      int pst = ins.registarInscricao(getConnection());
        if(pst == 1){
            listaInscricao.add(ins);
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("CADASTRO DO ALUNO");
            info.setContentText("Aluno cadastrado com sucesso.");
            info.setHeaderText("RESULTADO DO CADASTRO");
            limpar();
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
            
        }else{
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("CADASTRO DO ALUNO");
            info.setContentText("Aluno não cadastrado.");
            info.setHeaderText("RESULTADO DO CADASTRO");
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
            
            
        }
        
    }
    
    public void limpar(){
        txtIdInscricao.setText(null);
        dpkDataInscricao.setValue(null);
        rbnConfirmacao.setSelected(false);
        rbnMatricula.setSelected(false);
        txtNumero.setText(null);
        cboAluno.setValue(null);
        cboClasse.setValue(null);
        cboCurso.setValue(null);
        cboTurma.setValue(null);
        cboSala.setValue(null);
        cboTurno.setValue(null);
        cboUsuario.setValue(null);
        txtObs.setText(null);        
        btnSalvar.setDisable(false);
        btnActualizar.setDisable(true);
        btnProcurar.setDisable(false);
        btnDeletar.setDisable(true);
    }
    
    public void gerarSelecao(){
        tbViewInscricao.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Inscricao>(){
            @Override
            public void changed(ObservableValue<? extends Inscricao> observable, Inscricao oldValue, Inscricao newValue) {
                if(newValue !=null){
                    if(newValue.getModalidade().equals("CONFIRMAÇÃO")){
                        rbnConfirmacao.setSelected(true);
                        rbnMatricula.setSelected(false);
                    }else if(newValue.getModalidade().equals("MATRÍCULA")){
                        rbnConfirmacao.setSelected(false);
                        rbnMatricula.setSelected(true);
                    }
                }
                txtIdInscricao.setText(String.valueOf(newValue.getIdInscricao()));
                dpkDataInscricao.setValue(newValue.getDataInscricao().toLocalDate());
                txtNumero.setText(String.valueOf(newValue.getNumero()));
                cboAluno.setValue(newValue.getAluno());
                cboClasse.setValue(newValue.getClasse());
                cboCurso.setValue(newValue.getCurso());
                cboTurma.setValue(newValue.getTurma());
                cboSala.setValue(newValue.getSala());
                cboTurno.setValue(newValue.getTurno());
                cboUsuario.setValue(newValue.getUsuario());
                txtObs.setText(newValue.getObsInscricao());
                btnSalvar.setDisable(true);
                btnActualizar.setDisable(false);
                btnProcurar.setDisable(true);
                btnDeletar.setDisable(false);
            }
                    
                }
        );
    }
    
    //método para deletar
    public void accionarDeletar(){
        conexao = new ConnectionLayer();
        conexao.connectar();
        int pst = tbViewInscricao.getSelectionModel().getSelectedItem().deletar(conexao.getConnection());
        try {
            conexao.connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLInsAlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pst == 1){
            listaInscricao.remove(tbViewInscricao.getSelectionModel().getSelectedIndex());
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("DELETANDO");
            info.setHeaderText("A DELETAR A INSCRIÇÃO");
            info.setContentText("Inscrição deletada com sucesso.");
            limpar();
            info.show();
        }else{
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("DELETANDO");
            info.setHeaderText("A DELETAR A INSCRIÇÃO");
            info.setContentText("Inscrição deletada com sucesso.");
            limpar();
            info.show();
            btnSalvar.setDisable(true);
            btnActualizar.setDisable(false);
            btnProcurar.setDisable(true);
            btnDeletar.setDisable(false);
        }
    }
    
    //método actualizar
    public void accionarActualizar(){
        String modalidade = String.valueOf(tgpModalidade);
        if(rbnConfirmacao.isSelected()){
            modalidade = "CONFIRMAÇÃO";
        }else if(rbnMatricula.isSelected()){
            modalidade = "MATRÍCULA";
        }else{
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("ACTUALIZANDO");
            info.setContentText("Informe a modalidade da inscrição.");
            info.setHeaderText("A ACTUALIZAR A INSCRIÇÃO");
            info.show();
        }
        
        Inscricao ins = new Inscricao(
                Integer.valueOf(txtIdInscricao.getText()),
                Date.valueOf(dpkDataInscricao.getValue()),
                modalidade,
                Integer.valueOf(txtNumero.getText()),
                cboAluno.getSelectionModel().getSelectedItem(),
                cboClasse.getSelectionModel().getSelectedItem(),
                cboCurso.getSelectionModel().getSelectedItem(),
                cboTurma.getSelectionModel().getSelectedItem(),
                cboSala.getSelectionModel().getSelectedItem(),
                cboTurno.getSelectionModel().getSelectedItem(),
                cboUsuario.getSelectionModel().getSelectedItem(),
                txtObs.getText()
        );
    conexao = new ConnectionLayer();
    conexao.connectar();
        int pst = ins.actualizar(conexao.getConnection());
        if(pst == 1){
            listaInscricao.set(tbViewInscricao.getSelectionModel().getSelectedIndex(), ins);
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("ACTUALIZANDO");
            info.setContentText("Inscrição actualizada com sucesso.");
            info.setHeaderText("A ACTUALIZAR A INSCRIÇÃO");
            limpar();
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
        }else{
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("ACTUALIZAÇÃO DA INSCRIÇÃO");
            info.setContentText("Inscrição não actualizada.");
            info.setHeaderText("RESULTADO DA ACTUALIZAÇÃO");
            btnSalvar.setDisable(true);
            btnActualizar.setDisable(false);
            btnProcurar.setDisable(true);
            btnDeletar.setDisable(false);
            btnLimpar.setDisable(false);
            info.show();
        }
    }
    
    
}
