package Controlador;

import Modelo.Bolita;
import Modelo.Colores;
import Modelo.IPrototype;

import java.util.Hashtable;

public class GestorBolitas {

    private Hashtable<Colores, IPrototype> esferas = new Hashtable<>();

    public GestorBolitas(){
       IPrototype<Bolita> roja = new Bolita(Colores.RED);
       this.addBolitas(Colores.RED, roja);

        IPrototype<Bolita> verde = new Bolita(Colores.GREEN);
        this.addBolitas(Colores.GREEN, verde);

        IPrototype<Bolita> amarilla = new Bolita(Colores.YELLOW);
        this.addBolitas(Colores.YELLOW, amarilla);

        IPrototype<Bolita> azul = new Bolita(Colores.BLUE);
        this.addBolitas(Colores.BLUE, azul);
    }

    public void addBolitas(Colores color, IPrototype esfera ){
        this.esferas.put(color, esfera);
    }

    public IPrototype getBolita(Colores color){
        IPrototype objPrototipo = (IPrototype) esferas.get(color);
        return objPrototipo;
    }

    public IPrototype getClon(Colores color) {
        return (IPrototype) getBolita(color).clone();
    }
}
