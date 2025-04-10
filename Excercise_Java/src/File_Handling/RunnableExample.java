package JavaProblemsolving.File_Handling;


import java.util.concurrent.Callable;

import java.util.concurrent.*;

public class RunnableExample {
    public static void main(String[] args) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable Task is running on thread: " + Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}

class Callable1 implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println("Computing result...");
        Thread.sleep(2000);
        return 42;
    }
}


