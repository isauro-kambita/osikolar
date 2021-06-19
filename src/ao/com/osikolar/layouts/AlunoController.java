package ao.com.osikolar.layouts;

import ao.com.osikolar.dal.ConnectionLayer;
import ao.com.osikolar.javaClasses.Aluno;
import ao.com.osikolar.javaClasses.Provincia;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
public class AlunoController implements Initializable {
    
    
    //Columns
    @FXML private TableColumn <Aluno, String> clcId;
    @FXML private TableColumn <Aluno, String> clcNome;
    @FXML private TableColumn <Aluno, String> clcGenero;
    @FXML private TableColumn <Aluno, String> clcAniversario;
    @FXML private TableColumn <Aluno, String> clcEstado;
    @FXML private TableColumn <Aluno, Number> clcAnoLectivo;
    @FXML private TableColumn <Aluno, String> clcMunicipio;
    @FXML private TableColumn <Aluno, String> clcProvincia;
    @FXML private TableColumn <Aluno, Number> clcCedulaBi;
    
    //Buttons
    @FXML private Button btnSalvar;
    @FXML private Button btnActualizar;
    @FXML private Button btnDeletar;
    @FXML private Button btnLimpar;
    @FXML private Button btnProcurar;
    
    @FXML private ToggleGroup estadoAluno;
    
    
    //GUI Components
    @FXML private ComboBox <Provincia> cboProvincia;
    @FXML private TableView <Aluno> tblTableView;
    
    //Fields
    @FXML private TextField txtId;
    @FXML private TextField txtNome;
    @FXML private DatePicker dpkAniversario;
    @FXML private TextField txtPai;
    @FXML private TextField txtMae;
    @FXML private TextField txtNaturalidade;
    @FXML private TextField txtMunicipio;
    @FXML private TextField txtCedulaBi;
    @FXML private DatePicker dpkDataArquivo;
    @FXML private TextField txtAnoLectivo;
    @FXML private TextArea txaObs;
    @FXML private RadioButton rbnFeminino;
    @FXML private RadioButton rbnMasculino;
    @FXML private RadioButton rbnActivo;
    @FXML private RadioButton rbnDesistido;
    @FXML private RadioButton rbnFalecido;
    @FXML private RadioButton rbnTransferido;
    
    
    
    //Collections
    private ObservableList <Aluno> listaAluno;
    private ObservableList <Provincia> listaProvincia;
    
    private ConnectionLayer conexao;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnSalvar.setDisable(false);
        btnActualizar.setDisable(true);
        btnProcurar.setDisable(false);
        btnDeletar.setDisable(true);
        try {
        conexao = new ConnectionLayer();
        conexao.connectar();
        
        listaAluno = FXCollections.observableArrayList();
        listaProvincia = FXCollections.observableArrayList();
        
        Aluno.gerarAluno(ConnectionLayer.getConnection(), listaAluno);
        Provincia.gerarProvincia(conexao.getConnection(), listaProvincia);
        
        cboProvincia.setItems(listaProvincia);
        tblTableView.setItems(listaAluno);
        
        //Colunas
        clcId.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("idAluno"));
        clcNome.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("nomeAluno"));
        clcGenero.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("generoAluno"));
        clcAniversario.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("dataAluno"));
        clcEstado.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("estadoAluno"));
        clcAnoLectivo.setCellValueFactory(new PropertyValueFactory <Aluno, Number> ("anoLectivo"));
        clcMunicipio.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("municipio"));
        clcProvincia.setCellValueFactory(new PropertyValueFactory <Aluno, String> ("provincia"));
        clcCedulaBi.setCellValueFactory(new PropertyValueFactory <Aluno, Number> ("cedulaBi"));
        gerarSelecao();
        ConnectionLayer.connection.close();
        
        } catch (Exception e) {
            Alert mensagem = new Alert(AlertType.ERROR);
            mensagem.setTitle("RESULTADO DA BUSCA AUTOMÁTICA");
            mensagem.setContentText("Erro na busca automática!");
            mensagem.setHeaderText("ERRO DE BUSCA");
            mensagem.show();
        }
    }
    
    public void gerarSelecao(){
        tblTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Aluno>(){
            @Override
            public void changed(ObservableValue<? extends Aluno> observable, Aluno oldValue, Aluno newValue) {
                if(newValue !=null){
                    if(newValue.getGeneroAluno().equals("F")){
                        rbnFeminino.setSelected(true);
                        rbnMasculino.setSelected(false);
                    }else if(newValue.getGeneroAluno().equals("M")){
                        rbnFeminino.setSelected(false);
                        rbnMasculino.setSelected(true);
                    }
                    String estado = newValue.getEstadoAluno();
                    switch(estado){
                        case "ACTIVO":
                             rbnActivo.setSelected(true);
                             rbnDesistido.setSelected(false);
                             rbnFalecido.setSelected(false);
                             rbnTransferido.setSelected(false);
                            break;
                        case "DESISTIDO":
                             rbnActivo.setSelected(false);
                             rbnDesistido.setSelected(true);
                             rbnFalecido.setSelected(false);
                             rbnTransferido.setSelected(false);
                            break;
                        case "FALECIDO":
                             rbnActivo.setSelected(false);
                             rbnDesistido.setSelected(true);
                             rbnFalecido.setSelected(true);
                             rbnTransferido.setSelected(false);
                            break;
                        case "TRANSFERIDO":
                             rbnActivo.setSelected(false);
                             rbnDesistido.setSelected(false);
                             rbnFalecido.setSelected(false);
                             rbnTransferido.setSelected(true);
                            break;
                            
                    }
                    txtId.setText(String.valueOf(newValue.getIdAluno()));
                    txtNome.setText(newValue.getNomeAluno());
                    dpkAniversario.setValue(newValue.getDataAluno().toLocalDate());
                    txtPai.setText(newValue.getPai());
                    txtMae.setText(newValue.getMae());
                    txtNaturalidade.setText(newValue.getNatural());
                    txtMunicipio.setText(newValue.getMunicipio());
                    cboProvincia.setValue(newValue.getProvincia());
                    txtCedulaBi.setText(newValue.getCedulaBi());
                    txtAnoLectivo.setText(String.valueOf(newValue.getAnoLectivo()));
                    txaObs.setText(newValue.getObsAluno());
                    dpkDataArquivo.setValue(newValue.getDataArquivo().toLocalDate());
                    btnSalvar.setDisable(true);
                    btnActualizar.setDisable(false);
                    btnProcurar.setDisable(true);
                    btnDeletar.setDisable(false);
                }
            }
                            
                        });
    }
    
    public void accionarSalvar(){
        String item = String.valueOf(estadoAluno);
        if(rbnActivo.isSelected()){
            item = "ACTIVO";
        }else if(rbnDesistido.isSelected()){
            item = "DESISTIDO";
        }else if(rbnFalecido.isSelected()){
            item = "FALECIDO";
        }else if(rbnTransferido.isSelected()){
            item = "TRANSFERIDO";
        }else{
            Alert mensagem = new Alert(AlertType.ERROR);
            mensagem.setTitle("ESCOLHA DO ESTADO");
            mensagem.setContentText("Selecione o estado.");
            mensagem.setHeaderText("MENSAGEM DA ESCOLHA DO ESTADO");
            mensagem.show();
        }
        Aluno a = new Aluno(
                0,
                txtNome.getText().toUpperCase(),
                rbnFeminino.isSelected()?"F":"M",
                Date.valueOf(dpkAniversario.getValue()),
                txtPai.getText().toUpperCase(),
                txtMae.getText().toUpperCase(),
                txtNaturalidade.getText().toUpperCase(),
                txtMunicipio.getText().toUpperCase(),
                cboProvincia.getSelectionModel().getSelectedItem(),
                txtCedulaBi.getText().toUpperCase(),
                Date.valueOf(dpkDataArquivo.getValue()),
                item,
                Integer.valueOf(txtAnoLectivo.getText()),
                txaObs.getText().toUpperCase()
                );
                conexao = new ConnectionLayer();
                conexao.connectar();
                int pst = a.salvar(conexao.getConnection());
                
            if(pst == 1){
            listaAluno.add(a);
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
    
    public void accionarActualizar(){
        String item = String.valueOf(estadoAluno);
        if(rbnActivo.isSelected()){
            item = "ACTIVO";
        }else if(rbnDesistido.isSelected()){
            item = "DESISTIDO";
        }else if(rbnFalecido.isSelected()){
            item = "FALECIDO";
        }else if(rbnTransferido.isSelected()){
            item = "TRANSFERIDO";
        }else{
            Alert mensagem = new Alert(AlertType.ERROR);
            mensagem.setTitle("ESCOLHA DO ESTADO");
            mensagem.setContentText("Selecione o estado.");
            mensagem.setHeaderText("MENSAGEM DA ESCOLHA DO ESTADO");
            mensagem.show();
        }
        Aluno b = new Aluno(
                Integer.valueOf(txtId.getText()),
                txtNome.getText().toUpperCase(),
                rbnFeminino.isSelected()?"F":"M",
                Date.valueOf(dpkAniversario.getValue()),
                txtPai.getText().toUpperCase(),
                txtMae.getText().toUpperCase(),
                txtNaturalidade.getText().toUpperCase(),
                txtMunicipio.getText().toUpperCase(),
                cboProvincia.getSelectionModel().getSelectedItem(),
                txtCedulaBi.getText().toUpperCase(),
                Date.valueOf(dpkDataArquivo.getValue()),
                item,
                Integer.valueOf(txtAnoLectivo.getText()),
                txaObs.getText().toUpperCase()
                );
    conexao = new ConnectionLayer();
    conexao.connectar();
    int pst = b.actualizar(conexao.getConnection());
                
            if(pst == 1){
            listaAluno.set(tblTableView.getSelectionModel().getSelectedIndex(), b);
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("ACTUALIZAÇÃO DO ALUNO");
            info.setContentText("Aluno actualizado com sucesso.");
            info.setHeaderText("RESULTADO DA ACTUALIZAÇÃO");
            limpar();
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
            
        }else{
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("ACTUALIZAÇÃO DO ALUNO");
            info.setContentText("Aluno não actualizado.");
            info.setHeaderText("RESULTADO DA ACTUALIZAÇÃO");
            btnSalvar.setDisable(true);
            btnActualizar.setDisable(false);
            btnProcurar.setDisable(true);
            btnDeletar.setDisable(false);
            btnLimpar.setDisable(false);
            info.show();
            
        }
        
    }
    
    public void accionarDeletar() throws SQLException{
        conexao = new ConnectionLayer();
        conexao.connectar();
        int pst = tblTableView.getSelectionModel().getSelectedItem().deletar(conexao.getConnection());
        conexao.connection.close();
        
        if (pst == 1) {
            listaAluno.remove(tblTableView.getSelectionModel().getSelectedIndex());
            Alert msg = new Alert(AlertType.INFORMATION);
            msg.setTitle("ELIMINACÃO CADASTRO.");
            msg.setContentText("Cadastro eliminado com sucesso");
            msg.setHeaderText("ELIMINANDO O CADASTRO");
            limpar();
            msg.show();
            
        }else{
            Alert msg = new Alert(AlertType.ERROR);
            msg.setTitle("ELIMINAÇÃO CADASTRO.");
            msg.setContentText("Cadastro não eliminado.");
            msg.setHeaderText("ELIMINANDO O CADASTRO");
            btnSalvar.setDisable(true);
            btnActualizar.setDisable(false);
            btnProcurar.setDisable(true);
            btnDeletar.setDisable(false);
            msg.show();
    }
   }
    
    public void limpar(){
        txtId.setText(null);
        txtNome.setText(null);
        dpkAniversario.setValue(null);
        txtPai.setText(null);
        txtMae.setText(null);
        txtNaturalidade.setText(null);
        txtMunicipio.setText(null);
        cboProvincia.setValue(null);
        txtCedulaBi.setText(null);
        txtAnoLectivo.setText(null);
        txaObs.setText(null);
        dpkDataArquivo.setValue(null);
        rbnFeminino.setSelected(false);
        rbnMasculino.setSelected(false);
        rbnActivo.setSelected(false);
        rbnDesistido.setSelected(false);
        rbnFalecido.setSelected(false);
        rbnTransferido.setSelected(false);
        
        btnSalvar.setDisable(false);
        btnActualizar.setDisable(true);
        btnProcurar.setDisable(false);
        btnDeletar.setDisable(true);
    }
    
    
}
