package Controlador;

import Modelo.Bolita;
import Modelo.Colores;
import Modelo.IFactory;
import Modelo.IPrototype;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BolitaFactory implements IFactory<Bolita> {

    @Override
    public Bolita crear(Colores color) {
        List<Integer> direcciones = Arrays.asList(0, 45, 90, 135, 180, 225, 270, 315);
        int dir = (int) (Math.random()*8);
        int direccion = direcciones.get(dir);
        int velocidad = (int) (Math.random()*10);
        return new Bolita(color, direccion, velocidad);
    }


}

