package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import java.util.Map;
import java.util.Objects;

public final class BinaryTreeDictionaryFactory {

    public enum Type {
        SIMPLE
    }

    public static <K, V> BinaryTreeDictionary<K, V> createBinaryTree(Type type) {
        Objects.requireNonNull(type);

        BinaryTree<Map.Entry<K, V>> tree;
        switch (type) {
            case SIMPLE:
                tree = new SimpleBinaryTree<>();
                break;
            default:
                tree = null;
                break;
        }
        return new BinaryTreeDictionary<>(tree);
    }
}
