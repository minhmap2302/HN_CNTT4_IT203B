package ra.entity;
import java.util.Scanner;

public class Employee {
    private String empId;
    private String empName;
    private int age;
    private double salary;

    public Employee(){};
    public Employee(String empId, String empName, int age, double salary){
        this.empId = empId;
        this.empName = empName;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public void inputData(Scanner scanner){
        System.out.println("Enter Employee ID (VD: NV001,NV002,NV003,NV004): ");
        this.empId = scanner.nextLine();
        System.out.println("Enter Employee Name: ");
        this.empName = scanner.nextLine();

        while (true){
            System.out.println("Enter Employee Age(age >= 18): ");
            this.age = Integer.parseInt(scanner.nextLine());
            if (this.age>=18) {
                break;
            }
            System.out.println("Invalid Age");
        }
        while (true){
            System.out.println("Enter Employee Salary(Salary > 0): ");
            this.salary = Double.parseDouble(scanner.nextLine());
            if (this.salary>0){
                break;
            }
            System.out.println("Invalid Salary");
        }
    }

    public void displayData(){
        System.out.printf("| %-10s | %-20s | %-5d | %-15.2f |\n", empId, empName, age, salary);
    }
}
