package interfaces;

public interface InterfaceB {

    default void print(){
        System.out.println("hello from interfaceB");
    }

    public int size();
}
