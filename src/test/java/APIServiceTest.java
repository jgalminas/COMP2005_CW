import data.Static;
import models.Patient;
import network.MaternityService;
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

    private static MaternityService maternityService;
    private static APIService apiService;

    @BeforeAll
    static void setUp() throws IOException {
        maternityService = mock(MaternityService.class);
        apiService = new APIServiceImpl(maternityService);

        Call call = mock(Call.class);

        // mock GET admissions endpoint
        when(maternityService.getAdmissions()).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(Static.ADMISSIONS));

        // mock GET allocations endpoint
        when(maternityService.getAllocations()).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(Static.ALLOCATIONS));

        // mock GET employees endpoint
        when(maternityService.getEmployees()).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(Static.EMPLOYEES));

        // mock GET patients endpoint
        when(maternityService.getPatients()).thenReturn(call);
        when(call.execute()).thenReturn(Response.success(Static.PATIENTS));

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