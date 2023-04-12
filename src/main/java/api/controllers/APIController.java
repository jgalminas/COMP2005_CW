package api.controllers;

import api.services.APIService;
import api.models.AdmissionDuration;
import api.models.Day;
import api.models.Patient;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class APIController {

    private final APIService apiService;

    @Autowired
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }

    @Operation(summary = "Get the day of the week which has the most amount of admissions.",
            description = "If two or more days of the week have the same amount of admissions only one will be returned at random.")
    @GetMapping(value = "day-with-most-admissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public Day getDayWithMostAdmissions() {
        return apiService.getDayWithMostAdmissions();
    }

    @Operation(summary = "Get the average patient admission duration for a specific employee.",
            description = "If an admission has an invalid date format, the duration will be set to 0, therefore, affecting the result.")
    @GetMapping(value = "avg-patient-time/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AdmissionDuration getAvgPatientTimeByEmployeeId(@PathVariable("id") int id) {
        return apiService.getAvgPatientTimeByEmployeeId(id);
    }

    @Operation(summary = "Get a list of patients seen by a specific employee.")
    @GetMapping(value = "patients-by-employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getPatientsByEmployeeId(@PathVariable("id") int id) {
        return apiService.getPatientsByEmployeeId(id);
    }

    @Operation(summary = "Get a list of patients which were discharged within 3 days of admission.")
    @GetMapping(value = "discharged-within-3-days", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Patient> getPatientsDischargedWithin3Days() {
        return apiService.getPatientsDischargedWithin3Days();
    }
}
