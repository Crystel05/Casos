package MODEL;

import Controlador.ComidaManager;

import java.util.ArrayList;

public class Combo implements IPrototype {

        private String nombre;
        private IPrototype platoFuerte;
        private ArrayList<IPrototype> adicionales;
        private ArrayList<IPrototype> bebidas;

    public Combo(String nombre, IPrototype platoFuerte, ArrayList<IPrototype> adicionales, ArrayList<IPrototype> bebidas) {
        this.nombre = nombre;
        this.platoFuerte = platoFuerte;
        this.adicionales = adicionales;
        this.bebidas = bebidas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public IPrototype getPlatoFuerte() {
        return platoFuerte;
    }

    public void setPlatoFuerte(Comida platoFuerte) {
        this.platoFuerte = platoFuerte;
    }

    public ArrayList<IPrototype> getAdicionales() {
        return adicionales;
    }

    public String getFormatedAdicionales() {
        StringBuilder add = new StringBuilder();
        for(IPrototype comida : adicionales){
            String precio = String.valueOf(((Comida) comida).getPrecio());
            add.append(((Comida) comida).getNombre()).append("\t\t\t\t₡ ").append(precio).append("-");
        }
        return add.toString();
    }

    public String getFormatedBebida() {
        StringBuilder add = new StringBuilder();
        for(IPrototype comida : bebidas){
            String precio = String.valueOf(((Comida) comida).getPrecio());
            add.append(((Comida) comida).getNombre()).append("\t\t\t\t₡ ").append(precio).append("-");
        }
        return add.toString();
    }

    public void addAdicional(IPrototype adicional) {
        this.adicionales.add(adicional);
    }

    public ArrayList<IPrototype> getBebidas() {
        return bebidas;
    }

    public void addBebida(IPrototype bebida) {
        this.bebidas.add(bebida);
    }

    public float getPrice(){
        float acumulado = ((Comida)this.platoFuerte).getPrecio() ;
        for(IPrototype adicional : this.adicionales){
            acumulado+=((Comida)adicional).getPrecio();
        }
        for(IPrototype bebida : this.bebidas){
            acumulado+=((Comida)bebida).getPrecio();
        }
        return acumulado;
    }

    //implementar
    @Override
    public Combo clone(){
        Combo combo =  new Combo(
                this.nombre,
                this.platoFuerte,
                this.adicionales,
                this.bebidas
        );
        return combo;
    }

    @Override
    public Combo deepClone(){
        ArrayList<IPrototype> adicionalesCopy = new ArrayList<IPrototype>();
        ArrayList<IPrototype> bebidasCopy = new ArrayList<IPrototype>();
        for(IPrototype comida : this.adicionales){
            adicionalesCopy.add(comida.clone());
        }
        for(IPrototype bebida : this.bebidas){
            bebidasCopy.add(bebida.clone());
        }
        Combo combo = new Combo(this.nombre, this.platoFuerte.clone(),adicionalesCopy,bebidasCopy);

        return combo;
    }

    public static class Builder implements IBuilder<Combo>{
        private String nombre;
        private IPrototype platoFuerte;
        private ArrayList<IPrototype> adicionales = new ArrayList<>();
        private ArrayList<IPrototype> bebidas = new ArrayList<>();

        public Builder setNombre(String nombre){
            this.nombre = nombre;
            return this;
        }

        public Builder setPlatoFuerte(String nombre){
            this.platoFuerte = ComidaManager.getItem(nombre);
            return this;
        }

        public Builder addAdicionales(String nombre){
            IPrototype item = ComidaManager.getItem(nombre);
            this.adicionales.add(item);
            return this;
        }

        public Builder addBebidas(String nombre){
            this.bebidas.add(ComidaManager.getItem(nombre));
            return this;
        }

        @Override
        public Combo build(){
            return new Combo(nombre,platoFuerte,adicionales,bebidas);
        }
    }
}
