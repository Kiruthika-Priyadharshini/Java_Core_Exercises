import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelReaderParallelStream {

    public static void main(String[] args) throws Exception {
        ExcelReaderParallelStream reader = new ExcelReaderParallelStream();
        String filePath = "C:\\Users\\kirut\\Downloads\\excel_employee.csv";

        List<Customer> data = readCsvData(filePath);

        LocalDateTime startTime = LocalDateTime.now();

        List<Customer> processedData = processInParallel(data);

        processedData.forEach(System.out::println);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Processing Time (ms): " + java.time.Duration.between(startTime, endTime).toMillis());

        Optional<Customer> highestSalary = reader.getHighestSalary(processedData);
        highestSalary.ifPresent(c -> System.out.println("Highest Salary in Data: " + c));

        Map<String, Customer> highestSalaryByDept = reader.getHighestSalaryByDepartment(processedData);
        highestSalaryByDept.forEach((dept, cust) -> {
            String a = "Name: " + cust.firstName + " " + cust.lastName + " Salary: " + cust.getSalary();
            System.out.println("Department: " + dept + " " + a.replaceAll("\"", ""));
        });
    }

    public static List<Customer> readCsvData(String filePath) throws IOException {
        List<Customer> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line = reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] cus = line.split("\\s*,\\s*");

            if (cus.length > 5) {
                try {
                    int year = Integer.parseInt(cus[0].trim());
                    String department = cus[1].trim();
                    String title = cus[2].trim();
                    String firstName = cus[3].trim();
                    String lastName = cus[4].trim();
                    double salary = Double.parseDouble(cus[5].trim());

                    Customer customer = new Customer(year, department, title, firstName, lastName, salary);
                    data.add(customer);

                    System.out.println(customer);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        reader.close();
        return data;
    }

    public static List<Customer> processInParallel(List<Customer> data) {
        return data.parallelStream()
                .map(ExcelReaderParallelStream::processCustomer)
                .collect(Collectors.toList());
    }

    public static Customer processCustomer(Customer customer) {
        return customer;
    }

    public Optional<Customer> getHighestSalary(List<Customer> customers) {
        return customers.stream()
                .max(Comparator.comparingDouble(Customer::getSalary));
    }

    public Map<String, Customer> getHighestSalaryByDepartment(List<Customer> customers) {
        Map<String, Customer> result = new HashMap<>();

        for (Customer customer : customers) {
            String department = customer.getDepartment();
            if (!result.containsKey(department) || customer.getSalary() > result.get(department).getSalary()) {
                result.put(department, customer);
            }
        }
        return result;
    }
}
