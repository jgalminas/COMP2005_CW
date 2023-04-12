package api.network;

import api.models.Admission;
import api.models.Allocation;
import api.models.Patient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MaternityService {

    CompletableFuture<List<Allocation>> getAllocations();
    CompletableFuture<List<Admission>> getAdmissions();
    CompletableFuture<List<Patient>> getPatients();

}
