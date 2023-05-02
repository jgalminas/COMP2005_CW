package api.utils;

import api.models.Admission;
import api.models.Allocation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdmissionsUtil {

    /**
     * Finds all patient admissions which correspond to admissions in the given list.
     * @param admissions List of Admission objects
     * @param allocations List of Allocation objects
     * @return List of Admission objects
     */
    public static List<Admission> filterByAllocations(List<Admission> admissions,  List<Allocation> allocations) {

        Map<Integer, Admission> filtered = new HashMap<>();

        if (admissions != null && allocations != null) {

            for (Allocation alloc : allocations) {

                for (Admission adm : admissions) {
                    if (adm.getId() == alloc.getAdmissionId()) {
                        filtered.put(adm.getId(), adm);
                    }
                }

            }

        }

        return new ArrayList<>(filtered.values());
    }

    /**
     * Calculates the duration of an admission.
     * @param admission Admission object
     * @return Duration object
     */
    public static Duration getAdmissionDuration(Admission admission) {
        return Duration.between(admission.getAdmissionDate(), admission.getDischargeDate());
    }
}
