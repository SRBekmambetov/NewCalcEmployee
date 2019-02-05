import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Department {

    private int id;
    private String name;
    private List<Employee> employeesList;

    public Department createNewDepartment(List<Department> departmentsList, Employee employee, String nameDepartment) {
        Department department = new Department();
        List<Employee> listEmployees = new ArrayList<>();
        employee.setId(listEmployees.size() +1);
        listEmployees.add(employee);
        department.setId(departmentsList.size() + 1);
        department.setName(nameDepartment);
        department.setEmployeesList(listEmployees);
        departmentsList.add(department);
        return department;
    }
}
