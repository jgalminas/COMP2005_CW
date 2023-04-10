import data.Static;
import models.Patient;
import network.MaternityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class APIServiceTest {

    private static APIService apiService = new APIServiceImpl(mock(MaternityService.class));

    @BeforeAll
    static void setUp()  {

        MaternityService maternityService = mock(MaternityService.class);
        apiService = new APIServiceImpl(maternityService);

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> Static.ADMISSIONS));
        when(maternityService.getAllocations()).thenReturn(CompletableFuture.supplyAsync(() -> Static.ALLOCATIONS));
        when(maternityService.getEmployees()).thenReturn(CompletableFuture.supplyAsync(() -> Static.EMPLOYEES));
        when(maternityService.getPatients()).thenReturn(CompletableFuture.supplyAsync(() -> Static.PATIENTS));

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