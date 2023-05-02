package api.services;

import api.models.*;
import api.network.MaternityService;
import api.utils.AdmissionsUtil;
import api.utils.AllocationsUtil;
import api.utils.PatientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class APIServiceImpl implements APIService {

    private final MaternityService maternityService;

    @Autowired
    public APIServiceImpl(MaternityService maternityAPI) {
        this.maternityService = maternityAPI;
    }

    /**
     * Retrieve patients by employee.
     * @param id employee ID
     * @return A list of Patient objects
     */
    @Override
    public List<Patient> getPatientsByEmployeeId(int id) {

        // retrieve data from the maternity web service
        List<Admission> admissions = maternityService.getAdmissions().join();
        List<Allocation> allocations = maternityService.getAllocations().join();
        List<Patient> patients = maternityService.getPatients().join();


        List<Allocation> filteredAllocations = AllocationsUtil.filterByEmployeeId(id, allocations);
        List<Admission> filteredAdmissions = AdmissionsUtil.filterByAllocations(admissions, filteredAllocations);

        return PatientUtil.filterByAdmissions(patients, filteredAdmissions);

    }

    /**
     * Find patients which were discharged within three days of admission.
     * @return List of Patient object
     */
    @Override
    public List<Patient> getPatientsDischargedWithin3Days() {

        // retrieve data from the maternity web service
        List<Admission> admissions = maternityService.getAdmissions().join();
        List<Patient> patients = maternityService.getPatients().join();

        // filter data
        List<Admission> filteredAdmissions = new ArrayList<>();

        for (Admission ad : admissions) {

            long durationInDays = AdmissionsUtil.getAdmissionDuration(ad).toDays();

            if (durationInDays >= 0 && durationInDays < 3) {
                filteredAdmissions.add(ad);
            }
        }

        return PatientUtil.filterByAdmissions(patients, filteredAdmissions);
    }

    /**
     * Find the day of the week with the most admissions.
     * @return Day object
     */
    @Override
    public Day getDayWithMostAdmissions() {

        // retrieve data from the maternity web service
        List<Admission> admissions = maternityService.getAdmissions().join();

        Map<String, Integer> dayCounts = new HashMap<>();

        // sum up the days
        for (Admission ad : admissions) {
            String day = ad.getAdmissionDate().getDayOfWeek().toString();
            dayCounts.put(day, (dayCounts.get(day) != null ? dayCounts.get(day): 0) + 1);
        }

        // get the day with most admissions
        String day = null;

        // find the day with the highest count
        for (String key : dayCounts.keySet()) {
            if (day == null || dayCounts.get(day) < dayCounts.get(key)) {
                day = key;
            }
        }

        return new Day(day);
    }

    /**
     * Calculates the average patient admission time by doctor.
     * @param id employee ID
     * @return an AdmissionDuration object
     */
    @Override
    public AdmissionDuration getAvgPatientTimeByEmployeeId(int id) {

        // retrieve data from the maternity web service
        List<Admission> admissions = maternityService.getAdmissions().join();
        List<Allocation> allocations = maternityService.getAllocations().join();

        // filter data
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
