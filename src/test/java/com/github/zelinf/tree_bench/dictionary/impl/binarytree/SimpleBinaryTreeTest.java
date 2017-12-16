package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import com.github.zelinf.tree_bench.dictionary.TreeDictionary;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BiFunction;

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
    public void computeTest() {
        BiFunction<String, Integer, Integer> addOne = (k, v) -> v == null ? 1 : v + 1;
        dictionary.compute("hello", addOne);
        dictionary.compute("hi", addOne);
        Assert.assertEquals(Integer.valueOf(1), dictionary.get("hello"));
        Assert.assertEquals(Integer.valueOf(1), dictionary.get("hi"));
    }

    @Test
    public void sizeAndHeightTest() {
        Assert.assertEquals(0, dictionary.size());
        Assert.assertEquals(0, dictionary.height());
        dictionary.put("a", 1);
        Assert.assertEquals(1, dictionary.size());
        Assert.assertEquals(1, dictionary.height());
        dictionary.put("b", 3);
        Assert.assertEquals(2, dictionary.size());
        Assert.assertEquals(2, dictionary.height());
        dictionary.put("a", 0);
        Assert.assertEquals(2, dictionary.size());

        dictionary.put("c", 2);
        Assert.assertEquals(3, dictionary.height());
    }

    @Test
    public void iteratorTest() {
        dictionary.put("b", 1);
        dictionary.put("a", 2);
        dictionary.put("c", 3);

        Iterator<Map.Entry<String, Integer>> iterator = dictionary.iterator();
        Assert.assertEquals("a", iterator.next().getKey());
        Assert.assertEquals("b", iterator.next().getKey());
        Assert.assertEquals("c", iterator.next().getKey());
    }
}