package oops;

public class Customer {
    int year;
    String department;
    String title;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    String firstName;
    String lastName;
    double salary;



    public Customer(int year, String department, String title,String firstName, String lastName, double salary){
        this.year = year;
        this.department=department;
        this.title=title;
        this.firstName= firstName;
        this.lastName= lastName;
        this.salary=salary;
    }

    @Override
    public String toString() {
        return "oops.Customer{" +
                "year=" + year +
                ", department='" + department + '\'' +
                ", title='" + title + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
