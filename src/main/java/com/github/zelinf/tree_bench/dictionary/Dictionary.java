package com.github.zelinf.tree_bench.dictionary;

import java.util.Map;
import java.util.function.BiFunction;

public interface Dictionary<K, V> extends Iterable<Map.Entry<K, V>> {

    V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction);

    int size();

    default V put(K key, V value) {
        return compute(key, (k, v) -> value);
    }

    default V get(K key) {
        return compute(key, (k, v) -> v);
    }

    void clear();
}
