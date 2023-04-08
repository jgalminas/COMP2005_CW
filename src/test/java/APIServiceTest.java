import data.Static;
import models.Admission;
import models.Allocation;
import models.Employee;
import models.Patient;
import network.MaternityAPI;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class APIServiceTest {

    private static MaternityAPI maternityAPI;
    private static APIService apiService;

    @BeforeAll
    static void setUp() throws IOException {
        maternityAPI = mock(MaternityAPI.class);
        apiService = new APIServiceImpl(maternityAPI);

        Call<List<Admission>> admissionsCall = mock(Call.class);

        // mock GET admissions endpoint
        when(maternityAPI.getAdmissions()).thenReturn(admissionsCall);
        when(admissionsCall.execute()).thenReturn(Response.success(Static.ADMISSIONS));

        Call<List<Allocation>> allocationsCall = mock(Call.class);

        // mock GET allocations endpoint
        when(maternityAPI.getAllocations()).thenReturn(allocationsCall);
        when(allocationsCall.execute()).thenReturn(Response.success(Static.ALLOCATIONS));

        Call<List<Employee>> employeesCall = mock(Call.class);

        // mock GET employees endpoint
        when(maternityAPI.getEmployees()).thenReturn(employeesCall);
        when(employeesCall.execute()).thenReturn(Response.success(Static.EMPLOYEES));

        Call<List<Patient>> patientCall = mock(Call.class);

        // mock GET patients endpoint
        when(maternityAPI.getPatients()).thenReturn(patientCall);
        when(patientCall.execute()).thenReturn(Response.success(Static.PATIENTS));

    }

    @Test
    void getPatientsByEmployeeNegativeId() {

        int id = -10;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeIdNonExisting() {

        int id = 0;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeIdMaxInt() {
        int id = Integer.MAX_VALUE;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeIdMinInt() {
        int id = Integer.MIN_VALUE;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeIdValidButNoRecords() {
        int id = 1;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeIdValidWithRecords() {
        int id = 4;
        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
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