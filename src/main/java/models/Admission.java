package models;

import java.util.Date;

public class Admission {
    private final int id;
    private Date admissionDate;
    private Date dischargeDate;
    private int patientId;

    public Admission(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
}
