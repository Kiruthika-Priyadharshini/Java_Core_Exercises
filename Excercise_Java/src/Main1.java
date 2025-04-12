import java.util.*;

class Restaurant {
    private final Set<String> ordersReady = new HashSet<>();

    public synchronized void placeOrder(String customer) throws InterruptedException {
        System.out.println(customer + " placed an order and is waiting.");
        while (!ordersReady.contains(customer)) {
            wait();
        }
        System.out.println(customer + " got their food and is eating.");
    }

    public synchronized void prepareOrder(String... customers) {
        ordersReady.addAll(Arrays.asList(customers));
        if (customers.length == 1) {
            System.out.println("Chef prepared food for " + customers[0] + ".");
            notify();  // Notify one thread
        } else {
            System.out.println("Chef prepared food for " + String.join(" and ", customers) + ".");
            notifyAll();  // Notify all threads
        }
    }
}

public class Main1 {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // Customers
        new Thread(() -> {
            try {
                restaurant.placeOrder("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                restaurant.placeOrder("B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                restaurant.placeOrder("C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        // Chef
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                restaurant.prepareOrder("A"); // notify one

                Thread.sleep(2000);
                restaurant.prepareOrder("B", "C"); // notify all
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}