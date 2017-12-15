package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimpleBinaryTreeTest {

    private TreeDictionary<String, Integer> dictionary;

    @Before
    public void setUp() throws Exception {
        dictionary = BinaryTreeDictionaryFactory.createBinaryTree(BinaryTreeDictionaryFactory.Type.SIMPLE);
    }

    @Test
    public void putTest() {
        Assert.assertEquals(Integer.valueOf(12), dictionary.put("hello", 12));
        Assert.assertEquals(Integer.valueOf(3), dictionary.put("a", 3));
        Assert.assertEquals(Integer.valueOf(1), dictionary.put("hello", 1));

        Assert.assertEquals(Integer.valueOf(1), dictionary.get("hello"));
        Assert.assertEquals(Integer.valueOf(3), dictionary.get("a"));
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(0, dictionary.size());
        dictionary.put("a", 1);
        Assert.assertEquals(1, dictionary.size());
        dictionary.put("b", 3);
        Assert.assertEquals(2, dictionary.size());
        dictionary.put("a", 0);
        Assert.assertEquals(2, dictionary.size());
    }
}