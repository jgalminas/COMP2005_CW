import models.Patient;

import java.util.List;

public interface IAPIService {
    List<Patient> getPatientsByEmployeeId(int id);
    List<Patient> getRecentlyDischargedPatients();
    String GetDayWithMostAdmissions();
    int GetAvgPatientTimeByEmployeeId(int id);
}
