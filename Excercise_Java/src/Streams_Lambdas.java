package JavaProblemsolving.File_Handling;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;



class Employee {
    String name;
    int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", age=" + age + "]";
    }

}
interface Welcome {
    void hello(String name);
}
public class Streams_Lambdas {


    public static int addLambda(int a, int b) {
        BiFunction<Integer, Integer, Integer> sum = (i, j) -> i + j;
        return sum.apply(a, b);
    }


    public static int fpSum(List<Integer> numbers) {
        int sum = numbers.stream().reduce(0, (num1, num2) -> num1 + num2);
        return sum;
    }

    public static void functionalInterfaceExample() {
        Welcome welcome = name -> System.out.println("Hello, " + name + "!");
        welcome.hello("Kiruthika");
    }
//list to stream and stream too list
    public static void listToStreamPrint() {
        List<String> names = Arrays.asList("Kiruthika", "Priyadharshini", "Elamurugan");
        names.stream().forEach(System.out::println);
    }

    public static void listToEvenNumbers() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 6);
        num.stream().filter(n -> n % 2 == 0).forEach(System.out::println);
    }
//map
    public static void ConvertToUpperCase() {
        List<String> names = Arrays.asList("kiru", "priya", "dharshini");
        List<String> upperNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(upperNames);
    }
//reduce
    public static void SumUsingReduce() {
        List<Integer> num = Arrays.asList(1, 2, 3, 4, 5, 6);
        int sum = num.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }
//forEach
    public static void forEachPrint() {
        List<String> c = Arrays.asList("A", "B", "C");
        c.stream().forEach(System.out::println);
    }
//sort
    public static void sortCustomObjects() {
        List<Employee> p = Arrays.asList(
                new Employee("Kiru", 26),
                new Employee("Priya", 22),
                new Employee("Elamurugan", 30));

        p.sort((p1, p2) -> Integer.compare(p1.age, p2.age));
        p.forEach(System.out::println);
        System.out.println("sorted ");
    }
// collect
    public static void collectintoList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(squares);
    }
//filter
    public static void filterMapList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> squares = numbers.stream().filter(num -> num % 2 == 0).map(n -> n * n)
                .toList();
        System.out.println(squares+"squares");
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(4, 6, 8, 13, 3, 15);
        System.out.println(fpSum(numbers));
        functionalInterfaceExample();
        listToStreamPrint();
        listToEvenNumbers();
        ConvertToUpperCase();
        SumUsingReduce();
        sortCustomObjects();
        collectintoList();
        filterMapList();

        Employee emp = new Employee("Kiruthika", 27);

        Consumer<Employee> printEmployee = e -> System.out.println("Added " + e);
        printEmployee.accept(emp);

        Supplier<Employee> employeeSupplier = () -> new Employee("Priya", 25);
        Employee newEmp = employeeSupplier.get();
        System.out.println("Added" + newEmp);

        Predicate<Employee> rightToVote = e -> e.age > 18;
        System.out.println("Can Vote!" + rightToVote.test(emp));

        Function<Employee, String> empNameUpper = e -> e.name.toUpperCase();
        System.out.println("name uppercase" + empNameUpper.apply(emp));

        BiFunction<String, Integer, Employee> createEmployee = (name, age) -> new Employee(name, age);
        //BiFunction<String, Integer, Employee> createEmployee = Employee::new;
        Employee empFromBiFunc = createEmployee.apply("Dharshini", 35);
        System.out.println("BiFunction " + empFromBiFunc);

    }

}

