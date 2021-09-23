package MODEL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FactoryComida implements IFactory {

    private static FactoryComida factoryComida;

    private FactoryComida(){

    }

    public HashMap<String, IPrototype> comidas;

    public static FactoryComida getInstance(){
        if(factoryComida == null){
            return new FactoryComida();
        }
        return factoryComida;
    }

    @Override
    public IPrototype getItem(String nombreCombo) {
        return comidas.get(nombreCombo);
    }

    @Override
    public void addItem() {

    }

    @Override
    public ArrayList<IPrototype> getAll() {
        ArrayList<IPrototype> listaCombos = new ArrayList<IPrototype>();

        Set<String> llaves = comidas.keySet();

        for (String llave : llaves) {
            listaCombos.add(comidas.get(llave));
        }
        return listaCombos;


    }
}
