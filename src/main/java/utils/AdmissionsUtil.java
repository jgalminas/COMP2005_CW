package utils;

import models.Admission;
import models.Allocation;

import java.util.ArrayList;
import java.util.List;

public class AdmissionsUtil {

    public static List<Admission> filterByAllocations(List<Admission> admissions,  List<Allocation> allocations) {

        List<Admission> filtered = new ArrayList<>();

        if (admissions != null && allocations != null) {

            for (Allocation alloc : allocations) {

                for (Admission adm : admissions) {
                    if (adm.getId() == alloc.getAdmissionId()) {
                        filtered.add(adm);
                    }
                }

            }

        }

        return filtered;
    }

}
