package clone;

public class CloneableExample {
    public static void main(String[] args) throws CloneNotSupportedException {
        Student s1 = new Student(21, "piyuish");
        s1.setAddress(new Address("faridabad"));
        Student s2 = (Student) s1.clone();
        System.out.println(s1.hashCode() == s2.hashCode());
        System.out.println(s1.equals(s2));
        System.out.println(s1.getAddress().hashCode() == s2.getAddress().hashCode());
    }
}
