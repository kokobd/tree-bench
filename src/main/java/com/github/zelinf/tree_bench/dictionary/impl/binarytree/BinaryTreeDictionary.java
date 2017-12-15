package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import com.github.zelinf.tree_bench.dictionary.impl.Entry;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Decorator class. Encapsulates a {@link BinaryTree} as a {@link TreeDictionary}
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public class BinaryTreeDictionary<K, V> implements TreeDictionary<K, V> {

    private BinaryTree<Map.Entry<K, V>> tree;

    private Comparator<? super K> keyComparator;

    BinaryTreeDictionary(BinaryTree<Map.Entry<K, V>> tree) {
        this.tree = tree;
    }

    @Override
    public int height() {
        return heightOfTree(tree.getRoot());
    }

    private static int heightOfTree(BinaryTree.Node<?> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = heightOfTree(root.getLeft());
        int rightHeight = heightOfTree(root.getRight());
        return Integer.max(leftHeight, rightHeight);
    }

    @Override
    public List<Map.Entry<K, V>> deepestEntries() {
        BinaryTree.Node<Map.Entry<K, V>> root = tree.getRoot();
        List<BinaryTree.Node<Map.Entry<K, V>>> currentLevel = new ArrayList<>();
        if (root != null)
            currentLevel.add(root);

        List<Map.Entry<K, V>> deepestEntries = new ArrayList<>();
        while (currentLevel.size() != 0) {
            List<BinaryTree.Node<Map.Entry<K, V>>> nextLevel = new ArrayList<>();
            for (BinaryTree.Node<Map.Entry<K, V>> node : currentLevel) {
                if (node.getLeft() != null) {
                    nextLevel.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    nextLevel.add(node.getRight());
                }
            }
            if (nextLevel.size() == 0) {
                deepestEntries = currentLevel.stream()
                        .map(BinaryTree.Node::getData)
                        .collect(Collectors.toList());
            }
            currentLevel = nextLevel;
        }

        return deepestEntries;
    }

    @Override
    public String getName() {
        return tree.getName();
    }

    @Override
    public void setComparator(Comparator<? super K> comparator) {
        keyComparator = comparator;
        tree.setComparator((e1, e2) -> comparator.compare(e1.getKey(), e2.getKey()));
    }

    @Override
    public Comparator<? super K> getComparator() {
        if (keyComparator == null) {
            keyComparator = (k1, k2) ->
                    tree.getComparator().compare(new NullValueEntry<>(k1),
                            new NullValueEntry<>(k2));
        }

        return keyComparator;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return tree.putOrUpdate(new NullValueEntry<>(key), (newEntry, oldEntry) -> {
            K theKey = newEntry.getKey();
            return new Entry<>(theKey, remappingFunction.apply(theKey, oldEntry.getValue()));
        }).getValue();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return tree.inorderIterator();
    }

    private static class NullValueEntry<K, V> implements Map.Entry<K, V> {
        private K key;

        NullValueEntry(K key) {
            this.key = key;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return null;
        }

        @Override
        public V setValue(V value) {
            return null;
        }
    }
}
