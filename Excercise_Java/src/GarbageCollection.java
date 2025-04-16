import java.util.ArrayList;
import java.util.List;

public class GarbageCollection {
    static List<byte[]> memory = new ArrayList<>();
    public static void main(String[] args) {
            int i=0;
            try{
                while (true){
                    byte[] a = new byte[1024*1024];
                    memory.add(a);
                    System.out.println("Allocated"+ (++i));
                }
            } catch (OutOfMemoryError e) {
                System.err.println("OutOfMemoryError");

            } finally {
                System.gc();
            }
        }
    }


