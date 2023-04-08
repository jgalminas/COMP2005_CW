package utils;

import data.Static;
import models.Allocation;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AllocationsUtilTest {

    @Test
    void testFilterByEmployeeByNegativeId() {
        int id = -2;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeWhenAllocationsIsNull() {
        int id = -2;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, null);

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeByValidIdButNoResults() {
        int id = 2;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeByIdEmptyAllocationsList() {
        int id = 4;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Collections.emptyList());

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeByValidIdWithResults() {
        int id = 4;
        List<Allocation> expected = Arrays.asList(
                new Allocation(1, 1, 4, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00")),
                new Allocation(2, 3, 4, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-24T09:50:00"))
        );

        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeIdMaxValue() {
        int id = Integer.MAX_VALUE;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeIdMinValue() {
        int id = Integer.MIN_VALUE;
        List<Allocation> expected = Collections.emptyList();
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Static.ALLOCATIONS);

        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

}