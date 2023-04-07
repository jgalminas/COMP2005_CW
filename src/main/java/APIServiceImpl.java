import models.Patient;

import java.util.List;

public class APIServiceImpl implements APIService {

    @Override
    public List<Patient> getPatientsByEmployeeId(int id) {
        return null;
    }

    @Override
    public List<Patient> getRecentlyDischargedPatients() {
        return null;
    }

    @Override
    public String getDayWithMostAdmissions() {
        return null;
    }

    @Override
    public int getAvgPatientTimeByEmployeeId(int id) {
        return 0;
    }

}
