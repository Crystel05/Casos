package VIEW;

import Controlador.Inicializador;
import MODEL.ComboManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ArrayList;
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
        Inicializador.inicializar();
        ArrayList<String> combosL = ComboManager.getAllKeys();
        ObservableList<String> comb = FXCollections.observableArrayList();
        comb.addAll(combosL);
        combos.setItems(comb);

        ArrayList<String> platosFuertes = ComboManager.getAllKeys();
        ObservableList<String> platos = FXCollections.observableArrayList();
        comb.addAll(platosFuertes);
        platosFuerte.setItems(platos);

        ArrayList<String> bebidasL = ComboManager.getAllKeys();
        ObservableList<String> bebs = FXCollections.observableArrayList();
        comb.addAll(bebidasL);
        bebidas.setItems(bebs);

        ArrayList<String> adicions = ComboManager.getAllKeys();
        ObservableList<String> adis = FXCollections.observableArrayList();
        comb.addAll(adicions);
        adicionales.setItems(adis);
    }
}
