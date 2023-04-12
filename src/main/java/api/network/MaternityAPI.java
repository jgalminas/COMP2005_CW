package api.network;

import api.models.Admission;
import api.models.Allocation;
import api.models.Patient;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface MaternityAPI {

    @GET("admissions")
    Call<List<Admission>> getAdmissions();

    @GET("allocations")
    Call<List<Allocation>> getAllocations();

    @GET("patients")
    Call<List<Patient>> getPatients();

}
