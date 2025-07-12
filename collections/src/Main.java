import enums.EnumSample;
import immutable.Immutable;
import innerClass.InnerClass;
import interfaces.Implement;
import interfaces.Implement2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchFieldException {
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
        
        InnerClass.StaticClass staticClass = new InnerClass.StaticClass(1);
        System.out.println(staticClass.hashCode());
        staticClass.print();
        InnerClass.StaticClass staticClass2 = new InnerClass.StaticClass(2);
        System.out.println(staticClass2.hashCode());
        staticClass2.print();
        staticClass.print();

        EnumSample enumSample = EnumSample.BLACK;
        System.out.println(enumSample.toString());

        List<String> list2 = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        String s = "hello";
        Immutable immutable = new Immutable(list2,s);
        List<String> list3 = immutable.getStrings();
        list3.add(1,"jjddjd");
        System.out.println(list3);
        immutable.printStrings();

        Implement implement = new Implement();
        implement.print();
        Implement2 implement2 = new Implement2();
        implement2.print();

        Class<Implement> implement3 = Implement.class;

        System.out.println(implement3.getName());
        System.out.println(implement3.getSuperclass());
        System.out.println(Modifier.toString(implement3.getModifiers()));

        Method[] methods = implement3.getMethods();
        for(Method method: methods){
            System.out.println(method.getName());
            System.out.println("method return type : " + method.getReturnType());
            System.out.println("method.getParameterTypes() : " + Arrays.toString(method.getParameterTypes()));
            System.out.println("method declare class: " + method.getDeclaringClass() );
        }

        Method[] methodsDeclared = implement3.getDeclaredMethods();
        for(Method method: methodsDeclared){
            System.out.println("declared methods: " + method.getName());
            System.out.println("method return type : " + method.getReturnType());
            System.out.println("method.getParameterTypes() : " + Arrays.toString(method.getParameterTypes()));
            System.out.println("method declare class: " + method.getDeclaringClass() );
        }
        //System.out.println(implement3.getMethods().getClass().getName());
        //System.out.println(implement3.getDeclaredMethods().getClass().getName());
        Class<?> implement4 = Implement.class;
        Object implementObject = implement4.newInstance();

        Method printMethod = implement4.getMethod("print");
        printMethod.invoke(implementObject);

        Field[] fields = implement3.getFields();
        for (Field field: fields) {
            System.out.println(field.getName());
            System.out.println("field return type : " + field.getType());
            System.out.println("field.getModifiers() : " + field.getModifiers());
        }

        Field[] fieldsDeclared = implement3.getDeclaredFields();
        for (Field field: fieldsDeclared) {
            System.out.println("declared fields: " + field.getName());
            System.out.println("field.getType() : " + field.getType());
            System.out.println("field.getModifiers() : " + field.getModifiers());
        }

        Class<?> implement5 = Implement.class;
        Implement implementObject2 = new Implement();

        Field fieldValueB = implement5.getDeclaredField("valueB");
        fieldValueB.setAccessible(true);
        fieldValueB.set(implementObject2,1);

        System.out.println(fieldValueB.get(implementObject2));
    }
}