class A{
    int age;
    String name;
    public A(int age,String name) {
        this.age= age;
        this.name= name;
    }
    public void display(){
        System.out.println("I am in class A"+age+name);
    }
    public void screen(){
        System.out.println("I am in class A"+age+name);
    }
}
class B extends A{
    String education;
    String hobbies;
    public B(int age,String name,String education, String hobbies){
        super(age, name);
        this.education=education;
        this.hobbies=hobbies;
    }
    public void display(){
        System.out.println("I am in class B"+age+name+hobbies+education);
    }

}


public class Inheritance {

    public static void main(String[] args) {
        A a = new A(27,"priya");
        a.display();
        B b = new B(25,"kiru","Masters","playing");
        b.display();
        b.screen();

    }
}
