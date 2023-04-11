package network;

import models.Admission;
import models.Allocation;
import models.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MaternityServiceImpl implements MaternityService {

    final MaternityAPI maternityAPI;

    public MaternityServiceImpl(MaternityAPI maternityAPI) {
        this.maternityAPI = maternityAPI;
    }

    @Override
    public CompletableFuture<List<Allocation>> getAllocations() {

        CompletableFuture<List<Allocation>> future = new CompletableFuture<>();

        maternityAPI.getAllocations().enqueue(new Callback<List<Allocation>>() {
            @Override
            public void onResponse(Call<List<Allocation>> call, Response<List<Allocation>> response) {
                if (response.code() == 200) {
                    future.complete(response.body());
                } else {
                    future.complete(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<Allocation>> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

    @Override
    public CompletableFuture<List<Admission>> getAdmissions() {

        CompletableFuture<List<Admission>> future = new CompletableFuture<>();

        maternityAPI.getAdmissions().enqueue(new Callback<List<Admission>>() {
            @Override
            public void onResponse(Call<List<Admission>> call, Response<List<Admission>> response) {
                if (response.code() == 200) {
                    future.complete(response.body());
                } else {
                    future.complete(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<Admission>> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

    @Override
    public CompletableFuture<List<Patient>> getPatients() {

        CompletableFuture<List<Patient>> future = new CompletableFuture<>();

        maternityAPI.getPatients().enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if (response.code() == 200) {
                    future.complete(response.body());
                } else {
                    future.complete(Collections.emptyList());
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                future.completeExceptionally(t);
            }
        });

        return future;
    }

}
