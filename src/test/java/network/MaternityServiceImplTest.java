package network;

import data.Static;
import models.Admission;
import models.Allocation;
import models.Patient;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MaternityServiceImplTest {

    final MaternityAPI maternityAPI = mock(MaternityAPI.class);
    final MaternityServiceImpl maternityService = new MaternityServiceImpl(maternityAPI);

    @Test
    void testGetAllocations_Successful() {

        // mock GET allocations endpoint
        Call<List<Allocation>> call = mock(Call.class);

        when(maternityAPI.getAllocations()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.success(Static.ALLOCATIONS));
            return null;
        }).when(call).enqueue(any(Callback.class));


        // test the method
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
    void testGetAllocations_Unsuccessful() {

        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Allocation>> call = mock(Call.class);

        when(maternityAPI.getAllocations()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad Request")));
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Allocation>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();

        List<Allocation> expected = expectedFuture.join();
        List<Allocation> actual = actualFuture.join();

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );

    }

    @Test
    void testGetAllocations_Failed() {

        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Allocation>> call = mock(Call.class);

        when(maternityAPI.getAllocations()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onFailure(call, new Throwable());
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();

        assertThrows(CompletionException.class, actualFuture::join);

    }

    @Test
    void testGetAdmissions_Successful() {

        // mock GET admissions endpoint
        Call<List<Admission>> call = mock(Call.class);

        when(maternityAPI.getAdmissions()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.success(Static.ADMISSIONS));
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Admission>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.ADMISSIONS);
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();

        List<Admission> expected = expectedFuture.join();
        List<Admission> actual = actualFuture.join();

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testGetAdmissions_Unsuccessful() {

        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Admission>> call = mock(Call.class);

        when(maternityAPI.getAdmissions()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad Request")));
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Admission>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();

        List<Admission> expected = expectedFuture.join();
        List<Admission> actual = actualFuture.join();

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );

    }

    @Test
    void testGetAdmissions_Failed() {

        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Admission>> call = mock(Call.class);

        when(maternityAPI.getAdmissions()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onFailure(call, new Throwable());
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();

        assertThrows(CompletionException.class, actualFuture::join);

    }

    @Test
    void testGetPatients_Successful() {

        // mock GET patients endpoint
        Call<List<Patient>> call = mock(Call.class);

        when(maternityAPI.getPatients()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.success(Static.PATIENTS));
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Patient>> expectedFuture = CompletableFuture.supplyAsync(() -> Static.PATIENTS);
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();

        List<Patient> expected = expectedFuture.join();
        List<Patient> actual = actualFuture.join();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatients_Unsuccessful() {

        // mock GET patients endpoint
        Call<List<Patient>> patientCall = mock(Call.class);

        when(maternityAPI.getPatients()).thenReturn(patientCall);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onResponse(patientCall, Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad Request")));
            return null;
        }).when(patientCall).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Patient>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();

        List<Patient> expected = expectedFuture.join();
        List<Patient> actual = actualFuture.join();

        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatients_Failed() {

        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Patient>> call = mock(Call.class);

        when(maternityAPI.getPatients()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onFailure(call, new Throwable());
            return null;
        }).when(call).enqueue(any(Callback.class));

        // test the method
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();

        assertThrows(CompletionException.class, actualFuture::join);

    }

}