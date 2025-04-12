import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ExcelReaderParallelStream {

    public static void main(String[] args) throws Exception {
        ExcelReaderParallelStream reader = new ExcelReaderParallelStream();
        String filePath = "C:\\Users\\kirut\\Downloads\\excel_employee.csv";

        List<oops.Customer> data = readCsvData(filePath);

        LocalDateTime startTime = LocalDateTime.now();

        List<oops.Customer> processedData = processInParallel(data);

        processedData.forEach(System.out::println);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("Processing Time ms " + java.time.Duration.between(startTime, endTime).toMillis());

        Optional<oops.Customer> highestSalary = reader.getHighestSalary(processedData);
        highestSalary.ifPresent(c -> System.out.println("Highest Salary in Data: " + c));

        Map<String, oops.Customer> highestSalaryByDept = reader.getHighestSalaryByDepartment(processedData);
        highestSalaryByDept.forEach((dept, cust) -> {
            String a = "Name:".concat(cust.getFirstName()).concat(cust.getLastName()).concat(" Salary:").concat(Double.toString(cust.getSalary()));
            System.out.println("Department: " + dept + " " + a.replaceAll("\"", ""));
        });
    }

    public static List<oops.Customer> readCsvData(String filePath) throws IOException {
        List<oops.Customer> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        return reader.lines()
                .skip(1)
                .parallel()
                .map(line -> line.split("\\s*,\\s*"))
                .filter(cus -> cus.length > 5)
                .map(cus -> {
                    try {
                        int year = Integer.parseInt(cus[0].trim());
                        String department = cus[1].trim();
                        String title = cus[2].trim();
                        String firstName = cus[3].trim();
                        String lastName = cus[4].trim();
                        double salary = Double.parseDouble(cus[5].trim());

                        oops.Customer customer = new oops.Customer(year, department, title, firstName, lastName, salary);
                        System.out.println(customer);
                        return customer;
                    } catch (Exception e) {
                        throw new RuntimeException("Error processing line: " + Arrays.toString(cus), e);
                    }
                })
                .collect(Collectors.toList());
    }
////        String line = reader.readLine();
////        while ((line = reader.readLine()) != null) {
////
////            String[] cus = line.split("\\s*,\\s*");
////
////
////            }
//
//
////        reader.close();
        //return data;


    public static List<oops.Customer> processInParallel(List<oops.Customer> data) {
        return data.parallelStream()
                .map(ExcelReaderParallelStream::processCustomer)
                .collect(Collectors.toList());
    }

    public static oops.Customer processCustomer(oops.Customer customer) {
        return customer;
    }

    public Optional<oops.Customer> getHighestSalary(List<oops.Customer> customers) {
        return customers.parallelStream()
                .max(Comparator.comparingDouble(oops.Customer::getSalary));
    }

    public Map<String, oops.Customer> getHighestSalaryByDepartment(List<oops.Customer> customers) {
        Map<String, oops.Customer> result = new HashMap<>();

        for (oops.Customer customer : customers) {
            String department = customer.getDepartment();
            if (!result.containsKey(department) || customer.getSalary() > result.get(department).getSalary()) {
                result.put(department, customer);
            }
        }
        return result;
    }
}
