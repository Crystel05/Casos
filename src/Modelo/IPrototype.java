package Modelo;

public interface IPrototype<T> {
    public T clone();
    public T deepClone();
}
