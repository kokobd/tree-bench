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
    void clear() {
        super.clear();
        size = 0;
    }

    @Override
    E putOrUpdate(E elem, BiFunction<? super E, ? super E, ? extends E> remapping) {
        Objects.requireNonNull(elem);
        Objects.requireNonNull(remapping);

        if (getRoot() == null) {
            E newData = remapping.apply(elem, null);
            setRoot(new Node<>(newData));
            size++;
            return newData;
        }
        return putOrUpdateNode(getRoot(), elem, remapping).getData();
    }

    // Assume root is never null
    private BinaryTree.Node<E> putOrUpdateNode(BinaryTree.Node<E> root, E elem,
                                               BiFunction<? super E, ? super E, ? extends E> remapping) {
        BinaryTree.Node<E> updatedNode;
        int compResult = getComparator().compare(elem, root.getData());
        if (compResult < 0) {
            if (root.getLeft() == null) {
                updatedNode = new Node<>(remapping.apply(elem, null));
                root.setLeft(updatedNode);
                size++;
            } else {
                updatedNode = putOrUpdateNode(root.getLeft(), elem, remapping);
            }
        } else if (compResult > 0) {
            if (root.getRight() == null) {
                updatedNode = new Node<>(remapping.apply(elem, null));
                root.setRight(updatedNode);
                size++;
            } else {
                updatedNode = putOrUpdateNode(root.getRight(), elem, remapping);
            }
        } else {
            E newData = remapping.apply(elem, root.getData());
            root.setData(newData);
            updatedNode = root;
        }

        return updatedNode;
    }

    static class Node<E> extends BinaryTree.Node<E> {
        Node(E data) {
            super(data);
        }
    }
}
