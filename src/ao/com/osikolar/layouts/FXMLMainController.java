package ao.com.osikolar.layouts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Ghil
 */
public class FXMLMainController implements Initializable {
    
    Label lblHora;
    @FXML
    private BorderPane mainContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void btnAluno(javafx.event.ActionEvent event) throws IOException {
        Parent aluno = FXMLLoader.load(getClass().getResource("Aluno.fxml"));
        mainContainer.setCenter(aluno);
    }
    
    @FXML
    private void btnInsAluno(javafx.event.ActionEvent event) throws IOException{
        Parent insAluno = FXMLLoader.load(getClass().getResource("FXMLInsAluno.fxml"));
        mainContainer.setCenter(insAluno);
    }

    @FXML
    private void btnPessoal(javafx.event.ActionEvent event) throws IOException{
        Parent pessoal = FXMLLoader.load(getClass().getResource("FXMLPessoal.fxml"));
        mainContainer.setCenter(pessoal);
    }

    @FXML
    private void btnUsuario(javafx.event.ActionEvent event) throws IOException {
        Parent usuario = FXMLLoader.load(getClass().getResource("FXMLUsuario.fxml"));
        mainContainer.setCenter(usuario);
    }
    
    
}
