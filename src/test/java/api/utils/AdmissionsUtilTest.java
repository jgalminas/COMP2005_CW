package api.utils;

import data.Static;
import api.models.Admission;
import api.models.Allocation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdmissionsUtilTest {

    @Test
    void testFilterByAllocationsAdmissionsAndAllocationsEmpty() {

        List<Admission> admissions = Collections.emptyList();
        List<Allocation> allocations = Collections.emptyList();

        List<Admission> expected = Collections.emptyList();
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocationsAdmissionsIsNull() {

        List<Admission> admissions = null;
        List<Allocation> allocations = Collections.emptyList();

        List<Admission> expected = Collections.emptyList();
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocationsAllocationsIsNull() {

        List<Admission> admissions = Collections.emptyList();
        List<Allocation> allocations = null;

        List<Admission> expected = Collections.emptyList();
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocationsAllocationsAndAdmissionsIsNull() {

        List<Admission> admissions = null;
        List<Allocation> allocations = null;

        List<Admission> expected = Collections.emptyList();
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocationsAdmissionsEmpty() {

        List<Admission> admissions = Collections.emptyList();
        List<Allocation> allocations = new ArrayList<>(Static.ALLOCATIONS);

        List<Admission> expected = Collections.emptyList();
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocationsAllocationsEmpty() {

        List<Admission> admissions = new ArrayList<>(Static.ADMISSIONS);
        List<Allocation> allocations = Collections.emptyList();

        List<Admission> expected = Collections.emptyList();
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocationsAllocationsAndAdmissionsNotEmpty() {

        List<Admission> admissions = new ArrayList<>(Static.ADMISSIONS);
        List<Allocation> allocations = new ArrayList<>(Static.ALLOCATIONS);

        List<Admission> expected = Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00"), 2),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
        );
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testGetAdmissionDurationSameDates() {
        Admission admission = new Admission(
                3,
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                2
        );

        long expected = 0;
        long actual = AdmissionsUtil.getAdmissionDuration(admission).toMinutes();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAdmissionDurationEarlierDate() {
        Admission admission = new Admission(
                3,
                DateUtil.StringToDate("2021-09-23T12:00:00"),
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                2
        );

        long expected = -600;
        long actual = AdmissionsUtil.getAdmissionDuration(admission).toMinutes();

        assertEquals(expected, actual);
    }

    @Test
    void testGetAdmissionDurationLaterDate() {
        Admission admission = new Admission(
                3,
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                DateUtil.StringToDate("2021-09-23T12:10:20"),
                2
        );

        long expected = 610;
        long actual = AdmissionsUtil.getAdmissionDuration(admission).toMinutes();

        assertEquals(expected, actual);
    }

}