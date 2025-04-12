package oops;

    interface A1 {
        default void method() {
            System.out.println("Method in A");
        }
    }

    interface B1 {
        default void method() {
            System.out.println("Method in B");
        }
    }
    class Di implements A1, B1 {

        @Override
        public void method() {
            A1.super.method();
            //B1.super.method();
        }
    }
    public class Diamond{
        public static void main(String[] args) {
            Di di = new Di();
            di.method();
        }

    }

