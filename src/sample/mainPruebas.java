package sample;

import Controlador.BolitaFactory;
import Controlador.GestorBolitas;
import Modelo.Bolita;
import Modelo.Colores;

import java.util.Arrays;
import java.util.List;

public class mainPruebas {

    public static void main(String[] args){
        System.out.println("PROTOTYPE");
        System.out.println("***************************************");

        //Prototype factory
        GestorBolitas gestorBolitas = new GestorBolitas();
        Bolita bolita = (Bolita) gestorBolitas.getBolita(Colores.RED);
        Bolita bolita1 = (Bolita) gestorBolitas.getClon(Colores.BLUE).clone();
        bolita1.setDireccion(0);
        bolita1.setVelocidad(5);
        System.out.println(bolita.getColor());
        System.out.println(bolita1.getColor());
        System.out.println(bolita1.getVelocidad());

        System.out.println("\nFACTORY");
        System.out.println("***************************************");
        //Factory
        BolitaFactory factory = new BolitaFactory();
        for(int i = 0; i<10; i++){
            Bolita bolita2 = factory.crear(Colores.BLUE);
            System.out.println(bolita2.getColor());
            System.out.println(bolita2.getDireccion());
            System.out.println(bolita2.getVelocidad());
            System.out.print("\n");
        }

        System.out.println("\nBUILDER");
        System.out.println("***************************************");
        for (int i = 0; i<10; i++){
            Bolita bolita3 = new Bolita.BolitaBuilder().setColor(Colores.BLUE).setDireccion(40).setVelocidad(5).build();
            System.out.println(bolita3.getColor());
            System.out.println(bolita3.getVelocidad());
            System.out.println(bolita3.getDireccion());
            System.out.print("\n");
        }

        for (int i = 0; i<1000000; i++){
            int velocidad = (int) (Math.random()*10+1);
            System.out.println(velocidad);
        }


    }
}
