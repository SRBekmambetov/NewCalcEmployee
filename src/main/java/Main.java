import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Department> departmentsList = new ArrayList<>();
    private static List<BigDecimal> averageSalarysList = new ArrayList<>();

    private static void reader() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название файла");
        String nameFile = sc.nextLine();
        File file = new File(nameFile);
        FileReader fileReader = null; // поток который подключается к текстовому файлу
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e1) {
            System.out.println("Файл не найден");
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader); // соединяем FileReader с BufferedReader
        String line;
        int j = 1;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] arr = line.split("\\|");
                String firstName = arr[0];
                String secondName = arr[1];
                String middleName = arr[2];
                String nameDepartment = arr[3];
                BigDecimal salary = new BigDecimal(arr[4]);
                Employee employee = new Employee();
                employee = employee.createEmployee(firstName, secondName, middleName, salary);
                if (j == 1) {
                    Department department = new Department();
                    department = department.createNewDepartment(departmentsList, employee, nameDepartment);
                } else {
                    addEmployee(departmentsList, employee, nameDepartment);
                }
                j++;
            }
            bufferedReader.close(); // закрываем поток
        } catch (IOException e) {
            System.out.println("Файл не доступен");
        }
    }

    private static void addEmployee(List<Department> departmentsList, Employee employee, String nameDepartment) {
        for (Department department: departmentsList) {
            String currNameDepartment = department.getName();
            if (currNameDepartment.equals(nameDepartment)) {
                List<Employee> employeesList = department.getEmployeesList();
                employee.setId(employeesList.size() + 1);
                employeesList.add(employee);
                return;
            }
        }
        Department department = new Department();
        department = department.createNewDepartment(departmentsList, employee, nameDepartment);
    }

    private static void calcAndPrintAverageSalaryDepartment() {
        for (Department department: departmentsList) {
            BigDecimal sumSalary = new BigDecimal(0);
            for (Employee employee: department.getEmployeesList()) {
                BigDecimal salary = employee.getSalary();
                sumSalary = sumSalary.add(salary);
            }
            BigDecimal averageSalary = sumSalary.divide(new BigDecimal(department.getEmployeesList().size()), 4, RoundingMode.HALF_UP);
            System.out.println(averageSalary);
            averageSalarysList.add(averageSalary);
        }
    }

    private static void compareSalary() {
        Department department1 = new Department();
        Department department2 = new Department();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите наименование 1-го отдела");
        String introducedDepartment1 = sc.nextLine();
        System.out.println("Введите наименование 2-го отдела");
        String introducedDepartment2 = sc.nextLine();
        for (Department department: departmentsList) {
            String currNameDepartment = department.getName();
            if (currNameDepartment.equals(introducedDepartment1)) {
                department1 = department;
            }
            if (currNameDepartment.equals(introducedDepartment2)) {
                department2 = department;
            }
        }
        for (int k = 0; k < department1.getEmployeesList().size(); k++) {
            BigDecimal sumSalary1 = new BigDecimal(0);
            BigDecimal sumSalary2 = new BigDecimal(0);
            BigDecimal tempSalary = new BigDecimal(0);
            Employee tempEmployee = new Employee();
            for (int i = 0; i < department1.getEmployeesList().size(); i++) {
                Employee employee1 = department1.getEmployeesList().get(i);
                BigDecimal salary1 = employee1.getSalary();
                if (k == i) {
                    tempSalary = salary1;
                    tempEmployee = employee1;
                    continue;
                }
                sumSalary1 = sumSalary1.add(salary1);
            }
            for (int i = 0; i < department2.getEmployeesList().size(); i++) {
                Employee employee2 = department2.getEmployeesList().get(i);
                BigDecimal salary2 = employee2.getSalary();
                sumSalary2 = sumSalary2.add(salary2);
            }
            sumSalary2 = sumSalary2.add(tempSalary);
            BigDecimal averageSalary1 = sumSalary1.divide(new BigDecimal(department1.getEmployeesList().size() - 1), 4, RoundingMode.HALF_UP);
            BigDecimal averageSalary2 = sumSalary2.divide(new BigDecimal(department2.getEmployeesList().size() + 1), 4, RoundingMode.HALF_UP);
            if (averageSalary1.compareTo(averageSalarysList.get(0)) == 1  && averageSalary2.compareTo(averageSalarysList.get(1)) == 1) {
//                System.out.println(averageSalary1);
//                System.out.println(averageSalary2);
                System.out.println(department1.getName() + " " + tempEmployee.getFirstName() + " -> " + department2.getName());
            }
        }
    }

    private static void printDepartmentsInfo() {
        for (Department department: departmentsList) {
            System.out.println(department.getId());
            System.out.println(department.getName());
            System.out.println(department.getEmployeesList());
        }
    }

    public static void main(String[] args) {
        reader();
        printDepartmentsInfo();
        calcAndPrintAverageSalaryDepartment();
        System.out.println();
        compareSalary();
    }
}