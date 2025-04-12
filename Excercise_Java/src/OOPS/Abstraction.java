package oops;

abstract class Calculator1 {
    public int add(int a, int b) {
        return a + b;
    }
    public abstract int subtract(int a, int b);
}

interface Calculator {
    int multiply(int a, int b);
}
class SimpleCalculation extends Calculator1 implements Calculator {
    @Override
    public int subtract(int a, int b) {
        return a-b;
    }
    @Override
    public int multiply(int a, int b) {
        return a * b;
    }
}
public class Abstraction{
    public static void main(String[] args) {
        SimpleCalculation cal = new SimpleCalculation();
        System.out.println(cal.add(4, 5));
        System.out.println(cal.subtract(4, 5));
        System.out.println(cal.multiply(4, 5));
    }

}
