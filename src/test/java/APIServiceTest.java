import models.Admission;
import models.Allocation;
import models.Employee;
import models.Patient;
import network.MaternityAPIClient;
import network.MaternityService;
import okhttp3.Request;
import okio.Timeout;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import utils.DateUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class APIServiceTest {

    private final MaternityService maternityService;
    private final APIService apiService;

    public APIServiceTest() {

        // mock maternity api

        Mockery context = new Mockery();
        maternityService = context.mock(MaternityService.class);
        apiService = new APIServiceImpl(maternityService);


        Call call = context.mock(Call.class);

        List<Admission> admissions = Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00"), 2),
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
        );

        context.checking(new Expectations() {{
            oneOf(maternityService).getAdmissions();
            will(returnValue(call));
            oneOf(call);
            will(returnValue(Response.success(admissions)));
        }});


        List<Allocation> allocations = Arrays.asList(
                new Allocation(1, 1, 4, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00")),
                new Allocation(2, 3, 4, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-24T09:50:00"))
        );

        context.checking(new Expectations() {{
            oneOf(maternityService).getAllocations();
            will(returnValue(call));
            oneOf(call);
            will(returnValue(Response.success(allocations)));
        }});

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Finley", "Sarah"),
                new Employee(2, "Jackson", "Robert"),
                new Employee(3, "Allen", "Alice"),
                new Employee(4, "Jones", "Sarah"),
                new Employee(5, "Wicks", "Patrick"),
                new Employee(6, "Smith", "Alice")
        );

        context.checking(new Expectations() {{
            oneOf(maternityService).getEmployees();
            will(returnValue(call));
            oneOf(call);
            will(returnValue(Response.success(employees)));
        }});

        List<Patient> patients = Arrays.asList(
                new Patient(1, "Robinson", "Viv", "1113335555"),
                new Patient(2, "Carter", "Heather", "2224446666"),
                new Patient(3, "Barnes", "Nicky", "6663338888")
        );

        context.checking(new Expectations() {{
            oneOf(maternityService).getPatients();
            will(returnValue(call));
            oneOf(call);
            will(returnValue(Response.success(patients)));
        }});
    }

    @Test
    void getPatientsByEmployeeNegativeId() {

        int id = -10;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdNonExisting() {

        int id = 0;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdMaxInt() {
        int id = Integer.MAX_VALUE;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdMinInt() {
        int id = Integer.MIN_VALUE;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdTooLargeNeg() {
        int id = Integer.MIN_VALUE - 100;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdTooLargePos() {
        int id = Integer.MAX_VALUE + 100;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdValidButNoRecords() {
        int id = 1;
        List<Patient> expected = new ArrayList<>();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getPatientsByEmployeeIdValidWithRecords() {
        int id = 4;
        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertEquals(expected, actual);
    }

    @Test
    void getDayWithMostAdmissions() {
        fail();
    }

    @Test
    void getAvgPatientTimeByCorrectEmployeeId() {
        fail();
    }

    @Test
    void getAvgPatientTimeByCIncorrectEmployeeId() {
        fail();
    }

}