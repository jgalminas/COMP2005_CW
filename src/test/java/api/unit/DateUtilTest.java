package api.unit;

import api.utils.DateUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void testStringToDate_MinTime() {

        // arrange
        LocalDateTime expected = LocalDateTime.of(2022, Calendar.DECEMBER, 1, 0, 0, 0);

        // act
        LocalDateTime actual = DateUtil.StringToDate("2022-11-01T00:00:00");

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void testStringToDate_MaxTime() {

        // arrange
        LocalDateTime expected = LocalDateTime.of(2022, Calendar.DECEMBER, 1, 23, 59, 59);

        // act
        LocalDateTime actual = DateUtil.StringToDate("2022-11-01T23:59:59");

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void testStringToDate_2022MayFirst() {

        // arrange
        LocalDateTime expected = LocalDateTime.of(2022, Calendar.MAY, 1, 22, 13, 22);

        // act
        LocalDateTime actual = DateUtil.StringToDate("2022-04-01T22:13:22");

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void testStringToDate_Null() {

        // arrange
        LocalDateTime expected = LocalDateTime.MIN;

        // act
        LocalDateTime actual = DateUtil.StringToDate(null);

        // assert
        assertEquals(expected, actual);
    }

}