package Controlador;

import MODEL.*;

public class Inicializador {

    public Inicializador() {
    }

    public static void inicializar(){
        Comida comida = new Comida("papas", 700, "pap", TipoComida.Adicional);
        ComidaManager.addItem("papas",comida);
        comida = new Comida("ensalada", 700, "ens", TipoComida.Adicional);
        ComidaManager.addItem("ensalada",comida);
        comida = new Comida("pure", 700, "pur", TipoComida.Adicional);
        ComidaManager.addItem("pure",comida);
        comida = new Comida("tres leches", 700, "tres", TipoComida.Adicional);
        ComidaManager.addItem("tres leches",comida);
        comida = new Comida("patatas", 700, "pat", TipoComida.Adicional);
        ComidaManager.addItem("patatas",comida);
        comida = new Comida("maiz", 700, "maiz", TipoComida.Adicional);
        ComidaManager.addItem("maiz",comida);
        comida = new Comida("uvas", 500, "uv", TipoComida.Adicional);
        ComidaManager.addItem("uvas",comida);

        comida = new Comida("hamburguesa", 1500, "burg", TipoComida.Principal);
        ComidaManager.addItem("hamburguesa",comida);
        comida = new Comida("pollo", 1500, "poll", TipoComida.Principal);
        ComidaManager.addItem("pollo",comida);
        comida = new Comida("hot dog", 1500, "hdog", TipoComida.Principal);
        ComidaManager.addItem("hot dog",comida);
        comida = new Comida("pizza", 1500, "piz", TipoComida.Principal);
        ComidaManager.addItem("pizza",comida);
        comida = new Comida("sandwich", 1500, "san", TipoComida.Principal);
        ComidaManager.addItem("sandwich",comida);
        comida = new Comida("sandwich", 1500, "san", TipoComida.Principal);
        ComidaManager.addItem("sandwich",comida);
        comida = new Comida("wrap", 1500, "wrp", TipoComida.Principal);
        ComidaManager.addItem("wrap",comida);


        comida = new Comida("gaseosa", 500, "gas", TipoComida.Bebida);
        ComidaManager.addItem("gaseosa",comida);
        comida = new Comida("cafe", 500, "caf", TipoComida.Bebida);
        ComidaManager.addItem("cafe",comida);
        comida = new Comida("te", 500, "te", TipoComida.Bebida);
        ComidaManager.addItem("te",comida);
        comida = new Comida("natural", 500, "nat", TipoComida.Bebida);
        ComidaManager.addItem("natural",comida);
        comida = new Comida("frozen", 500, "frz", TipoComida.Bebida);
        ComidaManager.addItem("frozen",comida);
        comida = new Comida("batido", 500, "bat", TipoComida.Bebida);
        ComidaManager.addItem("batido",comida);


        Combo combo = new Combo.Builder().setNombre("Combo 1").setPlatoFuerte("hamburguesa").addAdicionales("papas").addBebidas("gaseosa").build();
        ComboManager.addItem("Combo 1", combo);
        combo = new Combo.Builder().setNombre("Combo 2").setPlatoFuerte("pollo").addBebidas("gaseosa").addAdicionales("papas").build();
        ComboManager.addItem("Combo 2", combo);
        combo = new Combo.Builder().setNombre("Combo 3").setPlatoFuerte("hot dog").addBebidas("gaseosa").addAdicionales("pure").build();
        ComboManager.addItem("Combo 3", combo);
        combo = new Combo.Builder().setNombre("Combo 4").setPlatoFuerte("pizza").addBebidas("gaseosa").build();
        ComboManager.addItem("Combo 4", combo);
        combo = new Combo.Builder().setNombre("Combo 5").setPlatoFuerte("pizza").addBebidas("gaseosa").addAdicionales("ensalada").addAdicionales("tres leches").build();
        ComboManager.addItem("Combo 5", combo);
        combo = new Combo.Builder().setNombre("Personalizado").build();
        ComboManager.addItem("Personalizado", combo);
    }
}
