package com.github.zelinf.tree_bench.dictionary;

import com.github.zelinf.tree_bench.dictionary.impl.StandardTree;
import com.github.zelinf.tree_bench.dictionary.impl.binarytree.BinaryTreeDictionaryFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class TreeDictionaryFactory {

    public static <K, V> List<TreeDictionary<K, V>> allDictionaries() {
        List<TreeDictionary<K, V>> result = new ArrayList<>();
        result.add(new StandardTree<>());

        for (BinaryTreeDictionaryFactory.Type type : BinaryTreeDictionaryFactory.Type.values()) {
            result.add(BinaryTreeDictionaryFactory.createBinaryTree(type));
        }

        return result;
    }

}
