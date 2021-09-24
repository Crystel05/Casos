package Vista;
import Controlador.FactoryPatrones;
import Modelo.Bolita;
import Modelo.Colores;
import Modelo.Patrones;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    private ArrayList<Circle> esferas = new ArrayList<>();
    private FactoryPatrones factoryPatrones = new FactoryPatrones();
    private List<Integer> direciones = Arrays.asList(0, 45, 90, 135, 180, 225, 270, 315);
    private ArrayList<Bolita> poolBolitas = new ArrayList<>();

    @FXML
    private ComboBox<Integer> direccionEsferas;

    @FXML
    private Slider velocidadS;

    @FXML
    private Pane contEsferas;

    @FXML
    private ComboBox<Patrones> patronEsferas;

    @FXML
    private Text tiempoPatron4;

    @FXML
    private ComboBox<Colores> colorEsferas;

    @FXML
    private Text tiempoPatron3;

    @FXML
    private Text tiempoPatron2;

    @FXML
    private Text tiempoPatron1;

    @FXML
    private Text patron1;

    @FXML
    private Text patron2;

    @FXML
    private Text patron3;

    @FXML
    private Text patron4;

    @FXML
    private Text ms1;

    @FXML
    private Text ms2;

    @FXML
    private Text ms3;

    @FXML
    private Text ms4;

    @FXML
    private TextField cantEsferas;

    @FXML

    private void iniciar(ActionEvent event) {
        if(!cantEsferas.getText().isEmpty() && !colorEsferas.getSelectionModel().isEmpty() && !patronEsferas.getSelectionModel().isEmpty()) {
            int cant = Integer.parseInt(cantEsferas.getText());
            crearEsferas(cant);
        }
    }

    @FXML
    private void reIniciar(ActionEvent event) {
        patron1.setVisible(false);
        patron2.setVisible(false);
        patron3.setVisible(false);
        patron4.setVisible(false);

        ms1.setVisible(false);
        ms2.setVisible(false);
        ms3.setVisible(false);
        ms4.setVisible(false);

        tiempoPatron1.setVisible(false);
        tiempoPatron2.setVisible(false);
        tiempoPatron3.setVisible(false);
        tiempoPatron4.setVisible(false);
        cantEsferas.clear();
        velocidadS.setValue(0);
        for (Circle esfera : esferas) {
            contEsferas.getChildren().remove(esfera);
        }
        esferas.clear();

        for (Bolita bolita : poolBolitas) {
            factoryPatrones.getPool().releaceObject(bolita);
        }
        
        poolBolitas.clear();
    }

    private void rebotar(Circle circle, int velocidad, int direccion){
        Timeline tiempo = new Timeline(new KeyFrame(Duration.millis(velocidad), new EventHandler<ActionEvent>() { // el tiempo dentro del milis es la velocidad
        double deltaX = 2;
        double deltaY = 2;

        @Override
        public void handle(ActionEvent actionEvent) {
            switch (direccion){
                case 0:
                case 180:
                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY());
                    break;
                case 45:
                case 225:
                    circle.setLayoutX(circle.getLayoutX() + deltaX);
                    circle.setLayoutY(circle.getLayoutY() + deltaY);
                    break;
                case 90:
                case 270:
                    circle.setLayoutX(circle.getLayoutX());
                    circle.setLayoutY(circle.getLayoutY() + deltaY);
                    break;
                case 135:
                case 315:
                    circle.setLayoutX(circle.getLayoutX() + deltaX*2);
                    circle.setLayoutY(circle.getLayoutY() + deltaY*2);
                    break;

            }

            Bounds bounds = contEsferas.getLayoutBounds();
            boolean rightBorder = circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius());
            boolean leftBorder = circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius());
            boolean bottomBorder = circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius());
            boolean topBorder = circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius());

            if (rightBorder || leftBorder) {
                deltaX *= -1;
            }
            if (bottomBorder || topBorder) {
                deltaY *= -1;
            }
        }
    }));
        tiempo.setCycleCount(Animation.INDEFINITE);
        tiempo.play();
    }

    private void crearEsferas(int cant){
        Patrones patron = patronEsferas.getSelectionModel().getSelectedItem();
        Colores color = colorEsferas.getSelectionModel().getSelectedItem();

        int direccion = 0;
        int velocidad = 0;
        boolean random;
        if (!direccionEsferas.getSelectionModel().isEmpty()){
            direccion  = direccionEsferas.getSelectionModel().getSelectedItem();
            velocidad  = (int) velocidadS.getValue();
            random = false;
        }else{
            random = true;
        }

        ArrayList<Bolita> bolitas = bolitas(factoryPatrones, patron, Integer.parseInt(cantEsferas.getText()), color, direccion, velocidad, random);
        crearEsferasAux(cant, bolitas);
    }

    private ArrayList<Bolita> bolitas(FactoryPatrones factoryPatrones, Patrones patron,  int cant, Colores color, int direccion, int velocidad, boolean random) {
        ArrayList<Bolita> bolitas = new ArrayList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i <= cant; i++) {
            if (random) {
                int direc = (int) (Math.random() * 7);
                velocidad = (int) (Math.random() * 10 + 1);
                direccion = direciones.get(direc);
            }
            Bolita bolita = factoryPatrones.crear(patron, color, direccion, velocidad);
            bolitas.add(bolita);
            if (patron.equals(Patrones.OBJECT_POOL)){
                poolBolitas.add(bolita);
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;

        if (!patron1.isVisible()) {
            mostrarTiempo(patron1, tiempoPatron1, ms1, (int) duration, patron);
        }
        else if (patron1.isVisible() && !patron2.isVisible()){
            mostrarTiempo(patron2, tiempoPatron2, ms2, (int) duration, patron);
        }
        else if(patron1.isVisible() && patron2.isVisible() && !patron3.isVisible()){
            mostrarTiempo(patron3, tiempoPatron3, ms3, (int) duration, patron);
        }
        else if(patron1.isVisible() && patron2.isVisible() && patron3.isVisible() && !patron4.isVisible()){
            mostrarTiempo(patron4, tiempoPatron4, ms4, (int) duration, patron);
        }

        return bolitas;

    }

    private void mostrarTiempo(Text patron, Text tiempoPatron, Text ms,int duration, Patrones patronEsc) {
        patron.setText(patronEsc.toString());
        tiempoPatron.setText(String.valueOf(duration));
        patron.setVisible(true);
        tiempoPatron.setVisible(true);
        ms.setVisible(true);
    }

    private void crearEsferasAux(int cant, ArrayList<Bolita> bolitas){
        for(int i = 0; i <= cant; i++){
            int posX = (int) (Math.random()*830);
            int posY = (int) (Math.random()*725);
            Circle nuevo = new Circle();
            nuevo.setRadius(7);
            nuevo.setLayoutX(posX);
            nuevo.setLayoutY(posY);
            nuevo.setFill(Color.valueOf(bolitas.get(i).getColor().toString()));
            contEsferas.getChildren().add(nuevo);
            esferas.add(nuevo);
            rebotar(nuevo, bolitas.get(i).getVelocidad(), bolitas.get(i).getDireccion());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        factoryPatrones.inicializar();

        ObservableList<Colores> colores = FXCollections.observableArrayList();
        Collections.addAll(colores, Colores.values());
        colorEsferas.setItems(colores);

        ObservableList<Patrones> patrones = FXCollections.observableArrayList();
        Collections.addAll(patrones, Patrones.values());
        patronEsferas.setItems(patrones);

        ObservableList<Integer> direccionesC = FXCollections.observableArrayList();
        direccionesC.addAll(direciones);
        direccionEsferas.setItems(direccionesC);

    }
}
