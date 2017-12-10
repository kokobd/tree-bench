package com.github.zelinf.tree_bench.dictionary.impl;

import com.github.zelinf.tree_bench.dictionary.Word;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

public class StandardTreeTest {

    private StandardTree tree;

    @Before
    public void setUp() throws Exception {
        tree = new StandardTree();
        tree.addThenLookup(new Word("hello"));
        tree.addThenLookup(new Word("zelin"));
        tree.addThenLookup(new Word("abc"));
    }

    @Test
    public void treeHeight() throws Exception {
        Assert.assertEquals(2, tree.treeHeight());
    }

    @Test
    public void deepestWord() throws Exception {
        Assert.assertEquals(Collections.emptyList(), tree.deepestWord());
    }

    @Test
    public void totalWords() throws Exception {
        Assert.assertEquals(3, tree.totalWords());
    }

}