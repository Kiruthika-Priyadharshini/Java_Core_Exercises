import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentMain {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(3, "A", 85));
        students.add(new Student(1, "B", 92));
        students.add(new Student(2, "C", 78));

        System.out.println("Original list:");
        students.forEach(System.out::println);

        Collections.sort(students);
        System.out.println("Sorted by marks natural order:");
        students.forEach(System.out::println);

        Collections.sort(students, new NameComparator());
        System.out.println("Sorted by name:");
        students.forEach(System.out::println);

        Collections.sort(students, new IdComparator());
        System.out.println("Sorted by ID:");
        students.forEach(System.out::println);

    }
}

class Student implements Comparable<Student>{
    int id;
    String name;
    int marks;

    public Student(int id, String name, int marks) {
        this.id=id;
        this.marks=marks;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.marks,other.marks);
    }
}

class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name);
    }
}
class IdComparator implements Comparator<Student>{

    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.id, s2.id);
    }
}
