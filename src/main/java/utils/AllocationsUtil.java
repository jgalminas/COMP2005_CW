package utils;

import models.Allocation;
import java.util.ArrayList;
import java.util.List;

public class AllocationsUtil {

    public static List<Allocation> filterByEmployeeId(int id, List<Allocation> allocations) {

        List<Allocation> filtered = new ArrayList<>();

        if (allocations != null) {

            for (Allocation alloc : allocations) {
                if (alloc.getEmployeeId() == id) {
                    filtered.add(alloc);
                }
            }

        }

        return filtered;
    }

}
