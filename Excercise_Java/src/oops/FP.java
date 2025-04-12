package oops;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FP{
    public static void main(String[] args) throws IOException {

        Stream<String> lines = Files.lines(Paths.get("C:\\Users\\kirut\\Downloads\\Excercise_Java\\excel_employee.csv"));

        List<Customer> customers = lines.skip(1).parallel().map(line -> {
            String[] cus = line.split(",");
            return new Customer(Integer.parseInt(cus[0]), cus[1], cus[2], cus[3], cus[4], Double.parseDouble(cus[5]));
        }).toList();

        Predicate<Customer> departmentP = c -> c.getTitle().equalsIgnoreCase("ADMIN SUPPORT ASSISTANT");
        Predicate<Customer> salaryP= c -> c.getSalary() > 2000;

        List<Customer> filteredCustomers = customers.stream().filter(departmentP.and(salaryP)).toList();
        filteredCustomers.forEach(System.out::println);

        Function<Customer, String> OptionalName = c -> Optional.ofNullable(c.getFirstName()).orElse("not available");
        filteredCustomers.stream().map(OptionalName).forEach(System.out::println);
    }
}

