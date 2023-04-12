package api;

import api.models.AdmissionDuration;
import api.models.Day;
import api.models.Patient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface APIService {
    List<Patient> getPatientsByEmployeeId(int id);
    List<Patient> getPatientsDischargedWithin3Days();
    Day getDayWithMostAdmissions();
    AdmissionDuration getAvgPatientTimeByEmployeeId(int id);
}
