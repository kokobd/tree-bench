package com.github.zelinf.tree_bench.dictionary;

import com.github.zelinf.tree_bench.dictionary.util.CodePointReader;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * A word, obeying the following specification:
 * <blockquote>
 * A word is defined as a sequence of letters, together with
 * apostrophes (') and hyphens (-), provided that the apostrophe or
 * hyphen is both immediately preceded and followed by a letter.
 * Uppercase and lowercase letters should be regarded as the same
 * (by translating all letters into either uppercase or lowercase,
 * as you prefer). A word is to be truncated to its first 20
 * characters (that is, only 20 characters are to be stored in the
 * data structure) but words longer than 20 characters may appear
 * in the text. Non-alphabetic characters (such as digits, blanks,
 * punctuation marks, control characters) may appear in the text file.
 * The appearance of any of these terminates a word, and the next
 * word begins only when a letter appears.
 * </blockquote>
 */
public final class Word implements Comparable<Word> {

    private String word = "";

    public Word() {
    }

    public Word(String string) {
        if (checkIsWord(string)) {
            word = string;
        }
    }

    public boolean isEmpty() {
        return word.equals("");
    }

    private boolean checkIsWord(String string) {
        int length = string.codePointCount(0, string.length());
        int previousCodePoint = ' ';
        for (int i = 0; i < length; i++) {

            int currentCodePoint = string.codePointAt(
                    string.offsetByCodePoints(0, i));

            if (!Character.isAlphabetic(currentCodePoint)) { // not a letter
                if ((currentCodePoint == '\'' || currentCodePoint == '-')
                        && Character.isAlphabetic(previousCodePoint)) {
                    if (i == length - 1
                            || !Character.isAlphabetic(
                            string.codePointAt(
                                    string.offsetByCodePoints(0, i + 1)))) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            previousCodePoint = currentCodePoint;
        }

        return true;
    }

    public void setOnCompare(BiConsumer<Word, Word> biConsumer) {
        onCompare = biConsumer;
    }

    // no-op by default
    private BiConsumer<Word, Word> onCompare = (a, b) -> {
    };

    @Override
    public int compareTo(Word o) {
        onCompare.accept(this, o);
        return word.compareToIgnoreCase(o.word);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        return word.equalsIgnoreCase(word1.word);
    }

    @Override
    public int hashCode() {
        return word.toUpperCase().hashCode();
    }

    @Override
    public String toString() {
        return word;
    }

    private static Word newWordUnchecked(String word) {
        Word result = new Word();
        result.word = word;
        return result;
    }

    public static List<Word> readWords(Reader reader) throws IOException {
        CodePointReader codePointReader = null;
        List<Word> words = new ArrayList<>();
        try {
            codePointReader = new CodePointReader(reader);

            StringBuilder currentWord = null;
            boolean inWord = false;

            int currentCodePoint;
            int nextCodePoint = -1;
            boolean hasNext = false;
            if (codePointReader.hasNext()) {
                nextCodePoint = codePointReader.nextCodePoint();
                hasNext = true;
            }

            while (hasNext) {
                currentCodePoint = nextCodePoint;
                if (codePointReader.hasNext()) {
                    nextCodePoint = codePointReader.nextCodePoint();
                } else {
                    hasNext = false;
                }

                if (inWord) {
                    if (Character.isAlphabetic(currentCodePoint) ||
                            ((currentCodePoint == '-' || currentCodePoint == '\'') &&
                                    Character.isAlphabetic(nextCodePoint))) {
                        currentWord.appendCodePoint(currentCodePoint);
                    } else {
                        words.add(newWordUnchecked(currentWord.toString()));
                        currentWord = null;
                        inWord = false;
                    }
                } else { // not in word
                    if (Character.isAlphabetic(currentCodePoint)) {
                        inWord = true;
                        currentWord = new StringBuilder();
                        currentWord.appendCodePoint(currentCodePoint);
                    }
                }
            }

            if (currentWord != null) {
                words.add(newWordUnchecked(currentWord.toString()));
            }

        } finally {
            if (codePointReader != null)
                codePointReader.close();
        }

        return words;
    }
}
