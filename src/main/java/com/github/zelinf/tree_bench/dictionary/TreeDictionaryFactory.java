package com.github.zelinf.tree_bench.dictionary;

import com.github.zelinf.tree_bench.dictionary.impl.binarytree.BinaryTreeDictionaryFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class TreeDictionaryFactory {

    public static <K, V> List<TreeDictionary<K, V>> allDictionaries() {
        return Arrays.stream(BinaryTreeDictionaryFactory.Type.values())
                .map(BinaryTreeDictionaryFactory::<K, V>createBinaryTree)
                .collect(Collectors.toList());
    }

}
