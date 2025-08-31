package com.team.sorting.generator;

import java.util.List;

public interface EntityGenerator<T> {
    List<T> generate(int count);
}
