package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;

class SimpleBinaryTree<E> extends BinaryTree<E> {

    SimpleBinaryTree(Comparator<? super E> comparator) {
        super(comparator);
    }

    SimpleBinaryTree() {
        super();
    }

    @Override
    String getName() {
        return "Simple BinaryTree";
    }

    private int size;

    @Override
    int size() {
        return size;
    }

    @Override
    E putOrUpdate(E elem, BiFunction<? super E, ? super E, ? extends E> remapping) {
        Objects.requireNonNull(elem);
        Objects.requireNonNull(remapping);

        if (getRoot() == null) {
            setRoot(new Node<>(remapping.apply(elem, null)));
        } else {
            // TODO actually insert the element.
        }

        return null;
    }

    static class Node<E> extends BinaryTree.Node<E> {
        Node(E data) {
            super(data);
        }
    }
}
