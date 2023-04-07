package utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void stringToDateMinTime() {
        LocalDateTime expected = LocalDateTime.of(2022, Calendar.DECEMBER, 1, 0, 0, 0);
        LocalDateTime actual = DateUtil.StringToDate("2022-11-01T00:00:00");
        assertEquals(expected, actual);
    }

    @Test
    void stringToDateMaxTime() {
        LocalDateTime expected = LocalDateTime.of(2022, Calendar.DECEMBER, 1, 23, 59, 59);
        LocalDateTime actual = DateUtil.StringToDate("2022-11-01T23:59:59");
        assertEquals(expected, actual);
    }

    @Test
    void stringToDate2022MayFirst() {
        LocalDateTime expected = LocalDateTime.of(2022, Calendar.MAY, 1, 22, 13, 22);
        LocalDateTime actual = DateUtil.StringToDate("2022-04-01T22:13:22");
        assertEquals(expected, actual);
    }

}