package MODEL;

import sun.security.mscapi.CPublicKey;

import java.util.ArrayList;

public class Combo implements IPrototype {

        private IPrototype platoFuerte;
        private ArrayList<IPrototype> adicionales;
        private ArrayList<IPrototype> bebidas;

    public Combo(IPrototype platoFuerte, ArrayList<IPrototype> adicionales, ArrayList<IPrototype> bebidas) {
        this.platoFuerte = platoFuerte;
        this.adicionales = adicionales;
        this.bebidas = bebidas;
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

    public void setAdicionales(ArrayList<IPrototype> adicionales) {
        this.adicionales = adicionales;
    }

    public ArrayList<IPrototype> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<IPrototype> bebidas) {
        this.bebidas = bebidas;
    }

    //implementar
    @Override
    public Combo clone(){
        Combo combo =  new Combo(
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
        Combo combo = new Combo(this.platoFuerte.clone(),adicionalesCopy,bebidasCopy);

        return combo;
    }

    public static class Builder implements IBuilder<Combo>{
        private IPrototype platoFuerte;
        private ArrayList<IPrototype> adicionales;
        private ArrayList<IPrototype> bebidas;

        public Builder setPlatoFuerte(String nombre){
            this.platoFuerte = ComidaManager.getItem(nombre).clone();
            return this;
        }

        public Builder addAdicionales(String nombre){
            this.adicionales.add( this.platoFuerte = ComidaManager.getItem(nombre));
            return this;
        }

        public Builder addBebidas(String nombre){
            this.bebidas.add( this.platoFuerte = ComidaManager.getItem(nombre));
            return this;
        }

        @Override
        public Combo build(){
            return new Combo(platoFuerte,adicionales,bebidas);
        }
    }
}
