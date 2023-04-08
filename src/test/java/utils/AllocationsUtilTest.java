package utils;

import data.Static;
import models.Allocation;
import models.Patient;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AllocationsUtilTest {

    @Test
    void filterByEmployeeByNegativeId() {
        int id = -2;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeByValidIdButNoResults() {
        int id = 2;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeByIdEmptyAllocationsList() {
        int id = 4;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Collections.emptyList());
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeByValidIdWithResults() {
        int id = 4;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeIdMaxValue() {
        int id = Integer.MAX_VALUE;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeIdMinValue() {
        int id = Integer.MIN_VALUE;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeIdTooLargeNeg() {
        int id = Integer.MIN_VALUE - 100;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    void filterByEmployeeIdTooLargePos() {
        int id = Integer.MAX_VALUE + 100;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

}