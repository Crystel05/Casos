package Controlador;

import Modelo.Bolita;
import Modelo.Colores;


import java.util.Arrays;
import java.util.List;

public class BolitaFactory  {

    public Bolita crear(Colores color, int direccion, int velocidad) {
        Bolita bolita = null;
        switch (color){
            case RED: bolita = new Bolita(Colores.RED, direccion, velocidad); break;
            case BLUE: bolita = new Bolita(Colores.BLUE, direccion, velocidad); break;
            case GREEN: bolita = new Bolita(Colores.GREEN, direccion, velocidad); break;
            case YELLOW: bolita = new Bolita(Colores.YELLOW, direccion, velocidad); break;
        }
        return bolita;
    }
}

