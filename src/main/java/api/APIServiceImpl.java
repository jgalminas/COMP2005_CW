package api;

import api.models.*;
import api.network.MaternityService;
import api.utils.AdmissionsUtil;
import api.utils.AllocationsUtil;
import api.utils.PatientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class APIServiceImpl implements APIService {

    private final MaternityService maternityService;

    @Autowired
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

            long durationInDays = AdmissionsUtil.getAdmissionDuration(ad).toDays();

            if (durationInDays >= 0 && durationInDays < 3) {
                filteredAdmissions.add(ad);
            }
        }

        return PatientUtil.filterByAdmissions(patients, filteredAdmissions);
    }

    @Override
    public Day getDayWithMostAdmissions() {

        List<Admission> admissions = maternityService.getAdmissions().join();

        Map<String, Integer> dayCounts = new HashMap<>();

        // sum up the days
        for (Admission ad : admissions) {
            String day = ad.getAdmissionDate().getDayOfWeek().toString();
            dayCounts.put(day, (dayCounts.get(day) != null ? dayCounts.get(day): 0) + 1);
        }

        // get the day with most admissions
        String day = null;

        for (String key : dayCounts.keySet()) {
            if (day == null || dayCounts.get(day) < dayCounts.get(key)) {
                day = key;
            }
        }

        return new Day(day);
    }

    @Override
    public AdmissionDuration getAvgPatientTimeByEmployeeId(int id) {

        List<Admission> admissions = maternityService.getAdmissions().join();
        List<Allocation> allocations = maternityService.getAllocations().join();

        List<Allocation> filteredAllocations = AllocationsUtil.filterByEmployeeId(id, allocations);
        List<Admission> filteredAdmissions = AdmissionsUtil.filterByAllocations(admissions, filteredAllocations);

        // calculate avg time per admission
        long totalTime = 0;

        for (Admission ad : filteredAdmissions) {
            totalTime += Math.max(0, AdmissionsUtil.getAdmissionDuration(ad).toMinutes());
        }

        return new AdmissionDuration(totalTime == 0 ? totalTime : totalTime / filteredAdmissions.size());
    }

}
