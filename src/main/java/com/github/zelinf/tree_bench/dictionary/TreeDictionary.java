package com.github.zelinf.tree_bench.dictionary;

import com.github.zelinf.tree_bench.dictionary.impl.StandardTree;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Dictionaries implemented with tree should extend
 * this class.
 */
public interface TreeDictionary<K, V> extends Dictionary<K, V> {

    int height();

    List<Map.Entry<K, V>> deepestEntries();

    Type getType();

    /**
     * Set the key-comparator used by the tree.
     * Comparator may be set only when the tree is empty
     *
     * @param comparator the comparator used to compare key
     * @throws IllegalStateException if the tree is not empty
     */
    void setComparator(Comparator<? super K> comparator);

    Comparator<? super K> getComparator();

    enum Type {
        STANDARD {
            @Override
            public Class<? extends TreeDictionary> getImplementation() {
                return StandardTree.class;
            }
        };

        public abstract Class<? extends TreeDictionary> getImplementation();
    }

}
