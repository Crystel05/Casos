package MODEL;

public class TesterMain {

    public static void main(String[] args){

//        ComboManager comboManager = new ComboManager();
//        ComidaManager comidaManager = new ComidaManager();

        Comida comida = new Comida("papas", 500, "pap", TipoComida.Adicional);
        ComidaManager.addItem("papas",comida);
        comida = new Comida("ensalada", 500, "ens", TipoComida.Adicional);
        ComidaManager.addItem("ensalada",comida);
        comida = new Comida("pure", 500, "pur", TipoComida.Adicional);
        ComidaManager.addItem("pure",comida);
        comida = new Comida("tres leches", 500, "tres", TipoComida.Adicional);
        ComidaManager.addItem("tres leches",comida);
        comida = new Comida("hamburguesa", 1500, "burg", TipoComida.Principal);
        ComidaManager.addItem("hamburguesa",comida);
        comida = new Comida("pollo", 1500, "poll", TipoComida.Principal);
        ComidaManager.addItem("pollo",comida);
        comida = new Comida("hot dog", 1500, "hdog", TipoComida.Principal);
        ComidaManager.addItem("hot dog",comida);
        comida = new Comida("pizza", 1500, "piz", TipoComida.Principal);
        ComidaManager.addItem("pizza",comida);
        comida = new Comida("gaseosa", 500, "gas", TipoComida.Bebida);
        ComidaManager.addItem("gaseosa",comida);

        Combo combo = new Combo.Builder().setPlatoFuerte("hamburguesa").addAdicionales("papas").addBebidas("gaseosa").build();
        ComboManager.addItem("Combo 1", combo);
        combo = new Combo.Builder().setPlatoFuerte("pollo").addBebidas("gaseosa").addAdicionales("papas").build();
        ComboManager.addItem("Combo 2", combo);
        combo = new Combo.Builder().setPlatoFuerte("hot dog").addBebidas("gaseosa").addAdicionales("pure").build();
        ComboManager.addItem("Combo 3", combo);
        combo = new Combo.Builder().setPlatoFuerte("pizza").addBebidas("gaseosa").build();
        ComboManager.addItem("Combo 4", combo);
        combo = new Combo.Builder().setPlatoFuerte("pizza").addBebidas("gaseosa").addAdicionales("ensalada").addAdicionales("tres leches").build();
        ComboManager.addItem("Combo 5", combo);

        ComboManager.getAllKeys();
        ComidaManager.getTypeKeys(TipoComida.Adicional);

    }
}
