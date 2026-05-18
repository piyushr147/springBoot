package exception;

import java.security.KeyPair;

public class Exception1 {

    public static void method1(){
        try {
            System.out.println("method1");
            throw new ArithmeticException("maths exception");
            //throw new ClassCastException("class cast exception");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally {
            System.out.println("finally");
        }
    }

    public static void method2() throws ClassNotFoundException{
        System.out.println("method2");
        throw new ClassNotFoundException("class not found");
    }

    public static void method3() throws Exception {
        System.out.println("method3");
        method1();
        method2();
    }
    public static void main(String[] args) {
        //method1();
        method1();
        try{
            method1();
            method2();
        }catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
        finally {
            //method3();
        }
    }
}
