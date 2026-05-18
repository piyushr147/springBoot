package interfaces;

public interface InterfaceD extends InterfaceA{

    //interfaceD parent class interfaceA has default print method which is now declared in interfaceD which makes the implementing class
    //of interfaceD to define this method, the default implementation of interfaceA will not be inherited.
    void print();
}
