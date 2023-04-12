import api.APIService;
import api.APIServiceImpl;
import data.Static;
import api.models.Admission;
import api.models.Patient;
import api.network.MaternityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import api.utils.DateUtil;
import java.util.ArrayList;
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
        when(maternityService.getPatients()).thenReturn(CompletableFuture.supplyAsync(() -> Static.PATIENTS));

    }

    @Test
    void getPatientsByEmployeeId_Negative() {

        int id = -10;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeId_NonExisting() {

        int id = 0;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeId_MaxInt() {
        int id = Integer.MAX_VALUE;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeId_MinInt() {
        int id = Integer.MIN_VALUE;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeId_ValidButNoRecords() {
        int id = 1;
        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void getPatientsByEmployeeId_ValidWithRecords() {
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

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2023-04-11T22:14:00"), DateUtil.StringToDate("2023-04-04T22:14:00"), 1),
                new Admission(2, DateUtil.StringToDate("2023-04-18T22:14:00"), DateUtil.StringToDate("2023-04-11T22:14:00"), 2),
                new Admission(3, DateUtil.StringToDate("2023-04-25T21:50:00"), DateUtil.StringToDate("2023-04-23T22:14:00"), 2),
                new Admission(4, DateUtil.StringToDate("2023-04-07T22:14:00"), DateUtil.StringToDate("2023-04-24T22:14:00"), 3),
                new Admission(5, DateUtil.StringToDate("2023-04-02T21:50:00"), DateUtil.StringToDate("2023-04-22T22:14:00"), 2)
        ))));

        String expected = "TUESDAY";
        String actual = apiService.getDayWithMostAdmissions();

        assertEquals(expected, actual);
    }

    @Test
    void getDayWithMostAdmissions_NoAdmissions() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));

        String actual = apiService.getDayWithMostAdmissions();
        assertNull(actual);
    }

    @Test
    void getDayWithMostAdmissions_TwoOrMoreEqualDays() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2023-04-12T22:14:00"), DateUtil.StringToDate("2023-04-04T22:14:00"), 1),
                new Admission(2, DateUtil.StringToDate("2023-04-12T22:14:00"), DateUtil.StringToDate("2023-04-11T22:14:00"), 2),
                new Admission(3, DateUtil.StringToDate("2023-04-23T21:50:00"), DateUtil.StringToDate("2023-04-23T22:14:00"), 2),
                new Admission(4, DateUtil.StringToDate("2023-04-23T22:14:00"), DateUtil.StringToDate("2023-04-24T22:14:00"), 3),
                new Admission(5, DateUtil.StringToDate("2023-04-23T21:50:00"), DateUtil.StringToDate("2023-04-22T22:14:00"), 2)
        ))));

        String expected = "SUNDAY";
        String actual = apiService.getDayWithMostAdmissions();

        assertEquals(expected, actual);
    }

    @Test
    void getAvgPatientTimeByEmployeeId_ValidButNoAdmissions() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));

        long expected = 0;
        long actual = apiService.getAvgPatientTimeByEmployeeId(4);

        assertEquals(expected, actual);

    }

    @Test
    void getAvgPatientTimeByEmployeeId_ValidWithAdmissions() {

        long expected = 2738;
        long actual = apiService.getAvgPatientTimeByEmployeeId(4);

        assertEquals(expected, actual);

    }

    @Test
    void getAvgPatientTimeByEmployeeId_InvalidAdmissionDateRange() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-25T23:56:00"), 2),
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
        ))));

        long expected = 2523;
        long actual = apiService.getAvgPatientTimeByEmployeeId(4);

        assertEquals(expected, actual);

    }

    @Test
    void getAvgPatientTimeByEmployeeId_Invalid() {

        long expected = 0;
        long actual = apiService.getAvgPatientTimeByEmployeeId(-1);

        assertEquals(expected, actual);

    }

    @Test
    void getPatientsDischargedWithin3Days_NoAdmissions() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(ArrayList::new));

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getPatientsDischargedWithin3Days_NoDischargesWithin3Days() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(4, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-01-25T21:50:00"), 2)
        ))));

        List<Patient> expected = Collections.emptyList();
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getPatientsDischargedWithin3Days() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-25T21:50:00"), 2)
        ))));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getPatientsDischargedWithin3Days_DischargedWithin0Minutes() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-23T21:50:00"), 2)
        ))));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void getPatientsDischargedWithin3Days_Exactly3Days() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-24T21:50:00"), 2)
        ))));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

}