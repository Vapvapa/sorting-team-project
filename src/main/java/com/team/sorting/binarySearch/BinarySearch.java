package com.team.sorting.sortStrategies;

import java.util.List;

public class BinarySearch<T extends Comparable<T>> {

    public int search(List<T> sortedList, T key) {
        int low = 0;
        int high = sortedList.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = sortedList.get(mid).compareTo(key);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}