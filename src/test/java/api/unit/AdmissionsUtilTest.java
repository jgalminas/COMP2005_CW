package api.unit;

import api.utils.AdmissionsUtil;
import api.utils.DateUtil;
import test_data.Data;
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
    void testFilterByAllocations_AdmissionsAndAllocationsEmpty() {

        // arrange
        List<Admission> admissions = Collections.emptyList();
        List<Allocation> allocations = Collections.emptyList();
        List<Admission> expected = Collections.emptyList();

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocations_AdmissionsIsNullAllocationEmpty() {

        // arrange
        List<Admission> admissions = null;
        List<Allocation> allocations = Collections.emptyList();
        List<Admission> expected = Collections.emptyList();

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocations_AllocationsIsNullAdmissionEmpty() {

        // arrange
        List<Admission> admissions = Collections.emptyList();
        List<Allocation> allocations = null;
        List<Admission> expected = Collections.emptyList();

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // arrange
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocations_AllocationsAndAdmissionsIsNull() {

        // arrange
        List<Admission> admissions = null;
        List<Allocation> allocations = null;
        List<Admission> expected = Collections.emptyList();

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocations_AdmissionsEmpty() {

        // arrange
        List<Admission> admissions = Collections.emptyList();
        List<Allocation> allocations = new ArrayList<>(Data.ALLOCATIONS);
        List<Admission> expected = Collections.emptyList();

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocations_AllocationsEmpty() {

        // arrange
        List<Admission> admissions = new ArrayList<>(Data.ADMISSIONS);
        List<Allocation> allocations = Collections.emptyList();
        List<Admission> expected = Collections.emptyList();

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testFilterByAllocations_AllocationsAndAdmissionsNotEmpty() {

        // arrange
        List<Admission> admissions = new ArrayList<>(Data.ADMISSIONS);
        List<Allocation> allocations = new ArrayList<>(Data.ALLOCATIONS);

        List<Admission> expected = Arrays.asList(
                new Admission(1, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00"), 2),
                new Admission(3, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-27T09:56:00"), 2)
        );

        // act
        List<Admission> actual = AdmissionsUtil.filterByAllocations(admissions, allocations);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Admission::getId).toArray(),
                actual.stream().mapToInt(Admission::getId).toArray()
        );
    }

    @Test
    void testGetAdmissionDuration_SameDates() {

        // arrange
        Admission admission = new Admission(
                3,
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                2
        );

        long expected = 0;

        // act
        long actual = AdmissionsUtil.getAdmissionDuration(admission).toMinutes();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void testGetAdmissionDurationEarlierDate() {

        // arrange
        Admission admission = new Admission(
                3,
                DateUtil.StringToDate("2021-09-23T12:00:00"),
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                2
        );

        long expected = -600;

        // act
        long actual = AdmissionsUtil.getAdmissionDuration(admission).toMinutes();

        // assert
        assertEquals(expected, actual);
    }

    @Test
    void testGetAdmissionDuration_LaterDate() {

        // arrange
        Admission admission = new Admission(
                3,
                DateUtil.StringToDate("2021-09-23T02:00:00"),
                DateUtil.StringToDate("2021-09-23T12:10:20"),
                2
        );

        long expected = 610;

        // act
        long actual = AdmissionsUtil.getAdmissionDuration(admission).toMinutes();

        // assert
        assertEquals(expected, actual);
    }

}