package MODEL;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FactoryCombo implements IFactory<IPrototype> {

    public HashMap<String, IPrototype> combos;

    @Override
    public IPrototype getItem(String nombreCombo){
        return combos.get(nombreCombo);
    }

    @Override
    public void addItem(){

    }

    @Override
    public ArrayList<IPrototype>getAll(){
        ArrayList<IPrototype> listaCombos = new ArrayList<IPrototype>();

        Set<String> llaves = combos.keySet();

        for(String llave : llaves) {
            listaCombos.add(combos.get(llave));
        }
        return listaCombos;
    }

}
