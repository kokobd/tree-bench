package com.github.zelinf.tree_bench.dictionary;

/**
 * A word, obeying the following specification:
 * <blockquote>
 * A word is defined as a sequence of letters, together with
 * apostrophes (’) and hyphens (-), provided that the apostrophe or
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
    private static final int MAX_LENGTH = 20;

    public Word(String string) {
        if (checkIsWord(string)) {
            word = string;
            if (word.length() > MAX_LENGTH) {
                word = word.substring(0, MAX_LENGTH);
            }
        }
    }

    private boolean checkIsWord(String string) {
        // TODO check whether 'string' is a valid word
        return false;
    }

    @Override
    public int compareTo(Word o) {
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
}
