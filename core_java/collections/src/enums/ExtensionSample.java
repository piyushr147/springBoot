package enums;

public interface ExtensionSample {
    int sample = 10;

    public void call();

    default int get(){
        return 1;
    }
}
