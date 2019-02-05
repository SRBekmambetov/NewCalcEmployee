import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class Employee {

    private int id;
    private String firstName;
    private String secondName;
    private String middleName;
    private BigDecimal salary;

    public Employee createEmployee(String firstName, String secondName, String middleName, BigDecimal salary) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setSecondName(secondName);
        employee.setMiddleName(middleName);
        employee.setSalary(salary);
        return employee;
    }
}