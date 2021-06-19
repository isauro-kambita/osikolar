package ao.com.osikolar.layouts;

import ao.com.osikolar.dal.ConnectionLayer;
import ao.com.osikolar.javaClasses.Funcionario;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Ghil
 */
public class PessoalController implements Initializable {

    //Buttons
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnDeletar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Button btnProcurar;

    @FXML
    private TableView<Funcionario> tblTableView;

    //TableColumns
    @FXML
    private TableColumn<Funcionario, Number> clcId;
    @FXML
    private TableColumn<Funcionario, String> clcNome;
    @FXML
    private TableColumn<Funcionario, String> clcGenero;
    @FXML
    private TableColumn<Funcionario, String> clcFuncao;
    @FXML
    private TableColumn<Funcionario, String> clcHabilitacao;
    @FXML
    private TableColumn<Funcionario, String> clcAndFormacao;
    @FXML
    private TableColumn<Funcionario, String> clcEspecialidade;
    @FXML
    private TableColumn<Funcionario, String> clcClasseGrau;
    @FXML
    private TableColumn<Funcionario, String> clcEstado;

    //Form components
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;
    @FXML
    private RadioButton rbnFeminino;
    @FXML
    private RadioButton rbnMasculino;
    @FXML
    private TextField txtFuncao;
    @FXML
    private RadioButton rbnConcluida;
    @FXML
    private RadioButton rbnFrequencia;
    @FXML
    private RadioButton rbnMedio;
    @FXML
    private RadioButton rbnLicenciatura;
    @FXML
    private RadioButton rbnMestrado;
    @FXML
    private RadioButton rbnDoutoramento;
    @FXML
    private TextArea txaObs;
    @FXML
    private DatePicker dpkDataNascimento;
    @FXML
    private TextField txtEspecialidade;
    @FXML
    private RadioButton rbnActivo;
    @FXML
    private RadioButton rbnDevolvido;
    @FXML
    private RadioButton rbnFalecido;
    @FXML
    private RadioButton rbnTransferido;
    @FXML
    private TextField txtBi;
    @FXML
    private RadioButton rbnTecnicoPrimeira;
    @FXML
    private RadioButton rbnTecnicoSegunda;
    @FXML
    private RadioButton rbnTecnicoTerceira;

    //ToggleGroups
    @FXML
    private ToggleGroup tgpGeneroFuncionario;
    @FXML
    private ToggleGroup tgpEstadoHabilitacao;
    @FXML
    private ToggleGroup tgpHabilitacao;
    @FXML
    private ToggleGroup tgpEstado;
    @FXML
    private ToggleGroup tgpClasseGrau;

    //Observable lists
    private ObservableList listaFuncionario;

    private ConnectionLayer conexao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conexao = new ConnectionLayer();
            conexao.connectar();
            listaFuncionario = FXCollections.observableArrayList();

            Funcionario.gerarFuncionario(conexao.getConnection(), listaFuncionario);

            tblTableView.setItems(listaFuncionario);

            //Cell values
            clcId.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
            clcNome.setCellValueFactory(new PropertyValueFactory<>("nomeFuncionario"));
            clcGenero.setCellValueFactory(new PropertyValueFactory<>("generoFuncionario"));
            clcFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
            clcHabilitacao.setCellValueFactory(new PropertyValueFactory<>("grauFormacao"));
            clcAndFormacao.setCellValueFactory(new PropertyValueFactory<>("estadoFormacao"));
            clcEspecialidade.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
            clcClasseGrau.setCellValueFactory(new PropertyValueFactory<>("classeGrau"));
            clcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
            gerarSelecao();
            btnActualizar.setDisable(true);
            btnDeletar.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void accionarSalvar(ActionEvent event) {
    }

    @FXML
    private void accionarActualizar(ActionEvent event) {
    }

    @FXML
    private void accionarDeletar(ActionEvent event) {
    }

    @FXML
    private void limpar(ActionEvent event) {
    }

    public void gerarSelecao() {
        tblTableView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Funcionario>() {
            @Override
            public void changed(ObservableValue<? extends Funcionario> observable, Funcionario oldValue, Funcionario newValue) {
                txtId.setText(String.valueOf(newValue.getIdFuncionario()));
                txtNome.setText(newValue.getNomeFuncionario());
                String genero = newValue.getGeneroFuncionario();
                switch (genero) {
                    case "M":
                        rbnMasculino.setSelected(true);
                        rbnFeminino.setSelected(false);
                        break;
                    case "F":
                        rbnMasculino.setSelected(false);
                        rbnFeminino.setSelected(true);
                        break;
                }
                txtFuncao.setText(newValue.getFuncao());
                String grauFormacao = newValue.getGrauFormacao();
                switch (grauFormacao) {
                    case "MÉDIO":
                        rbnMedio.setSelected(true);
                        rbnLicenciatura.setSelected(false);
                        rbnMestrado.setSelected(false);
                        rbnDoutoramento.setSelected(false);
                        break;
                    case "LICENCIATURA":
                        rbnMedio.setSelected(false);
                        rbnLicenciatura.setSelected(true);
                        rbnMestrado.setSelected(false);
                        rbnDoutoramento.setSelected(false);
                        break;
                    case "MESTRADO":
                        rbnMedio.setSelected(false);
                        rbnLicenciatura.setSelected(false);
                        rbnMestrado.setSelected(true);
                        rbnDoutoramento.setSelected(false);
                        break;
                    case "DOUTORAMENTO":
                        rbnMedio.setSelected(false);
                        rbnLicenciatura.setSelected(false);
                        rbnMestrado.setSelected(false);
                        rbnDoutoramento.setSelected(true);
                        break;
                }
                String estadoFormacao = newValue.getEstadoFormacao();
                switch (estadoFormacao) {
                    case "CONCLUÍDA":
                        rbnConcluida.setSelected(true);
                        rbnFrequencia.setSelected(false);
                        break;
                    case "FREQUÊNCIA":
                        rbnConcluida.setSelected(false);
                        rbnFrequencia.setSelected(true);
                        break;
                }
                txtEspecialidade.setText(newValue.getEspecialidade());
                dpkDataNascimento.setValue(newValue.getDataFuncionario().toLocalDate());
                String classeGrau = newValue.getClasseGrau();
                switch (classeGrau) {
                    case "TEC. 1ª":
                        rbnTecnicoPrimeira.setSelected(true);
                        rbnTecnicoSegunda.setSelected(false);
                        rbnTecnicoTerceira.setSelected(false);
                        break;
                    case "TEC. 2ª":
                        rbnTecnicoPrimeira.setSelected(false);
                        rbnTecnicoSegunda.setSelected(true);
                        rbnTecnicoTerceira.setSelected(false);
                        break;
                    case "TEC. 3ª":
                        rbnTecnicoPrimeira.setSelected(false);
                        rbnTecnicoSegunda.setSelected(false);
                        rbnTecnicoTerceira.setSelected(true);
                        break;
                }
                String estado = newValue.getEstado();
                switch (estado) {
                    case "ACTIVO":
                        rbnActivo.setSelected(true);
                        rbnDevolvido.setSelected(false);
                        rbnFalecido.setSelected(false);
                        rbnTransferido.setSelected(false);
                        break;
                    case "DEVOLVIDO":
                        rbnActivo.setSelected(false);
                        rbnDevolvido.setSelected(true);
                        rbnFalecido.setSelected(false);
                        rbnTransferido.setSelected(false);
                        break;
                    case "FALECIDO":
                        rbnActivo.setSelected(false);
                        rbnDevolvido.setSelected(false);
                        rbnFalecido.setSelected(true);
                        rbnTransferido.setSelected(false);
                        break;
                    case "TRANSFERIDO":
                        rbnActivo.setSelected(false);
                        rbnDevolvido.setSelected(false);
                        rbnFalecido.setSelected(false);
                        rbnTransferido.setSelected(true);
                        break;
                }
                txtBi.setText(newValue.getBiFuncionario());
                txaObs.setText(newValue.getObsFuncionario());
                btnSalvar.setDisable(true);
                btnActualizar.setDisable(false);
                btnProcurar.setDisable(true);
                btnDeletar.setDisable(false);

            }

        }
        );
    }

    public void acionarInserirFuncionario() {
        String habilitacao = String.valueOf(tgpHabilitacao);
        if (rbnMedio.isSelected()) {
            habilitacao = "MÉDIO";
        } else if (rbnLicenciatura.isSelected()) {
            habilitacao = "LICENCIATURA";
        } else if (rbnMestrado.isSelected()) {
            habilitacao = "MESTRADO";
        } else if (rbnDoutoramento.isSelected()) {
            habilitacao = "DOUTORAMENTO";
        } else {
            JOptionPane.showMessageDialog(null, "INFORME A HABILITAÇÃO.");
        }

        String classeGrau = String.valueOf(tgpClasseGrau);
        if (rbnTecnicoPrimeira.isSelected()) {
            classeGrau = "TEC. 1ª";
        } else if (rbnTecnicoSegunda.isSelected()) {
            classeGrau = "TEC. 2ª";
        } else if (rbnTecnicoTerceira.isSelected()) {
            classeGrau = "TEC. 3ª";
        } else {
            JOptionPane.showMessageDialog(null, "INFORME A CATEGORIA (EX: TEC. 1ª).");
        }

        String estado = String.valueOf(tgpEstado);
        if (rbnActivo.isSelected()) {
            estado = "ACTIVO";
        } else if (rbnDevolvido.isSelected()) {
            estado = "DEVOLVIDO";
        } else if (rbnFalecido.isSelected()) {
            estado = "FALECIDO";
        } else if (rbnTransferido.isSelected()) {
            estado = "TRANSFERIDO";
        } else {
            JOptionPane.showMessageDialog(null, "INFORME O ESTADO DO FUNCIONÁRIO (EX: ACTIVO).");
        }

        String genero = String.valueOf(tgpGeneroFuncionario);
        if (rbnFeminino.isSelected()) {
            genero = "F";
        } else if (rbnMasculino.isSelected()) {
            genero = "M";
        } else {
            JOptionPane.showMessageDialog(null, "INFORME O GÊNERO.");
        }

        String estadoHabilitacao = String.valueOf(tgpEstadoHabilitacao);
        if (rbnConcluida.isSelected()) {
            estadoHabilitacao = "CONCLUÍDA";
        } else if (rbnFrequencia.isSelected()) {
            estadoHabilitacao = "FREQUÊNCIA";
        } else {
            JOptionPane.showMessageDialog(null, "INFORME O ESTADO DA FORMAÇÃO (EX: CONCLUÍDA).");
        }

        Funcionario func = new Funcionario(
                0,
                txtNome.getText().toUpperCase(),
                genero,
                txtFuncao.getText().toUpperCase(),
                habilitacao,
                estadoHabilitacao,
                txtEspecialidade.getText().toUpperCase(),
                Date.valueOf(dpkDataNascimento.getValue()),
                classeGrau,
                estado,
                txtBi.getText().toUpperCase(),
                txaObs.getText().toUpperCase()
        );
        conexao = new ConnectionLayer();
        conexao.connectar();
        int pst = func.inserirFuncionario(conexao.getConnection());
        if (pst == 1) {
            listaFuncionario.add(func);
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("SALVANDO OS DADOS DO FUNCIONÁRIO");
            info.setContentText("Funcionário cadastrado com sucesso.");
            info.setHeaderText("SALVAR FUNCIONÁRIO");
            limparCampos();
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
        } else {
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("SALVANDO OS DADOS DO FUNCIONÁRIO");
            info.setContentText("Funcionário não cadastrado!");
            info.setHeaderText("SALVAR FUNCIONÁRIO");
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
        }

    }

    public void limparCampos() {
        txtId.setText(null);
        txtNome.setText(null);
        rbnMasculino.setSelected(false);
        rbnFeminino.setSelected(false);
        txtFuncao.setText(null);
        rbnMedio.setSelected(false);
        rbnLicenciatura.setSelected(false);
        rbnMestrado.setSelected(false);
        rbnDoutoramento.setSelected(false);
        rbnConcluida.setSelected(false);
        rbnFrequencia.setSelected(false);
        txtEspecialidade.setText(null);
        dpkDataNascimento.setValue(null);
        rbnTecnicoPrimeira.setSelected(false);
        rbnTecnicoSegunda.setSelected(false);
        rbnTecnicoTerceira.setSelected(false);
        rbnActivo.setSelected(false);
        rbnDevolvido.setSelected(false);
        rbnFalecido.setSelected(false);
        rbnTransferido.setSelected(false);
        txtBi.setText(null);
        txaObs.setText(null);
        btnSalvar.setDisable(false);
        btnProcurar.setDisable(false);
        btnActualizar.setDisable(true);
        btnDeletar.setDisable(true);
    }

    //actualizar
    public void acionarActualizarFuncionario(){
        String habilitacao = String.valueOf(tgpHabilitacao);
        if(rbnMedio.isSelected()){
            habilitacao = "MÉDIO";
        }else  if(rbnLicenciatura.isSelected()){
            habilitacao = "LICENCIATURA";
        }else if(rbnMestrado.isSelected()){
            habilitacao = "MESTRADO";
        }else if(rbnDoutoramento.isSelected()){
            habilitacao = "DOUTORAMENTO";
        }else{
            JOptionPane.showMessageDialog(null, "Informe a habilitação.");
        }
        
        String classeGrau = String.valueOf(tgpClasseGrau);
        if(rbnTecnicoPrimeira.isSelected()){
            classeGrau = "TEC. 1ª";
        }else  if(rbnTecnicoSegunda.isSelected()){
            classeGrau = "TEC. 2ª";
        }else if(rbnTecnicoTerceira.isSelected()){
            classeGrau = "TEC. 3ª";
        }else{
            JOptionPane.showMessageDialog(null, "Informe a categoria.");
        }
        
        String estado = String.valueOf(tgpEstado);
        if(rbnActivo.isSelected()){
            estado = "ACTIVO";
        }else  if(rbnDevolvido.isSelected()){
            estado = "DEVOLVIDO";
        }else if(rbnFalecido.isSelected()){
            estado = "FALECIDO";
        }else if(rbnTransferido.isSelected()){
            estado = "TRANSFERIDO";
        }else{
            JOptionPane.showMessageDialog(null, "Informe o Estado.");
        }
        
         Funcionario func = new Funcionario(
                 Integer.valueOf(txtId.getText()),
                 txtNome.getText().toUpperCase(),
                 rbnFeminino.isSelected() ? "F" : "M",
                 txtFuncao.getText().toUpperCase(),
                 habilitacao,
                 rbnConcluida.isSelected() ? "CONCLUÍDA" : "FREQUÊNCIA",
                 txtEspecialidade.getText().toUpperCase(),
                 Date.valueOf(dpkDataNascimento.getValue()),
                 classeGrau,
                 estado,
                 txtBi.getText().toUpperCase(),
                 txaObs.getText().toUpperCase()
         );
         
         conexao = new ConnectionLayer();
         conexao.connectar();
         
         int pst = func.actualizar(conexao.getConnection());
         if(pst == 1){
             listaFuncionario.set(tblTableView.getSelectionModel().getSelectedIndex(), func);
             Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("ACTUALIZANDO DO FUNCIONARIO");
            info.setContentText("Funcionario actualizado com sucesso.");
            info.setHeaderText("A ACTUALIZAR O A INSCRIÇÃO");
            limparCampos();
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(false);
            btnDeletar.setDisable(true);
            info.show();
         }else{
             Alert info = new Alert(AlertType.ERROR);
            info.setTitle("ACTUALIZAÇÃO DO FUNCIONARIO");
            info.setContentText("Funcionario não actualizado.");
            info.setHeaderText("RESULTADO DA ACTUALIZAÇÃO");
            btnSalvar.setDisable(true);
            btnActualizar.setDisable(false);
            btnProcurar.setDisable(true);
            btnDeletar.setDisable(false);
            btnLimpar.setDisable(false);
            info.show();
         }
    }
    
    //Deletar
    public void acionarDeletarFuncionario(){
        conexao = new ConnectionLayer();
        conexao.connectar();
        
        int pst = tblTableView.getSelectionModel().getSelectedItem().deletarFuncionario(conexao.getConnection());
        try {
            conexao.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(pst == 1){
            listaFuncionario.remove(tblTableView.getSelectionModel().getSelectedIndex());
            Alert info = new Alert(AlertType.INFORMATION);
            info.setTitle("FUNCIONARIO");
            info.setContentText("Funcionario deletado com sucesso.");
            info.setHeaderText("DELETANDO O FUNCIONARIO");
            limparCampos();
            info.show();
        }else{
            Alert info = new Alert(AlertType.ERROR);
            info.setTitle("FUNCIONARIO");
            info.setContentText("Funcionario não deletado.");
            info.setHeaderText("DELETANDO O FUNCIONARIO");
            btnSalvar.setDisable(false);
            btnActualizar.setDisable(true);
            btnProcurar.setDisable(true);
            btnDeletar.setDisable(true);
            limparCampos();
            info.show();
        }
    }
}
