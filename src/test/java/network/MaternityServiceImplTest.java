package network;

import data.Static;
import models.Admission;
import models.Allocation;
import models.Employee;
import models.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MaternityServiceImplTest {

    private static MaternityAPI maternityAPI;
    private static MaternityServiceImpl maternityService;

    @BeforeAll
    static void setUp() throws IOException {
        maternityAPI = mock(MaternityAPI.class);
        maternityService = new MaternityServiceImpl(maternityAPI);

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
    void getAllocationsNotEmpty() {

        CompletableFuture<List<Allocation>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.ALLOCATIONS);
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Allocation> expected = expectedFuture.join();
            List<Allocation> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Allocation::getId).toArray(),
                    actual.stream().mapToInt(Allocation::getId).toArray()
            );
        });

    }

    @Test
    void getAllocationsEmpty() {
        CompletableFuture<List<Allocation>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Allocation> expected = expectedFuture.join();
            List<Allocation> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Allocation::getId).toArray(),
                    actual.stream().mapToInt(Allocation::getId).toArray()
            );
        });
    }

    @Test
    void getAdmissionsNotEmpty() {
        CompletableFuture<List<Admission>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.ADMISSIONS);
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Admission> expected = expectedFuture.join();
            List<Admission> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Admission::getId).toArray(),
                    actual.stream().mapToInt(Admission::getId).toArray()
            );
        });
    }

    @Test
    void getAdmissionsEmpty() {
        CompletableFuture<List<Admission>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Admission> expected = expectedFuture.join();
            List<Admission> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Admission::getId).toArray(),
                    actual.stream().mapToInt(Admission::getId).toArray()
            );
        });
    }

    @Test
    void getEmployeesNotEmpty() {
        CompletableFuture<List<Employee>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.EMPLOYEES);
        CompletableFuture<List<Employee>> actualFuture = maternityService.getEmployees();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Employee> expected = expectedFuture.join();
            List<Employee> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Employee::getId).toArray(),
                    actual.stream().mapToInt(Employee::getId).toArray()
            );
        });
    }

    @Test
    void getEmployeesEmpty() {
        CompletableFuture<List<Employee>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Employee>> actualFuture = maternityService.getEmployees();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Employee> expected = expectedFuture.join();
            List<Employee> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Employee::getId).toArray(),
                    actual.stream().mapToInt(Employee::getId).toArray()
            );
        });
    }

    @Test
    void getPatientsNotEmpty() {
        CompletableFuture<List<Patient>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.PATIENTS);
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Patient> expected = expectedFuture.join();
            List<Patient> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Patient::getId).toArray(),
                    actual.stream().mapToInt(Patient::getId).toArray()
            );
        });
    }

    @Test
    void getPatientsEmpty() {
        CompletableFuture<List<Patient>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();

        assertTimeout(Duration.ofSeconds(2), () -> {
            List<Patient> expected = expectedFuture.join();
            List<Patient> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Patient::getId).toArray(),
                    actual.stream().mapToInt(Patient::getId).toArray()
            );
        });
    }

}