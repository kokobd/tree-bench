package com.github.zelinf.tree_bench.dictionary;

public final class TreeDictionaryFactory {

    @SuppressWarnings("unchecked")
    public static <K, V> TreeDictionary<K, V> newInstance(TreeDictionary.Type type) {
        TreeDictionary instance;
        try {
            instance = type.getImplementation().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return (TreeDictionary<K, V>) instance;
    }

}
