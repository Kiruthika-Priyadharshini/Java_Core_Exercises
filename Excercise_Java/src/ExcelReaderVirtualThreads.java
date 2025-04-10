import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ExcelReaderVirtualThreads {

    public static void main(String[] args) throws Exception {
        ExcelReaderVirtualThreads raeder = new ExcelReaderVirtualThreads();

        String filePath = "C:\\Users\\kirut\\Downloads\\excel_employee.csv";


        List<Customer> data = readCsvData(filePath);
        LocalDateTime startTime = LocalDateTime.now();

        List<Customer> processedData = processInParallelWithVirtualThreads(data);

        processedData.forEach(System.out::println);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println(java.time.Duration.between(startTime, endTime).toMillis());

        Optional<Customer> highestSalary = raeder.getHighestSalary(processedData);
        highestSalary.ifPresent(c -> System.out.println("Highest Salary in Data" + c));

//        Optional<Customer> highestInJudiciary = getHighestSalaryInDepartment(processedData, "JUDICIARY");
//        highestInJudiciary.ifPresent(c -> System.out.println("Highest Salary in JUDICIARY Department" + c));

        Map<String, Customer> highestSalaryByDept = raeder.getHighestSalaryByDepartment(processedData);
        highestSalaryByDept.forEach((dept, cust) -> {
                    String a = "Name:".concat(cust.firstName).concat(cust.lastName).concat(" Salary:").concat(Double.toString(cust.getSalary()));
                    System.out.println("Department:" + dept +" "+a.replaceAll("\"",""));
                }
        );
    }

    public static List<Customer> readCsvData(String filePath) throws IOException {
        List<Customer> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line= reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] cus = line.split("\\s*,\\s*");;

            if (cus.length > 5) {
                try {
                    int year = Integer.parseInt(cus[0].trim());
                    String department = cus[1].trim();
                    String title = cus[2].trim();
                    String firstName = cus[3].trim();
                    String lastName = cus[4].trim();
                    double salary = Double.parseDouble(cus[5].trim());

                    Customer customer = new Customer(year, department, title, firstName,lastName, salary);
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

    public static List<Customer> processInParallelWithVirtualThreads(List<Customer> data)
            throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();

        int chunkSize = (int) Math.ceil(data.size() / (double) 50);
        List<Callable<List<Customer>>> tasks = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            final int start = i * chunkSize;
            final int end = Math.min((i + 1) * chunkSize, data.size());
            tasks.add(() -> processChunk(data.subList(start, end)));
        }

        List<Future<List<Customer>>> results = executorService.invokeAll(tasks);
        List<Customer> processedData = new ArrayList<>();

        for (Future<List<Customer>> result : results) {
            processedData.addAll(result.get());
        }

        executorService.shutdown();
        return processedData;
    }

    public static List<Customer> processChunk(List<Customer> chunk) {
        List<Customer> processedChunk = new ArrayList<>();
        for(Customer customer: chunk){
            processedChunk.add(customer);

        }
        processedChunk.forEach(System.out::println);
        return processedChunk;
    }
    public  Optional<Customer> getHighestSalary(List<Customer> customers) {
        return customers.stream()
                .max(Comparator.comparingDouble(Customer::getSalary));
    }

//    public static Optional<Customer> getHighestSalaryInDepartment(List<Customer> customers, String department) {
//        return customers.stream()
//                .filter(c -> c.getDepartment().equalsIgnoreCase(department))
//                .max(Comparator.comparingDouble(Customer::getSalary));
//    }

    public  Map<String, Customer> getHighestSalaryByDepartment(List<Customer> customers) {
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