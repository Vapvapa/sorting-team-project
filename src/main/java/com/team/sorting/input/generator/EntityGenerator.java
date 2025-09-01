package com.team.sorting.input.generator;

import java.util.List;

public interface EntityGenerator<T> {
    List<T> generate(int count);
}
