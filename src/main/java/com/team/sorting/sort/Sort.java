package com.team.sorting.sort;

import java.util.Comparator;
import java.util.List;

@FunctionalInterface
public interface Sort<E> {

    List<E> sort(List<E> list, Comparator<? super E> comparator);
}
