import java.util.List;

public class TypeErasure {

    //compile time error
    //@SuppressWarnings("unchecked")
    List<String>[] array = (List<String>[]) new List<?>[10];

    @SuppressWarnings("unchecked")
    Box<String>[] box = new Box[10];
//    box[0] = "ss";
//    String[] abcs = new String[10];
//    abcs[0] = "hello";
//    abcs[1] = 42;
}
