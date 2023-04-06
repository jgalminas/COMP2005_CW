import models.Patient;

import java.util.List;

public class APIService implements IAPIService {


    @Override
    public List<Patient> getPatientsByEmployeeId(int id) {
        return null;
    }

    @Override
    public List<Patient> getRecentlyDischargedPatients() {
        return null;
    }

    @Override
    public String GetDayWithMostAdmissions() {
        return null;
    }

    @Override
    public int GetAvgPatientTimeByEmployeeId(int id) {
        return 0;
    }
}
