package interfaces;

import innerClass.InnerClass;

public class Implement2 implements InterfaceD,InterfaceE{

    @Override
    public void print() {
        InterfaceE.super.print();
    }

    @Override
    public int size() {
        return 0;
    }
}
