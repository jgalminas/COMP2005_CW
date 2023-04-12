package api.utils;

import api.models.Allocation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllocationsUtil {

    public static List<Allocation> filterByEmployeeId(int id, List<Allocation> allocations) {

        Map<Integer, Allocation> filtered = new HashMap<>();

        if (allocations != null) {

            for (Allocation alloc : allocations) {
                if (alloc.getEmployeeId() == id) {
                    filtered.put(alloc.getId(), alloc);
                }
            }

        }

        return new ArrayList<>(filtered.values());
    }

}
