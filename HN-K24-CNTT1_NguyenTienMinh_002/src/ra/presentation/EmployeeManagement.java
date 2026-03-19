package ra.presentation;

import ra.business.EmployeeBusiness;
import ra.entity.Employee;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeBusiness business = EmployeeBusiness.getInstance();

        while (true) {
            System.out.println("----------EMPLOYEE MANAGEMENT----------");
            System.out.println("1. View Employee Details");
            System.out.println("2. Add Employee");
            System.out.println("3. Update Employee by id");
            System.out.println("4. Delete Employee by id");
            System.out.println("5. Search Employee by name");
            System.out.println("6. Sort Employee of the month");
            System.out.println("7. Sort by Salary");
            System.out.println("8. Exits Sign !!");
            System.out.println("Enter your choice: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        displayTable(business.getAll());
                        break;
                    case 2:
                        Employee newEmployee = new Employee();
                        newEmployee.inputData(sc);
                        business.addEmployee(newEmployee);
                        break;
                    case 3:
                        System.out.println("Enter employee id: ");
                        if (business.updateEmployee(sc.nextLine(), sc)) {
                            System.out.println("Employee updated successfully!");
                        } else {
                            System.out.println("Employee not updated successfully!");
                        }
                        break;
                    case 4:
                        System.out.println("Enter employee id: ");
                        if (business.deleteEmployee(sc.nextLine())) {
                            System.out.println("Employee deleted successfully!");
                        } else {
                            System.out.println("Employee not deleted successfully!");
                        }
                        break;
                    case 5:
                        System.out.println("Enter employee name: ");
                        displayTable(business.searchByName(sc.nextLine()));
                        break;
                    case 6:
                        displayTable(business.sortBySalaryHigh());
                        break;
                    case 7:
                        business.sortBySalaryDesc();
                        System.out.println("Sorted successfully!");
                        displayTable(business.getAll());
                        break;
                    case 8:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice");
            }
        }
    }

    private static void displayTable(List<Employee> list) {
        if (list.isEmpty()) {
            System.out.println("No data!");
            return;
        }

        System.out.println("+------------+----------------------+-------+-----------------+");
        System.out.printf("| %-10s | %-20s | %-5s | %-15s |\n", "ID", "Name", "Age", "Salary");
        System.out.println("+------------+----------------------+-------+-----------------+");

        list.forEach(Employee::displayData);

        System.out.println("+------------+----------------------+-------+-----------------+");
    }
}
