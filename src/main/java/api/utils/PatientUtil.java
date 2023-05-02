package api.utils;

import api.models.Admission;
import api.models.Patient;
import java.util.*;

public class PatientUtil {

    /**
     * Find all patients which have been admitted before.
     * @param patients List of Patient objects
     * @param admissions List of Admission objects
     * @return List of Patient objects
     */
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
