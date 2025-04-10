package JavaProblemsolving.File_Handling;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable1 task = new Callable1();
        Future<Integer> future = executorService.submit(task);

        try {
            Integer result = future.get();
            System.out.println("Result from Callable1: " + result);
        } catch (InterruptedException | ExecutionException e) {
        } finally {
            executorService.shutdown();
        }
    }
}
