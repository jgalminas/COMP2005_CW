package network;

import models.Admission;
import models.Allocation;
import models.Patient;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MaternityService {

    CompletableFuture<List<Allocation>> getAllocations();
    CompletableFuture<List<Admission>> getAdmissions();
    CompletableFuture<List<Patient>> getPatients();

}
