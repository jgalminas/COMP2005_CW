package api.integration;

import api.network.MaternityAPI;
import api.network.MaternityServiceImpl;
import test_data.Data;
import api.models.Admission;
import api.models.Allocation;
import api.models.Patient;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        // arrange
        // mock GET allocations endpoint
        Call<List<Allocation>> call = mock(Call.class);

        when(maternityAPI.getAllocations()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.success(Data.ALLOCATIONS));
            return null;
        }).when(call).enqueue(any(Callback.class));

        CompletableFuture<List<Allocation>> expectedFuture = CompletableFuture.supplyAsync(() -> Data.ALLOCATIONS);
        List<Allocation> expected = expectedFuture.join();

        // act
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();
        List<Allocation> actual = actualFuture.join();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );

    }

    @Test
    void testGetAllocations_Unsuccessful() {

        // arrange
        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Allocation>> call = mock(Call.class);

        when(maternityAPI.getAllocations()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad Request")));
            return null;
        }).when(call).enqueue(any(Callback.class));

        CompletableFuture<List<Allocation>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        List<Allocation> expected = expectedFuture.join();

        // act
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();
        List<Allocation> actual = actualFuture.join();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );

    }

    @Test
    void testGetAllocations_Failed() {

        // arrange
        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Allocation>> call = mock(Call.class);

        when(maternityAPI.getAllocations()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Allocation>> callback = invocation.getArgument(0);
            callback.onFailure(call, new Throwable());
            return null;
        }).when(call).enqueue(any(Callback.class));

        // act
        CompletableFuture<List<Allocation>> actualFuture = maternityService.getAllocations();

        // assert
        assertThrows(CompletionException.class, actualFuture::join);

    }

    @Test
    void testGetAdmissions_Successful() {

        // arrange
        // mock GET admissions endpoint
        Call<List<Admission>> call = mock(Call.class);

        when(maternityAPI.getAdmissions()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.success(Data.ADMISSIONS));
            return null;
        }).when(call).enqueue(any(Callback.class));

        CompletableFuture<List<Admission>> expectedFuture = CompletableFuture.supplyAsync(() -> Data.ADMISSIONS);
        List<Admission> expected = expectedFuture.join();

        // act
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();
        List<Admission> actual = actualFuture.join();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testGetAdmissions_Unsuccessful() {

        // arrange
        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Admission>> call = mock(Call.class);

        when(maternityAPI.getAdmissions()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad Request")));
            return null;
        }).when(call).enqueue(any(Callback.class));

        CompletableFuture<List<Admission>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        List<Admission> expected = expectedFuture.join();

        // act
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();
        List<Admission> actual = actualFuture.join();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );

    }

    @Test
    void testGetAdmissions_Failed() {

        // arrange
        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Admission>> call = mock(Call.class);

        when(maternityAPI.getAdmissions()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Admission>> callback = invocation.getArgument(0);
            callback.onFailure(call, new Throwable());
            return null;
        }).when(call).enqueue(any(Callback.class));

        // act
        CompletableFuture<List<Admission>> actualFuture = maternityService.getAdmissions();

        // assert
        assertThrows(CompletionException.class, actualFuture::join);

    }

    @Test
    void testGetPatients_Successful() {

        // arrange
        // mock GET patients endpoint
        Call<List<Patient>> call = mock(Call.class);

        when(maternityAPI.getPatients()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onResponse(call, Response.success(Data.PATIENTS));
            return null;
        }).when(call).enqueue(any(Callback.class));

        CompletableFuture<List<Patient>> expectedFuture = CompletableFuture.supplyAsync(() -> Data.PATIENTS);
        List<Patient> expected = expectedFuture.join();

        // act
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();
        List<Patient> actual = actualFuture.join();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatients_Unsuccessful() {

        // arrange
        // mock GET patients endpoint
        Call<List<Patient>> patientCall = mock(Call.class);

        when(maternityAPI.getPatients()).thenReturn(patientCall);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onResponse(patientCall, Response.error(400, ResponseBody.create(MediaType.parse("application/json"), "Bad Request")));
            return null;
        }).when(patientCall).enqueue(any(Callback.class));

        CompletableFuture<List<Patient>> expectedFuture = CompletableFuture.supplyAsync(Collections::emptyList);
        List<Patient> expected = expectedFuture.join();

        // act
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();
        List<Patient> actual = actualFuture.join();

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Patient::getId).toArray(),
                actual.stream().mapToInt(Patient::getId).toArray()
        );
    }

    @Test
    void testGetPatients_Failed() {

        // arrange
        // mock GET admissions endpoint with unsuccessful outcome
        Call<List<Patient>> call = mock(Call.class);

        when(maternityAPI.getPatients()).thenReturn(call);
        doAnswer(invocation -> {
            Callback<List<Patient>> callback = invocation.getArgument(0);
            callback.onFailure(call, new Throwable());
            return null;
        }).when(call).enqueue(any(Callback.class));

        // act
        CompletableFuture<List<Patient>> actualFuture = maternityService.getPatients();

        // assert
        assertThrows(CompletionException.class, actualFuture::join);

    }

}