package api.integration;
import api.models.AdmissionDuration;
import api.models.Day;
import api.services.APIService;
import api.services.APIServiceImpl;
import test_data.Data;
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

    static final MaternityService maternityService = mock(MaternityService.class);
    static final APIService apiService = new APIServiceImpl(maternityService);

    @BeforeAll
    static void setUp()  {
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> Data.ADMISSIONS));
        when(maternityService.getAllocations()).thenReturn(CompletableFuture.supplyAsync(() -> Data.ALLOCATIONS));
        when(maternityService.getPatients()).thenReturn(CompletableFuture.supplyAsync(() -> Data.PATIENTS));
    }

    @Test
    void testGetPatientsByEmployeeId_Negative() {

        // arrange
        int id = -10;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatientsByEmployeeId_NonExisting() {

        // arrange
        int id = 0;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatientsByEmployeeId_MaxInt() {
        // arrange
        int id = Integer.MAX_VALUE;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatientsByEmployeeId_MinInt() {

        // arrange
        int id = Integer.MIN_VALUE;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatientsByEmployeeId_ValidButNoRecords() {

        // arrange
        int id = 1;
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatientsByEmployeeId_ValidWithRecords() {

        // arrange
        int id = 4;
        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );

        // act
        List<Patient> actual = apiService.getPatientsByEmployeeId(id);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetDayWithMostAdmissions() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2023-04-11T22:14:00"), DateUtil.StringToDate("2023-04-04T22:14:00"), 1),
                new Admission(2, DateUtil.StringToDate("2023-04-18T22:14:00"), DateUtil.StringToDate("2023-04-11T22:14:00"), 2),
                new Admission(3, DateUtil.StringToDate("2023-04-25T21:50:00"), DateUtil.StringToDate("2023-04-23T22:14:00"), 2),
                new Admission(4, DateUtil.StringToDate("2023-04-07T22:14:00"), DateUtil.StringToDate("2023-04-24T22:14:00"), 3),
                new Admission(5, DateUtil.StringToDate("2023-04-02T21:50:00"), DateUtil.StringToDate("2023-04-22T22:14:00"), 2)
        ))));

        Day expected = new Day("TUESDAY");

        // act
        Day actual = apiService.getDayWithMostAdmissions();

        // assert
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void testGetDayWithMostAdmissions_NoAdmissions() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));

        // act
        Day actual = apiService.getDayWithMostAdmissions();

        // assert
        assertNull(actual.getName());
    }

    @Test
    void testGetDayWithMostAdmissions_TwoOrMoreEqualDays() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2023-04-12T22:14:00"), DateUtil.StringToDate("2023-04-04T22:14:00"), 1),
                new Admission(2, DateUtil.StringToDate("2023-04-12T22:14:00"), DateUtil.StringToDate("2023-04-11T22:14:00"), 2),
                new Admission(3, DateUtil.StringToDate("2023-04-23T21:50:00"), DateUtil.StringToDate("2023-04-23T22:14:00"), 2),
                new Admission(4, DateUtil.StringToDate("2023-04-23T22:14:00"), DateUtil.StringToDate("2023-04-24T22:14:00"), 3),
                new Admission(5, DateUtil.StringToDate("2023-04-23T21:50:00"), DateUtil.StringToDate("2023-04-22T22:14:00"), 2)
        ))));

        Day expected = new Day("SUNDAY");

        // act
        Day actual = apiService.getDayWithMostAdmissions();

        // assert
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void testGetAvgPatientTimeByEmployeeId_ValidButNoAdmissions() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(Collections::emptyList));
        AdmissionDuration expected = new AdmissionDuration(0);

        // act
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(4);

        // assert
        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void testGetAvgPatientTimeByEmployeeId_ValidWithAdmissions() {

        // arrange
        AdmissionDuration expected = new AdmissionDuration(2738);

        // act
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(4);

        // assert
        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void testGetAvgPatientTimeByEmployeeId_InvalidAdmissionDateRange() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-25T23:56:00"), 2),
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
        ))));

        AdmissionDuration expected = new AdmissionDuration(2523);

        // act
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(4);

        // assert
        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void testGetAvgPatientTimeByEmployeeId_Invalid() {

        // arrange
        AdmissionDuration expected = new AdmissionDuration(0);

        // act
        AdmissionDuration actual = apiService.getAvgPatientTimeByEmployeeId(-1);

        // assert
        assertEquals(expected.getAmount(), actual.getAmount());

    }

    @Test
    void testGetPatientsDischargedWithin3Days_NoAdmissions() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(ArrayList::new));
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void testGetPatientsDischargedWithin3Days_NoDischargesWithin3Days() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(4, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-01-25T21:50:00"), 2)
        ))));
        List<Patient> expected = Collections.emptyList();

        // act
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void testGetPatientsDischargedWithin3Days() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-25T21:50:00"), 2)
        ))));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );

        // act
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void testGetPatientsDischargedWithin3Days_DischargedWithin0Minutes() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-23T21:50:00"), 2)
        ))));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );

        // act
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

    @Test
    void testGetPatientsDischargedWithin3Days_Exactly3Days() {

        // arrange
        when(maternityService.getAdmissions()).thenReturn(CompletableFuture.supplyAsync(() -> new ArrayList<>(Arrays.asList(
                new Admission(2, DateUtil.StringToDate("2020-12-07T22:14:00"), DateUtil.StringToDate("0001-01-01T00:00:00"), 1),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-24T21:50:00"), 2)
        ))));

        List<Patient> expected = Arrays.asList(
                new Patient(2, "Carter", "Heather", "2224446666")
        );

        // act
        List<Patient> actual = apiService.getPatientsDischargedWithin3Days();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );

    }

}