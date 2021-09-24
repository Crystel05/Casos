package MODEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ComidaManager {

    public ComidaManager(){}


    public static HashMap<String, IPrototype> comidas;


    public static IPrototype getItem(String nombreCombo) {
        return comidas.get(nombreCombo).clone();
    }

    public static void addItem(String nombre, IPrototype comida) {
        comidas.put(nombre, comida);
    }

    public static ArrayList<IPrototype> getAll() {
        ArrayList<IPrototype> listaCombos = new ArrayList<IPrototype>();

        Set<String> llaves = comidas.keySet();

        for (String llave : llaves) {
            listaCombos.add(comidas.get(llave).clone());
        }
        return listaCombos;

    }
}
