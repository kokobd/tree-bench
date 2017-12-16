package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class InorderIterator<E> implements Iterator<E> {

    private Deque<BinaryTree.Node<E>> stack = new ArrayDeque<>();

    InorderIterator(BinaryTree.Node<E> root) {
        pushLeftNodes(root);
    }

    private void pushLeftNodes(BinaryTree.Node<E> current) {
        while (current != null) {
            stack.push(current);
            current = current.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Have reached the end of the tree");
        }

        BinaryTree.Node<E> top = stack.pop();
        pushLeftNodes(top.getRight());
        return top.getData();
    }

}
