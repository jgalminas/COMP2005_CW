package data;

import models.Admission;
import models.Allocation;
import models.Employee;
import models.Patient;
import utils.DateUtil;
import java.util.Arrays;
import java.util.List;

public class Static {

    public static final List<Admission> ADMISSIONS = Arrays.asList(
            new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00"), 2),
            new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
            new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
    );

    public static final List<Allocation> ALLOCATIONS = Arrays.asList(
            new Allocation(1, 1, 4, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00")),
            new Allocation(2, 3, 4, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-24T09:50:00"))
    );

    public static final List<Employee> EMPLOYEES = Arrays.asList(
            new Employee(1, "Finley", "Sarah"),
            new Employee(2, "Jackson", "Robert"),
            new Employee(3, "Allen", "Alice"),
            new Employee(4, "Jones", "Sarah"),
            new Employee(5, "Wicks", "Patrick"),
            new Employee(6, "Smith", "Alice")
    );

    public static final List<Patient> PATIENTS = Arrays.asList(
            new Patient(1, "Robinson", "Viv", "1113335555"),
            new Patient(2, "Carter", "Heather", "2224446666"),
            new Patient(3, "Barnes", "Nicky", "6663338888")
    );

}