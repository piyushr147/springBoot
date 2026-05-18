package diamondProblem;

public class Problem {
    interface A {
        default void show() {
            System.out.println("A");
        }
    }
    interface B extends A {
        default void show() {
            System.out.println("B");
        }
    }
    interface C extends A {
        default void show() {
            A.super.show();
            System.out.println("C");
        }
    }

    class D implements B,C{

        @Override
        public void show() {
            C.super.show();
        }
    }
}
