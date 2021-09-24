package MODEL;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ComboManager {

    public ComboManager(){}

    public HashMap<String, IPrototype> combos;

    public IPrototype getItem(String nombreCombo){
        return combos.get(nombreCombo).clone();
    }

    public void addItem(String nombre, IPrototype combo){
        combos.put(nombre, combo);
    }

    public ArrayList<IPrototype>getAll(){
        ArrayList<IPrototype> listaCombos = new ArrayList<IPrototype>();

        Set<String> llaves = combos.keySet();

        for(String llave : llaves) {
            listaCombos.add(combos.get(llave).clone());
        }
        return listaCombos;
    }

    public ArrayList<String>getAllKeys(){
        ArrayList<String> keys = new ArrayList<String>();

        Set<String> llaves = combos.keySet();

        for(String llave : llaves) {
            keys.add(llave);
        }
        return keys;
    }

}
