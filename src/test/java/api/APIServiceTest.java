package api;
import api.models.AdmissionDuration;
import api.models.Day;
import api.utils.AdmissionsUtil;
import data.Static;
import api.models.Admission;
import api.models.Patient;
import api.network.MaternityService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import api.utils.DateUtil;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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

        Day expected = new Day("TUESDAY");
        Day actual = apiService.getDayWithMostAdmissions();

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getDayWithMostAdmissions_NoAdmissions() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));

        Day actual = apiService.getDayWithMostAdmissions();
        assertNull(actual.getName());
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

        Day expected = new Day("SUNDAY");
        Day actual = apiService.getDayWithMostAdmissions();

        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void getAvgPatientTimeByEmployeeId_ValidButNoAdmissions() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));

        AdmissionDuration expected = new AdmissionDuration(0);
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(4);

        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void getAvgPatientTimeByEmployeeId_ValidWithAdmissions() {

        AdmissionDuration expected = new AdmissionDuration(2738);
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(4);

        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void getAvgPatientTimeByEmployeeId_InvalidAdmissionDateRange() {

        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-25T23:56:00"), 2),
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
        ))));

        AdmissionDuration expected = new AdmissionDuration(2523);
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(4);

        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void getAvgPatientTimeByEmployeeId_Invalid() {

        AdmissionDuration expected = new AdmissionDuration(0);
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(-1);

        assertEquals(expected.getAmount(), actual.getAmount());

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