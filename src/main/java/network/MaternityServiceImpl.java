package network;

import models.Admission;
import models.Allocation;
import models.Employee;
import models.Patient;
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
        return CompletableFuture.supplyAsync(Collections::emptyList);
    }

    @Override
    public CompletableFuture<List<Admission>> getAdmissions() {
        return null;
    }

    @Override
    public CompletableFuture<List<Employee>> getEmployees() {
        return null;
    }

    @Override
    public CompletableFuture<List<Patient>> getPatients() {
        return null;
    }

}
