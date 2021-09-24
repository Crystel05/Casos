package Vista;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    ArrayList<Circle> esferas;
    @FXML
    private Button iniciarCreacion;

    @FXML
    private ComboBox<String> direccionEsferas;

    @FXML
    private ComboBox<String> velocidadEsferas;

    @FXML
    private Pane contEsferas;

    @FXML
    private ComboBox<Patrones> patronEsferas;

    @FXML
    private Button iniciarNuevaCreacion;

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
    private TextField cantEsferas;

    @FXML
    private void iniciar(ActionEvent event){
        int cant = Integer.parseInt(cantEsferas.getText());
        if(!cantEsferas.getText().isEmpty()) {
            crearEsferas(cant);
            for(Circle esfera: esferas){
                rebotar(esfera);
            }
        }


    }

    private void rebotar(Circle circle){
        Timeline tiempo = new Timeline(new KeyFrame(Duration.millis(5), new EventHandler<ActionEvent>() { // el tiempo dentro del milis es la velocidad
        double deltaX = 2;
        double deltaY = 2;

        @Override
        public void handle(ActionEvent actionEvent) {
            //si es 0 le sumo al x y no al y y así sigue...
            circle.setLayoutX(circle.getLayoutX() + deltaX);
            circle.setLayoutY(circle.getLayoutY() + deltaY);
            Bounds bounds = contEsferas.getBoundsInLocal();
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
        esferas = new ArrayList<>();
        Patrones patron = patronEsferas.getSelectionModel().getSelectedItem();
        crearEsferasAux(cant);
//        switch (patron){
//            case BUILDER:
//                crearEsferasAux(cant);
//                break;
//            case FACTORY: break;
//            case PROTOTYPE: break;
//            case OBJECT_POOL: break;
//        }
    }

    private void crearEsferasAux(int cant){
        for(int i = 0; i <= cant; i++){
            int posX = (int) (Math.random()*120) * 7;
            int posY = (int) (Math.random()*105) * 7;
            Circle nuevo = new Circle();
            nuevo.setRadius(7);
            nuevo.setLayoutX(posX);
            nuevo.setLayoutY(posY);
            contEsferas.getChildren().add(nuevo);
            esferas.add(nuevo);

        }
        rebotarBolitas(esferas);
    }

    private void rebotarBolitas(ArrayList<Circle> esferas) {
        Colores color = colorEsferas.getSelectionModel().getSelectedItem();
        for(Circle esfera : esferas){
            esfera.setFill(Color.valueOf(String.valueOf(color)));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Colores> colores = FXCollections.observableArrayList();
        Collections.addAll(colores, Colores.values());
        colorEsferas.setItems(colores);

        ObservableList<Patrones> patrones = FXCollections.observableArrayList();
        Collections.addAll(patrones, Patrones.values());
        patronEsferas.setItems(patrones);

        ObservableList<Integer> direcciones = FXCollections.observableArrayList();

//        Collections.addAll(colores, new List<Integer>());
//        direccionEsferas.setItems(direcciones);
//
//        ObservableList<Integer> velocidades = FXCollections.observableArrayList();
//        Collections.addAll(colores, Colores.values());
//        velocidadEsferas.setItems(velocidades);
    }
}
