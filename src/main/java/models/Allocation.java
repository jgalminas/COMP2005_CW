package models;

import java.util.Date;

public class Allocation {
    private final int id;
    private int admissionId;
    private int employeeId;
    private Date startTime;
    private Date endTime;

    public Allocation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
