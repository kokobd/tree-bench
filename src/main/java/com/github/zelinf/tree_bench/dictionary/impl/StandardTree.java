package com.github.zelinf.tree_bench.dictionary.impl;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;

import java.util.*;
import java.util.function.BiFunction;

public class StandardTree<K, V> implements TreeDictionary<K, V> {

    private TreeMap<K, V> treeMap;

    public StandardTree() {
        treeMap = new TreeMap<>();
    }

    @Override
    public int height() {
        return (int) (Math.log(treeMap.size() + 1) / Math.log(2));
    }

    @Override
    public List<Map.Entry<K, V>> deepestEntries() {
        return Collections.emptyList();
    }

    @Override
    public Type getType() {
        return Type.STANDARD;
    }

    @Override
    public void setComparator(Comparator<? super K> comparator) {
        Objects.requireNonNull(comparator);
        if (size() != 0) {
            throw new IllegalStateException("Comparator may be set only when the tree is empty");
        }
        treeMap = new TreeMap<>(comparator);
    }

    @Override
    public Comparator<? super K> getComparator() {
        return treeMap.comparator();
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return treeMap.compute(key, remappingFunction);
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return treeMap.entrySet().iterator();
    }
}
