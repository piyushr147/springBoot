package comparingObjects;

//this class is to write a code where we can compare two objects if they are equal by their values
public class CompareObjects {

    public static class Course{
        String courseName;

        public Course(String courseName){
            this.courseName = courseName;
        }
    }

    public static class Student{
        String name;
        Course course;
        int age;

        public Student(int age,String name,Course course){
            this.age=age;
            this.name=name;
            this.course=course;
        }

        @Override
        public boolean equals(Object obj){
            if(obj instanceof Student student){
                if(student.age != this.age || !student.name.equals(this.name)){
                    return false;
                }
                if(!student.course.courseName.equals(this.course.courseName)){
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args){
        Student student1 = new Student(10, "piyush", new Course("cs"));
        Student student2 = new Student(10, "piyush", new Course("cs"));

        System.out.println(student1.equals(student2));
    }
}
