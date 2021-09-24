package MODEL;

public class Comida implements IPrototype {

    private String nombre;
    private float precio;
    private String codigo;
    private TipoComida tipoComida;

    public Comida(String nombre, float precio, String codigo, TipoComida tipoComida) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = codigo;
        this.tipoComida = tipoComida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoComida getTipoComida() {
        return tipoComida;
    }

    public void setTipoComida(TipoComida tipoComida) {
        this.tipoComida = tipoComida;
    }

    public IPrototype clone(){
        Comida comida = new Comida(
                this.nombre,
                this.precio,
                this.codigo,
                this.tipoComida
        );
        return comida;
    }

    public IPrototype deepClone(){ return clone(); }
}
