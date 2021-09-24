package VIEW;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextArea factura;

    @FXML
    private Text totalFac;

    @FXML
    private ComboBox<String> adicionales;

    @FXML
    private ComboBox<String> combos;

    @FXML
    private ComboBox<String> platosFuerte;

    @FXML
    private ComboBox<String> bebidas;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        
    }
}
