package models;

import java.time.LocalDateTime;

public class Admission {
    private final int id;
    private final LocalDateTime admissionDate;
    private final LocalDateTime dischargeDate;
    private final int patientId;

    public Admission(int id, LocalDateTime admissionDate, LocalDateTime dischargeDate, int patientId) {
        this.id = id;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getAdmissionDate() {
        return admissionDate;
    }

    public LocalDateTime getDischargeDate() {
        return dischargeDate;
    }

    public int getPatientId() {
        return patientId;
    }

}
