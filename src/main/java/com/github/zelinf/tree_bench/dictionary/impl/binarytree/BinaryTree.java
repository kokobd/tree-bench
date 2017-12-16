package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiFunction;

abstract class BinaryTree<E> {

    static abstract class Node<E> {
        private Node<E> left;
        private Node<E> right;
        private E data;

        Node(Node<E> left, Node<E> right, E data) {
            this.left = left;
            this.right = right;
            this.data = data;
        }

        Node(E data) {
            this(null, null, data);
        }

        Node() {
            this(null);
        }

        Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }

        E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }
    }

    private Node<E> root;

    void setRoot(Node<E> root) {
        this.root = root;
    }

    Node<E> getRoot() {
        return root;
    }

    private Comparator<? super E> comparator;

    BinaryTree(Comparator<? super E> comparator) {
        Objects.requireNonNull(comparator);
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    BinaryTree() {
        comparator = (Comparator<? super E>) Comparator.naturalOrder();
    }

    void setComparator(Comparator<? super E> comparator) {
        if (root == null) {
            this.comparator = comparator;
        } else {
            throw new IllegalStateException("Comparator can not be set when the root is not null.");
        }
    }

    Comparator<? super E> getComparator() {
        return comparator;
    }

    abstract String getName();

    abstract int size();

    Iterator<E> inorderIterator() {
        return new InorderIterator<>(getRoot());
    }

    /**
     * Put elem into the tree. If an existing element which is equals to elem
     * is already in the tree, use the remapping function to create a new node
     * to replace it. Note that both arguments and the return value of remapping
     * function MUST equal (in the sense of comparator provided). Violation of
     * this restriction leads to undefined behavior.
     *
     * @param elem      the element to put or find
     * @param remapping the remapping function
     * @return the updated value
     */
    abstract E putOrUpdate(E elem, BiFunction<? super E, ? super E, ? extends E> remapping);

    void clear() {
        root = null;
    }
}
