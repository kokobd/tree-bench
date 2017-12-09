package com.github.zelinf.tree_bench.dictionary;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WordTest {
    @Test
    public void emptyConstructor() {
        Assert.assertTrue(new Word().isEmpty());
    }

    @Test
    public void wordValidation() {
        assertIsWord("hello");
        assertIsWord("ab-cd");
        assertIsWord("ab'cd");
        assertIsWord("a'b-cd");

        assertNotAWord("'abc");
        assertNotAWord("-abc");
        assertNotAWord("abc'");
        assertNotAWord("abc-");
        assertNotAWord("");
        assertNotAWord("--");
        assertNotAWord("a--bc");
        assertNotAWord("a-'bc");
        assertNotAWord("abc def");

        // Chinese
        assertIsWord("你好");
        assertNotAWord("你 好");
    }

    private void assertIsWord(String word) {
        Assert.assertFalse(new Word(word).isEmpty());
    }

    private void assertNotAWord(String word) {
        Assert.assertTrue(new Word(word).isEmpty());
    }

    @Test
    public void readWords() throws Exception {
        assertReadWordsAs("");
        assertReadWordsAs("a", "a");
        assertReadWordsAs("hi ", "hi");
        assertReadWordsAs("hello there", "hello", "there");
        assertReadWordsAs("hello' there'", "hello", "there");
        assertReadWordsAs("--hello there--", "hello", "there");
        assertReadWordsAs(" ''hello ' there''", "hello", "there");
        assertReadWordsAs("I'm Zelin-Feng", "I'm", "Zelin-Feng");
        assertReadWordsAs("-h-ello-'there ", "h-ello", "there");

        assertReadWordsAs("你-好--啊", "你-好", "啊");
    }

    private void assertReadWordsAs(String text, String... expectedWords) throws IOException {
        Reader reader = new CharArrayReader(text.toCharArray());
        List<String> actualWords = Word.readWords(reader).stream()
                .map(Word::toString)
                .collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList(expectedWords), actualWords);
    }

}