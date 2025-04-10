package JavaProblemsolving.File_Handling;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogsReader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date and time in this format yyyy-MM-ddTHH:mm:ss");
        String target_log= scanner.nextLine();
        searchLogByTime(target_log);

    }

    public static void searchLogByTime(String target_log) {
        int count = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("logs_test.txt"))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                if (line.startsWith(target_log)) {
                    System.out.println(line);
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("No logs found in the specific date");
            }else {
                System.out.println("Number of logs matched of same time is:" + count);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file " + e.getMessage());
        }
    }

}

