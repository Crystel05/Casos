package MODEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ComidaManager {

    public ComidaManager(){}


    public static HashMap<String, IPrototype> comidas = new HashMap<String, IPrototype>();;


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

    public static ArrayList<String>getTypeKeys(TipoComida tipo){
        ArrayList<String> keys = new ArrayList<String>();

        Set<String> llaves = comidas.keySet();

        for(String llave : llaves) {
            if(((Comida)comidas.get(llave)).getTipoComida() == tipo) {
                keys.add(llave);
                System.out.println(llave);
            }
        }
        return keys;
    }
}
