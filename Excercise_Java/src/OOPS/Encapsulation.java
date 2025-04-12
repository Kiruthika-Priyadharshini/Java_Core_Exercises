package oops;
class A {
    private int value;
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }

}
public class Encapsulation {
    public static void main(String[] args) {
        A a =new A();
        a.setValue(4);
        System.out.println(a.getValue());


    }
}
