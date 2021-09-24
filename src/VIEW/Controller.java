package VIEW;

import Controlador.ComboManager;
import Controlador.ComidaManager;
import Controlador.Inicializador;
import MODEL.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private String facturaString;
    private float precioTotal;
    private float precioComboCustom;
    private boolean platoFuerteSelccionado = false;

    @FXML
    private TextArea factura;

    @FXML
    private Text totalFa;

    @FXML
    private ComboBox<String> adicionales;

    @FXML
    private ComboBox<String> combos;

    @FXML
    private ComboBox<String> platosFuerte;

    @FXML
    private ComboBox<String> bebidas;

    @FXML
    private Text agregado;

    @FXML
    public void agregarCombos(MouseEvent event){
        hacerFactura();
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

    @FXML
    public void agregarCombo(MouseEvent event) throws IOException {
        agregarCombosPredefinido();
    }

    @FXML
    public void agregarAdicionales(MouseEvent event){
        agregarAdicional();
    }

    private void agregarAdicional() {
        if(!combos.getSelectionModel().isEmpty() && !adicionales.getSelectionModel().getSelectedItem().isEmpty()){
            Combo combo = (Combo) ComboManager.getItem(combos.getSelectionModel().getSelectedItem());
            Comida adicional = (Comida) ComidaManager.getItem(adicionales.getSelectionModel().getSelectedItem());
            combo.addAdicional(adicional);
            Comida ad = (Comida) combo.getAdicionales().get(combo.getAdicionales().size()-1);
            facturaString = facturaString + "_" + ad.getNombre() + "\t\t\t₡ " + ad.getPrecio();
            precioTotal = precioTotal + ad.getPrecio();
            if (combo.getNombre().equals("Personalizado")){
                precioComboCustom = precioComboCustom + ad.getPrecio();
            }
        }
    }

    @FXML
    public void agregarBebidas(MouseEvent event){
        agregarBebida();
    }

    private void agregarBebida(){
        if(!combos.getSelectionModel().isEmpty() && !bebidas.getSelectionModel().getSelectedItem().isEmpty()){
            Combo combo = (Combo) ComboManager.getItem(combos.getSelectionModel().getSelectedItem());
            Comida bebida = (Comida) ComidaManager.getItem(bebidas.getSelectionModel().getSelectedItem());
            combo.addBebida(bebida);
            Comida beb = (Comida) combo.getBebidas().get(combo.getBebidas().size()-1);
            facturaString = facturaString + "_" + beb.getNombre() + "\t\t\t₡ " + beb.getPrecio();
            precioTotal = precioTotal + beb.getPrecio();
            if (combo.getNombre().equals("Personalizado")){
                precioComboCustom = precioComboCustom + beb.getPrecio();
            }
        }
    }

    @FXML
    public void agregarPlatoFuertes(MouseEvent event){
        if (!platoFuerteSelccionado) {
            agregarPlatoFuerte();
            platoFuerteSelccionado = true;
        }
    }

    private void agregarPlatoFuerte() {
        if (combos.getSelectionModel().getSelectedItem().equals("Personalizado")){
            if (!platosFuerte.getSelectionModel().isEmpty()){
                Combo combo = (Combo) ComboManager.getItem(combos.getSelectionModel().getSelectedItem());
                Comida platoFuerte = (Comida) ComidaManager.getItem(platosFuerte.getSelectionModel().getSelectedItem());
                combo.setPlatoFuerte(platoFuerte);
                Comida plato = (Comida) combo.getPlatoFuerte();
                facturaString = facturaString + "_" + plato.getNombre() + "\t\t\t₡ " + plato.getPrecio();
                precioTotal = precioTotal + plato.getPrecio();
                precioComboCustom = precioComboCustom + plato.getPrecio();
            }
        }
    }

    private void agregarCombosPredefinido(){
        String comboSelec = combos.getSelectionModel().getSelectedItem();
        if(!comboSelec.isEmpty()) {
            if (!comboSelec.equals("Personalizado")) {
                Combo combo = (Combo) ComboManager.getItem(comboSelec);
                Comida platoFuerte = ((Comida) combo.getPlatoFuerte());
                precioTotal = precioTotal + combo.getPrice();
                facturaString = facturaString + "NUEVO" + "_" + combo.getNombre() + "\t\t\t\t₡ " + combo.getPrice() + "_"
                        + platoFuerte.getNombre() + "\t\t\t₡ " + platoFuerte.getPrecio();
                if (combo.getAdicionales() != null && combo.getBebidas() != null) {
                    facturaString = facturaString + "_" + combo.getFormatedBebida() + "_" + combo.getFormatedAdicionales(); //+ "_" + combo.getPrice();
                } else if (combo.getBebidas() != null && combo.getAdicionales() == null) {
                    facturaString = facturaString + "_" + combo.getFormatedBebida();  // + "_" + combo.getPrice();
                } else if (combo.getAdicionales() != null && combo.getBebidas() == null) {
                    facturaString = facturaString + "_" + combo.getFormatedAdicionales();// + "_" + combo.getPrice();
                }
            }else{
                facturaString = facturaString + "NUEVO_Personalizado";
            }
        }

    }

    private void hacerFactura(){
        String[] combos = facturaString.split("NUEVO");
        combos[0] = "";
        StringBuilder ordenado = new StringBuilder();
        for(String combo: combos){
            String[] comboAct = combo.split("_");
            for(String elemento: comboAct){
                String[] otros = elemento.split("-");
                for(String otro: otros){
                    if (!otro.equals(""))
                        if (otro.equals("Personalizado")){

                            ordenado.append("\t\t\t\t₡ ").append(precioComboCustom);
                        }
                        ordenado.append("\n").append(otro);
                }

            }
            if(!combo.equals(combos[0])) {
                ordenado.append("\n");
                ordenado.append("**************************");
                ordenado.append("\n");
            }
        }
        factura.setText(ordenado.toString());
        totalFa.setText("₡ " + precioTotal);
    }

}
