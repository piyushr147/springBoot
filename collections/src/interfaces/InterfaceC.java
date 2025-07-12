package interfaces;

public interface InterfaceC extends InterfaceA,InterfaceB{

    //Both the parent classes have same default method which makes it mandatory for this interface to provide either a default
    //implementation of make this method just a declaration.
    @Override
    default void print() {
        InterfaceA.super.print();
        InterfaceB.super.print();
    }

    //void print();
}
