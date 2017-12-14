package com.github.zelinf.tree_bench.dictionary.impl;

import com.github.zelinf.tree_bench.dictionary.Word;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class StandardTreeTest {

    private StandardTree<String, Integer> tree = new StandardTree<>();

    @Test
    public void putGetSize() throws Exception {
        Assert.assertEquals(Integer.valueOf(12), tree.put("Hello", 12));
        Assert.assertEquals(Integer.valueOf(13), tree.put("Hello", 13));

        Assert.assertEquals(1, tree.size());
        Assert.assertEquals(Integer.valueOf(13), tree.get("Hello"));
    }

    @Test
    public void treeHeight() throws Exception {
        Assert.assertEquals(0, tree.height());

        tree.put("1", 2);
        Assert.assertEquals(1, tree.height());
    }

    @Test
    public void deepestWord() throws Exception {
        Assert.assertEquals(Collections.emptyList(), tree.deepestEntries());
    }
}