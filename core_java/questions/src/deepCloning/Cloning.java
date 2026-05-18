package deepCloning;

public class Cloning {

    public static class College implements Cloneable{
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

    public static class Student implements Cloneable{
        public String name;
        public int age;
        public College college;

        Student(String name, int age, College college) {
            this.name = name;
            this.age = age;
            this.college = college;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            Student cloned = (Student) super.clone();
            cloned.college = (College) this.college.clone(); //deep cloning
            return cloned;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", college=" + college.name +
                    '}';
        }
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        College college = new College("MSIT","Btech");
        Student student = new Student("piyush",23,college);

        Student student2 = (Student) student.clone();

        System.out.println(student.hashCode() == student2.hashCode());
        System.out.println(student.college.hashCode() == student2.college.hashCode());
        System.out.println(student.toString());
        System.out.println(student2.toString());
    }
}
