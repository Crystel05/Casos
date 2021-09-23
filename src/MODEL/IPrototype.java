package MODEL;

public interface IPrototype {
    IPrototype deepClone();
    IPrototype clone();
}
