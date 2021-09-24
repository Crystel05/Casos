package VIEW;

import Controlador.Inicializador;
import MODEL.Combo;
import MODEL.ComboManager;
import MODEL.ComidaManager;
import MODEL.TipoComida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import javax.xml.soap.Text;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    String facturaString = "";

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

    @FXML
    public void agregarCombos(MouseEvent event){
        agregarCombosFact();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Inicializador.inicializar();
        ArrayList<String> combosL = ComboManager.getAllKeys();
        ObservableList<String> comb = FXCollections.observableArrayList();
        comb.addAll(combosL);
        combos.setItems(comb);

        ArrayList<String> platosFuertes = ComidaManager.getTypeKeys(TipoComida.Principal);
        ObservableList<String> platos = FXCollections.observableArrayList();
        platos.addAll(platosFuertes);
        platosFuerte.setItems(platos);

        ArrayList<String> bebidasL = ComidaManager.getTypeKeys(TipoComida.Bebida);
        ObservableList<String> bebs = FXCollections.observableArrayList();
        bebs.addAll(bebidasL);
        bebidas.setItems(bebs);

        ArrayList<String> adicions = ComidaManager.getTypeKeys(TipoComida.Adicional);
        ObservableList<String> adis = FXCollections.observableArrayList();
        adis.addAll(adicions);
        adicionales.setItems(adis);
    }

    private void agregarCombosFact(){

        String comboSelec = combos.getSelectionModel().getSelectedItem();
        if(!comboSelec.isEmpty()) {
            Combo combo = (Combo) ComboManager.getItem(comboSelec);
            if (combo.getAdicionales() != null && combo.getBebidas() != null) {
                facturaString = facturaString + "NUEVO" +"_" + combo.getNombre() + "_" + combo.getPlatoFuerte() + "_" + combo.getBebidas() + "_" +
                        combo.getAdicionales() + "_" + combo.getPrice();
            } else if (combo.getBebidas() != null && combo.getAdicionales() == null) {
                facturaString = facturaString + "NUEVO" + "_" + combo.getNombre() + "_" + combo.getPlatoFuerte() + "_" +
                        combo.getBebidas() + "_" + combo.getPrice();
            } else if (combo.getAdicionales() != null && combo.getBebidas() == null) {
                facturaString = facturaString + "NUEVO" +"_" + combo.getNombre() + "_" + combo.getPlatoFuerte() + "_" +
                        combo.getAdicionales() + "_" + combo.getPrice();
            } else if (combo.getAdicionales() == null && combo.getBebidas() == null) {
                facturaString = facturaString + "NUEVO" + "_" + combo.getNombre() + "_" + combo.getPlatoFuerte() + "_" + combo.getPrice();
            }
        }

        //otros ifs

        String[] combos = facturaString.split("NUEVO");
        System.out.println("Combos: " + facturaString);
    }

}
