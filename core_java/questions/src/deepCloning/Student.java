package deepCloning;

public class Student implements Cloneable{

    static class College implements Cloneable{
        String name;
        String stream;

        College(String name, String stream){
            this.name = name;
            this.stream = stream;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    private String name;
    private int age;
    private College college;

    public Student(String name, int age, College college) {
        this.name = name;
        this.age = age;
        this.college = college;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        College college = new Student.College("MSIT","Btech");
        Student student = new Student("piyush",23,college);

        Student student2 =  (Student) student.clone();
        student2.college = (College) student.college.clone();

        System.out.println(student.hashCode() == student2.hashCode());
        System.out.println(student.college.hashCode() == student2.college.hashCode());
    }
}
