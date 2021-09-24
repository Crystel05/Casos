package Controlador;


import MODEL.IPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ComboManager {

    public ComboManager(){}

    public static HashMap<String, IPrototype> combos = new HashMap<String, IPrototype>();

    public static IPrototype getItem(String nombreCombo){
        return combos.get(nombreCombo).clone();
    }

    public static void addItem(String nombre, IPrototype combo){
        combos.put(nombre, combo);
    }

    public static ArrayList<IPrototype>getAll(){
        ArrayList<IPrototype> listaCombos = new ArrayList<IPrototype>();

        Set<String> llaves = combos.keySet();

        for(String llave : llaves) {
            listaCombos.add(combos.get(llave).clone());
        }
        return listaCombos;
    }

    public static ArrayList<String>getAllKeys(){
        ArrayList<String> keys = new ArrayList<String>();

        Set<String> llaves = combos.keySet();

        for(String llave : llaves) {
            keys.add(llave);
        }
        return keys;
    }

}
