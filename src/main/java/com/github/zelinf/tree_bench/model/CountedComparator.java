package com.github.zelinf.tree_bench.model;

import java.util.Comparator;
import java.util.Objects;

class CountedComparator<T> implements Comparator<T> {

    private int numberOfComparison = 0;

    public int getNumberOfComparison() {
        return numberOfComparison;
    }

    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public CountedComparator(Comparator<T> comparator) {
        if (comparator == null) {
            comparator = (Comparator<T>) Comparator.naturalOrder();
        }
        this.comparator = comparator;
    }

    @Override
    public int compare(T o1, T o2) {
        numberOfComparison++;
        return comparator.compare(o1, o2);
    }
}
