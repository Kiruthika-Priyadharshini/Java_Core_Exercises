class EmployeeManager implements Cloneable{

    private static EmployeeManager instance;

    private EmployeeManager() {
        System.out.println("EmployeeManager Initialized.");
    }

    public static EmployeeManager getInstance() {
        if (instance == null) {
            synchronized (EmployeeManager.class) {
                instance = new EmployeeManager();
            }
        }
        return instance;
    }

    public void manageEmployee(String name) {
        System.out.println("Managing employee: " + name);
       }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        //return super.clone();//singleton breaking
        throw new CloneNotSupportedException("Cloning not allowed for singleton");
    }

}

public class ManagerMain {
    public static void main(String[] args) {
        try{EmployeeManager manager1 = EmployeeManager.getInstance();
            manager1.manageEmployee("Kiruthika (onboarded)");

            EmployeeManager manager2 = (EmployeeManager) manager1.clone();

            //EmployeeManager manager2 = EmployeeManager.getInstance();
            manager2.manageEmployee("Kiruthika (workspace assigned)");

            System.out.println(manager2.hashCode());
            System.out.println(manager1.hashCode());

        } catch (CloneNotSupportedException e) {
            System.out.println("Exception caught"+e.getMessage());
        }

    }
}