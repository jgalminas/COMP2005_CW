package utils;

import data.Static;
import models.Admission;
import models.Allocation;
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

}