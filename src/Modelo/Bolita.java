package Modelo;

public class Bolita implements IPrototype<Bolita> {

    private Colores color;
    private int direccion;
    private int velocidad;

    public Bolita(Colores color, int direccion, int velocidad) {
        this.color = color;
        this.direccion = direccion;
        this.velocidad = velocidad;
    }



    public Bolita() {
    }

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }

    public float getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    @Override
    public Bolita clone() {
        Bolita clone = new Bolita(color, direccion, velocidad);
        return clone;
    }

    @Override
    public Bolita deepClone() {
        return clone();
    }

    public static class BolitaBuilder implements IBuilder<Bolita>{

        private Colores color;
        private int direccion;
        private int velocidad;

        public BolitaBuilder setColor(Colores color) {
            this.color = color;
            return this;
        }

        public BolitaBuilder setDireccion(int direccion) {
            this.direccion = direccion;
            return this;
        }

        public BolitaBuilder setVelocidad(int velocidad) {
            this.velocidad = velocidad;
            return this;
        }


        @Override
        public Bolita build() {
            return new Bolita(color, direccion, velocidad);
        }
    }
}
