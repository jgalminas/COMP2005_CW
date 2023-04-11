import models.Patient;

import java.util.List;

public interface APIService {
    List<Patient> getPatientsByEmployeeId(int id);
    List<Patient> getPatientsDischargedWithin3Days();
    String getDayWithMostAdmissions();
    long getAvgPatientTimeByEmployeeId(int id);
}
