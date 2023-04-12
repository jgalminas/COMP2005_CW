package api.models;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Allocation {
    private final int id;
    @SerializedName("admissionID")
    private final int admissionId;
    @SerializedName("employeeID")
    private final int employeeId;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Allocation(int id, int admissionId, int employeeId, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.admissionId = admissionId;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

}
