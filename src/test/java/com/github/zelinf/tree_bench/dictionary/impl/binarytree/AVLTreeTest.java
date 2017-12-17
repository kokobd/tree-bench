package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

    private BinaryTreeDictionary<String, Integer> avlTree;

    @Before
    public void setUp() {
        avlTree = BinaryTreeDictionaryFactory.createBinaryTree(BinaryTreeDictionaryFactory.Type.AVL);
    }

    @Test
    public void putAndHeight() {
        avlTree.put("a", 1);
        avlTree.put("b", 2);
        avlTree.put("c", 1);
        Assert.assertEquals(2, avlTree.height());
    }
}
