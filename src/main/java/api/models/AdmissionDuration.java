package api.models;

import java.util.concurrent.TimeUnit;

public class AdmissionDuration {

    private final String unit = TimeUnit.MINUTES.toString();
    private final long amount;

    public AdmissionDuration(long amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public long getAmount() {
        return amount;
    }
}
