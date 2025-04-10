package JavaProblemsolving.File_Handling;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class CompletionExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        CompletableFuture<String> downloadTask = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
                list.add("File downloaded");
                System.out.println("File downloaded");
                return "FileContent";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        CompletableFuture<String> processTask = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
                System.out.println("Processing");
                list.add("File processing");
                return "Processed";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        CompletableFuture<String> combinedTask = processTask.thenCombine(downloadTask,String::concat);


        System.out.println("Main thread Continues");
        processTask.join();
        System.out.println(combinedTask.join());


    }

}
