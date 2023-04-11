import models.Admission;
import models.Allocation;
import models.Patient;
import network.MaternityService;
import utils.AdmissionsUtil;
import utils.AllocationsUtil;
import utils.PatientUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APIServiceImpl implements APIService {

    private final MaternityService maternityService;

    public APIServiceImpl(MaternityService maternityAPI) {
        this.maternityService = maternityAPI;
    }

    @Override
    public List<Patient> getPatientsByEmployeeId(int id) {

        List<Admission> admissions = maternityService.getAdmissions().join();
        List<Allocation> allocations = maternityService.getAllocations().join();
        List<Patient> patients = maternityService.getPatients().join();

        List<Allocation> filteredAllocations = AllocationsUtil.filterByEmployeeId(id, allocations);
        List<Admission> filteredAdmissions = AdmissionsUtil.filterByAllocations(admissions, filteredAllocations);

        return PatientUtil.filterByAdmissions(patients, filteredAdmissions);

    }

    @Override
    public List<Patient> getPatientsDischargedWithin3Days() {

        List<Admission> admissions = maternityService.getAdmissions().join();
        List<Patient> patients = maternityService.getPatients().join();

        List<Admission> filteredAdmissions = new ArrayList<>();

        for (Admission ad : admissions) {

            long durationInDays = AdmissionsUtil.getAdmissionDuration(ad) / (60 * 24);

            if (durationInDays >= 0 && durationInDays < 3) {
                filteredAdmissions.add(ad);
            }
        }

        return PatientUtil.filterByAdmissions(patients, filteredAdmissions);
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
