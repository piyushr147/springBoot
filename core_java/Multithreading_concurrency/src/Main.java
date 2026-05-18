//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("start of main");
        Thread thread1 = new Thread1();
//        thread1.setDaemon(true);
        thread1.start();
        Thread thread2 = new Thread(new Thread2(),"Thread2");
        thread2.start();
        System.out.println("end of main");
    }
}