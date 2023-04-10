import data.Static;
import models.Admission;
import models.Patient;
import network.MaternityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DateUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class APIServiceTest {

    static APIService apiService = new APIServiceImpl(mock(MaternityService.class));
    static final MaternityService maternityService = mock(MaternityService.class);


    @BeforeAll
    static void setUp()  {

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

    @Test
    void getRecentlyDischarged_NoAdmissions() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getRecentlyDischargedPatients();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getRecentlyDischarged_NoRecentAdmissions() {

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getRecentlyDischargedPatients();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getRecentlyDischarged() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), LocalDateTime.now().minusDays(1), 2)
        )));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getRecentlyDischargedPatients();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getRecentlyDischarged_0Minutes() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), LocalDateTime.now(), 2)
        )));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getRecentlyDischargedPatients();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getRecentlyDischarged_Exactly3Days() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), LocalDateTime.now().minusDays(3), 2)
        )));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getRecentlyDischargedPatients();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

}