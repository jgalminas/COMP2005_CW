package api.unit;

import api.utils.AllocationsUtil;
import api.utils.DateUtil;
import test_data.Data;
import api.models.Allocation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AllocationsUtilTest {

    @Test
    void testFilterByEmployeeById_Negative() {

        // arrange
        int id = -2;
        List<Allocation> expected = Collections.emptyList();

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Data.ALLOCATIONS);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeId_WhenAllocationsIsNull() {

        // arrange
        int id = -2;
        List<Allocation> expected = Collections.emptyList();

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, null);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeId_ValidIdButNoResults() {

        // arrange
        int id = 2;
        List<Allocation> expected = Collections.emptyList();

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Data.ALLOCATIONS);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeById_EmptyAllocationsList() {

        // arrange
        int id = 4;
        List<Allocation> expected = Collections.emptyList();

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Collections.emptyList());

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeById_ValidIdWithResults() {

        // arrange
        int id = 4;
        List<Allocation> expected = Arrays.asList(
                new Allocation(1, 1, 4, DateUtil.StringToDate("2020-11-28T16:45:00"), DateUtil.StringToDate("2020-11-28T23:56:00")),
                new Allocation(2, 3, 4, DateUtil.StringToDate("2021-09-23T21:50:00"), DateUtil.StringToDate("2021-09-24T09:50:00"))
        );

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Data.ALLOCATIONS);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeId_MaxIntValue() {

        // arrange
        int id = Integer.MAX_VALUE;
        List<Allocation> expected = Collections.emptyList();

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Data.ALLOCATIONS);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

    @Test
    void testFilterByEmployeeId_MinIntValue() {

        // arrange
        int id = Integer.MIN_VALUE;
        List<Allocation> expected = Collections.emptyList();

        // act
        List<Allocation> actual = AllocationsUtil.filterByEmployeeId(id, Data.ALLOCATIONS);

        // assert
        assertArrayEquals(
                expected.stream().mapToInt(Allocation::getId).toArray(),
                actual.stream().mapToInt(Allocation::getId).toArray()
        );
    }

}