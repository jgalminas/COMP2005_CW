package network;

import data.Static;
import models.Admission;
import models.Allocation;
import models.Employee;
import models.Patient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaternityServiceImplTest {

    private static MaternityServiceImpl maternityService;

    @BeforeAll
    static void setUp() {
        MaternityAPI maternityAPI = mock(MaternityAPI.class);
        maternityService = new MaternityServiceImpl(maternityAPI);

        Call<List<Admission>> admissionsCall = mock(Call.class);

        // mock GET admissions endpoint
        when(maternityAPI.getAdmissions()).thenReturn(admissionsCall);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onResponse(admissionsCall, Response.success(Static.ADMISSIONS));
            return null;
        }).when(admissionsCall).enqueue(any(Callback.class));

        Call<List<Allocation>> allocationsCall = mock(Call.class);

        // mock GET allocations endpoint
        when(maternityAPI.getAllocations()).thenReturn(allocationsCall);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onResponse(allocationsCall, Response.success(Static.ALLOCATIONS));
            return null;
        }).when(allocationsCall).enqueue(any(Callback.class));


        Call<List<Employee>> employeesCall = mock(Call.class);

        // mock GET employees endpoint
        when(maternityAPI.getEmployees()).thenReturn(employeesCall);
        doAnswer(invocation -> {
            Callback<List<Employee>> callback = invocation.getArgument(0);
            callback.onResponse(employeesCall, Response.success(Static.EMPLOYEES));
            return null;
        }).when(employeesCall).enqueue(any(Callback.class));

        Call<List<Patient>> patientCall = mock(Call.class);

        // mock GET patients endpoint
        when(maternityAPI.getPatients()).thenReturn(patientCall);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onResponse(patientCall, Response.success(Static.PATIENTS));
            return null;
        }).when(patientCall).enqueue(any(Callback.class));

    }

    @Test
    void testGetAllocations() {

        CompletableFuture<List<Allocation>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.ALLOCATIONS);
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();

        assertTimeout(Duration.ofSeconds(5), () -> {
            List<Allocation> expected = expectedFuture.join();
            List<Allocation> actual = actualFuture.join();

            assertArrayEquals(
                    expected.stream().mapToInt(Allocation::getId).toArray(),
                    actual.stream().mapToInt(Allocation::getId).toArray()
            );
        });

    }

    @Test
    void testGetAdmissions() {
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
    void testGetEmployees() {
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
    void testGetPatients() {
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

}