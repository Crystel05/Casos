package Controlador;

import Modelo.Bolita;
import Modelo.Colores;

public class GestorBolitas {

    Bolita roja = new Bolita(Colores.RED);
    Bolita azul = new Bolita(Colores.BLUE);
    Bolita amarilla = new Bolita(Colores.YELLOW);
    Bolita verde = new Bolita(Colores.GREEN);

//    private Hashtable<String, IPrototype<Bolita>> hashBolitas = new Hashtable<>();
//    // ------------------------------
//    public GestorBolitas()
//    {
//
//    }
//    // ------------------------------
//    public void addEnemigo( String nombre, PrototypeEnemigo objEnemigo )
//    {
//        this.hashEnemigos.put( nombre, objEnemigo );
//    }
//    // ------------------------------
//    public PrototypeEnemigo getEnemigo( String nombre )
//    {
//        PrototypeEnemigo objPrototipo = (PrototypeEnemigo) hashEnemigos.get( nombre );
//        return objPrototipo;
//    }
//    // ------------------------------
//    public PrototypeEnemigo getClon( String nombre )
//    {
//        // Localizar el objeto deseado
//        // Devolver un clon
//        return getEnemigo(nombre).clonar();
//    }
}
