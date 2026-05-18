package immutable;

import java.util.ArrayList;
import java.util.List;

public class Immutable {

    private final List<String> strings;
    private final String value;

    public Immutable(List<String> strings, String value) {
        this.strings = strings;
        this.value = value;
    }

    public List<String> getStrings() {
        return new ArrayList<>(strings);
    }

    public void printStrings() {
        System.out.println(strings);
    }
    public String getValue() {
        return value;
    }
}
