import models.Patient;

import java.util.List;

public interface APIService {
    List<Patient> getPatientsByEmployeeId(int id);
    List<Patient> getRecentlyDischargedPatients();
    String getDayWithMostAdmissions();
    int getAvgPatientTimeByEmployeeId(int id);
}
