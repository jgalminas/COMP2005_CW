package api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    /**
     * Converts an ISO date string to a LocalDateTime object
     * @param dateString ISO date string
     * @return LocalDateTime object
     */
    public static LocalDateTime StringToDate(String dateString) {
        if (dateString != null) {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
        } else {
            return LocalDateTime.MIN;
        }
    }

}
