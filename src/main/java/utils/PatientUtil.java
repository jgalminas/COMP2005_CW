package utils;

import models.Admission;
import models.Patient;
import java.util.*;

public class PatientUtil {

    public static List<Patient> filterByAdmissions(List<Patient> patients, List<Admission> admissions) {

        Map<Integer, Patient> filtered = new HashMap<>();

        if (patients != null && admissions != null) {

            for (Admission adm : admissions) {

                for (Patient pat : patients) {
                    if (pat.getId() == adm.getPatientId()) {
                        filtered.put(pat.getId(), pat);
                    }
                }

            }

        }

        return new ArrayList<>(filtered.values());
    }

}
