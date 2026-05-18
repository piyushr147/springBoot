package bounds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpperLowerBound {

    public static void main(String[] args) {
        //upper bound
        Object o = new Object();
        List<? extends Number> list = new ArrayList<>(Arrays.asList(1.33, 2, 3, 4, 5));
        Number number = list.get(1);
        Object obj = list.get(0);
        //this throws error because list can be of any type and we can't add our specific type.
        //list.add(1);
        System.out.println(number);
        //What we can do is explicitly cast it in integer, this might cause exception
        //Integer x = (Integer) list1.get(0);
        List<Float> floatList = new ArrayList<>(Arrays.asList(1.3f, 2.3f, 3.3f, 4.3f, 5.3f));
        List<Long> longList = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L));
        upperBound(floatList);
        upperBound(longList);

        //lower bound
        List<? super Number> list1 = new ArrayList<>(Arrays.asList(1.888, 2, 3, 4, 5, o));
        //Here we can add because int, float, double because the list is a super type of Number
        list1.add(1.4);
        Number num = list.get(1);
        Object obj2 = list1.get(1);
        //But we cannot read values because supertype might contain Long, Fload at position 0, but we are taking it in Integer
        //Integer x = list1.get(0);
        //What we can do is explicitly cast it in integer, this might cause exception
        //Integer x = (Integer) list1.get(0);
        //System.out.println(x);
        List<? super Integer> list2 = new ArrayList<>(Arrays.asList(1.888f, 2, 3, 4, 5, o));
        lowerBound(list2);
    }

    public static void upperBound(List<? extends Number> list) {
        Number number = list.get(0);
        //If by means we're sure it's Integer we can typecast, but may cause errors
        //Integer integer = (Integer) list.get(0);
        System.out.println(number);
        //We cannot write as we don't know the specific type
        //list.add(1L);
    }

    public static void lowerBound(List<? super Integer> list) {
        list.add(1);
        //We cannot read here because we don't know the type of the element, can be Number or Object.
        //Integer x = list.get(0);
    }
}
