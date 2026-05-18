package streams;

public class Person implements Comparable<Person>{
    int id;
    int age;
    int height;

    public Person(int id,int age, int height) {
        this.id = id;
        this.age = age;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "Age: " + age + " " + "Height: " + height;
    }

    @Override
    public int compareTo(Person p) {
        return this.id - p.id;
    }
}
