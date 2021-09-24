package Controlador;

import Modelo.*;

import java.awt.*;

public class FactoryPatrones {
    private ObjectPool pool;
    private GestorBolitas gestorBolitas;

    public ObjectPool getPool() {
        return pool;
    }

    public Bolita crear(Patrones patron, Colores color, int direccion, int velocidad){
        Bolita bolitaEscogida = null;
        switch (patron){
            case FACTORY:
                bolitaEscogida = factory(color, direccion, velocidad);
                break;
            case OBJECT_POOL:
                bolitaEscogida = pool(color, direccion, velocidad);
                break;
            case PROTOTYPE:
                bolitaEscogida = prototype(color, direccion, velocidad);
                break;
            case BUILDER:
                bolitaEscogida = new Bolita.BolitaBuilder().setColor(color).setDireccion(direccion).setVelocidad(velocidad).build();
                break;
        }
        return bolitaEscogida;
    }

    private Bolita prototype(Colores color, int direccion, int velocidad) {
        Bolita bolitaPrototype = (Bolita) gestorBolitas.getClon(color).clone();
        bolitaPrototype.setDireccion(direccion);
        bolitaPrototype.setVelocidad(velocidad);
        return bolitaPrototype;
    }

    private Bolita factory(Colores color, int direccion, int velocidad){
        BolitaFactory bolitaFactory = new BolitaFactory();
        Bolita bolita = bolitaFactory.crear(color);
        bolita.setVelocidad(velocidad);
        bolita.setDireccion(direccion);
        return bolita;
    }

    private Bolita pool(Colores color, int direccion, int velocidad){
        try {
            Bolita bolita = pool.getObject();
            bolita.setColor(color);
            bolita.setDireccion(direccion);
            bolita.setVelocidad(velocidad);
            return bolita;
        }catch(PoolException e){
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return new Bolita();
        }
    }

    public void inicializar(){
        pool = new ObjectPool(500,1000,1000*100);
        gestorBolitas = new GestorBolitas();
    }

}
