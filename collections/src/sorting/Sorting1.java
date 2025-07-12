package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting1 {

    public static void main(String[] args) {
        Integer[] array = {1,22,12,90,23,78};

        //passing an comparator implementation for sorting.
        Arrays.sort(array,(a,b)->b-a);
        System.out.println(Arrays.toString(array));

        Car[] cars = new Car[4];
        cars[0] = new Car("honda","red");
        cars[1] = new Car("suv","blue");
        cars[2] = new Car("skoda","green");
        cars[3] = new Car("bmw","yellow");

        //This will cause an error, because there is neither an implementation of comparator to tell how to compare two car objects.
        //Neither Car class has implemented Comparable to tell how to compare objects of car
        //Nor it has provided any custom Comparator class implementation as an parameter to sort function.
        //This works fine with Integer class is because internally it implements Comparable compareTo method, which is used when no Comparator is passed.
        //Arrays.sort(cars);

        //Car implements Comparable<Car> will also give compareTo method to compare two car objects.
        //But this approach makes sorting limited to one kind only, as compareTo method will now be used internally for comparison.
        Arrays.sort(cars);

        //Passing Comparator interface(FC) as an argument is a more flexible approach as we can now give any type of comparison logic
        //We can sort on the basis of name, color weight etc.
        Arrays.sort(cars, (Car c1,Car c2) -> c2.getColor().compareTo( c1.getColor()));
        System.out.println(Arrays.toString(cars));
        Arrays.sort(cars, (c1,c2)->c1.getName().compareTo(c2.getName()));
        System.out.println(Arrays.toString(cars));


    }
}
