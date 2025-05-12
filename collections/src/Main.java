import innerClass.InnerClass;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static class Student{

        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(1);
        list.add(1);
        list.add(1);
        list.trimToSize();
        System.out.println(list.getClass().getName());

        List<String> list1 = Arrays.asList("a", "b", "c", "d");
        list1.set(0,"z");
        System.out.println(list1.getClass().getName());
        //list1.add("e");

        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        stringList.add("ss");

        List<String> strings = List.of("a","b","c");
        //strings.set(0,"z");

        List<String> strings2 = new ArrayList<>(Arrays.asList("apple","banana","orange","grapes"));
        strings2.remove("apple");
        System.out.println(strings2);

        List<Integer> integers = new ArrayList<>(List.of(1,5,3,17,3,16,1,4,7,9,12,21,45,4));
        integers.remove(1);//remove by index
        integers.remove(Integer.valueOf(3));//remove 1st occurrence by value
        integers.removeAll(Collections.singleton(Integer.valueOf(3)));//remove all occurrences by value
        System.out.println(integers);

        integers.sort((a,b) -> b - a);
        System.out.println(integers);

        List<Student> students = new ArrayList<>();
        students.add(new Student("piyush", 23));
        students.add(new Student("keshav", 24));
        students.add(new Student("sachin", 22));
        students.add(new Student("gauransh", 23));

        students.sort((a,b) -> {
            if(a.age < b.age)
                return -1;
            else if(a.age > b.age)
                return 1;
            else
                return a.name.compareTo(b.name);
        });

        Comparator<Student> comparator = Comparator.comparing(Student::getAge).reversed().thenComparing(Student::getName).reversed();
        students.sort(comparator);

        for(Student student: students){
            System.out.println(student.getName() + " " + student.getAge());
        }

        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.remove(Integer.valueOf(1));

        Stack<Integer> stack = new Stack<>();
        
        InnerClass.StaticClass staticClass = new InnerClass.StaticClass();
        System.out.println(staticClass.hashCode());
        InnerClass.StaticClass staticClass2 = new InnerClass.StaticClass();
        System.out.println(staticClass2.hashCode());

    }
}