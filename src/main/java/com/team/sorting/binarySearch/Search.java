package com.team.sorting.sortStrategies;

import java.util.Comparator;
import java.util.List;

@FunctionalInterface
public interface Search<T extends Comparable<T>> {

    int search(List<T> list, T key);
}