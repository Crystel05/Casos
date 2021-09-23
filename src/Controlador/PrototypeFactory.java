package Controlador;

import Modelo.Bolita;
import Modelo.Colores;
import Modelo.IFactory;
import Modelo.IPrototype;
import java.util.HashMap;

public class PrototypeFactory implements IFactory {

    private static HashMap<String, IPrototype> prototipos = new HashMap<>();

    @Override
    public IPrototype crear(Colores color) { // equivalente al getPrototype
        return (IPrototype) prototipos.get(color.toString()).clone();
    }
    public static void addPrototype(String prototypeName, IPrototype prototype){
        prototipos.put(prototypeName, prototype);
    }
}
