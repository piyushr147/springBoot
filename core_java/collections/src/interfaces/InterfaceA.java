package interfaces;

public interface InterfaceA {

    default void print(){
        System.out.println("hello from interfaceA");
    }

    public int size();
}
