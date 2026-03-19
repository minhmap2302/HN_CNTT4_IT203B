package ra.business;

import ra.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeBusiness {
    private static EmployeeBusiness instance;
    private final List<Employee> employeeList = new ArrayList<>();
    private Scanner sc;

    private EmployeeBusiness() {}

    public static EmployeeBusiness getInstance() {
        if (instance == null) {
            instance = new EmployeeBusiness();
            return instance;
        }
        return null;
    }

    public List<Employee> getAll() {
        return employeeList;
    }

    public void addEmployee(Employee employee) {
        boolean exists = employeeList.stream().anyMatch(employee1 ->  employee1.equals(employee));
        if (exists){
            System.out.println("Employee Already Exists");
        } else {
            employeeList.add(employee);
            System.out.println("Employee added successfully");
        }
    }

    public boolean updateEmployee(String id , Scanner employee) {
        Optional<Employee> employee1 = employeeList.stream().filter(e -> e.getEmpId().equals(id)).findFirst();
        if (employee1.isPresent()){
            employee1.get().inputData(sc);
            return true;
        }
        return  false;
    }

    public boolean deleteEmployee(String id) {
        return employeeList.removeIf(employee -> employee.getEmpId().equals(id));
    }


    public List<Employee> searchByName(String name){
        return employeeList.stream().filter(employee -> employee.getEmpName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Employee> sortBySalaryHigh(){
        return employeeList.stream().filter(employee -> employee.getSalary() >= 15000000).collect(Collectors.toList());
    }

    public void sortBySalaryDesc(){
        employeeList.sort(Comparator.comparing(Employee::getSalary).reversed());
    }

}
