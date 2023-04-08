package utils;

import models.Admission;
import models.Allocation;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdmissionsUtil {

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

    public static long getAdmissionDuration(Admission admission) {
        return Duration.between(admission.getAdmissionDate(), admission.getDischargeDate()).toMinutes();
    }
}
