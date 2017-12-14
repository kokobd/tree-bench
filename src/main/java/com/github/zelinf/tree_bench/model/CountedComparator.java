package com.github.zelinf.tree_bench.model;

import java.util.Comparator;
import java.util.Objects;

class CountedComparator<T> implements Comparator<T> {

    private int numberOfComparison = 0;

    public int getNumberOfComparison() {
        return numberOfComparison;
    }

    private Comparator<T> comparator;

    public CountedComparator(Comparator<T> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    public CountedComparator() {
        comparator = (Comparator<T>) Comparator.naturalOrder();
    }

    @Override
    public int compare(T o1, T o2) {
        numberOfComparison++;
        return comparator.compare(o1, o2);
    }
}
