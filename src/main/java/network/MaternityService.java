package network;

import models.Allocation;
import models.Employee;
import models.Patient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface MaternityService {

    @GET("allocations")
    Call<List<Allocation>> getAllocations();

    @GET("allocations/{id}")
    Call<Allocation> getAllocationById(@Path("id") int id);

    @GET("employees")
    Call<List<Employee>> getEmployees();

    @GET("employees/{id}")
    Call<Employee> getEmployeeById(@Path("id") int id);

    @GET("patients")
    Call<List<Patient>> getPatients();

    @GET("patients/{id}")
    Call<Patient> getPatientById(@Path("id") int id);

}
