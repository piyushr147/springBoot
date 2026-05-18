package interfaces;

public interface InterfaceE extends InterfaceB{

    default void print(){
        InterfaceB.super.print();
        System.out.println("hello from interfaceE");
    }

}
