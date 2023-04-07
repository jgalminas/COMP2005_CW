import models.Patient;
import network.MaternityService;
import java.util.List;

public class APIServiceImpl implements APIService {

    private final MaternityService maternityService;

    public APIServiceImpl(MaternityService maternityService) {
        this.maternityService = maternityService;
    }

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
