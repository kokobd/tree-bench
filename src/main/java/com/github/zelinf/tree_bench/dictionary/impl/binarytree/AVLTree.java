package com.github.zelinf.tree_bench.dictionary.impl.binarytree;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BiFunction;

class AVLTree<E> extends BinaryTree<E> {
    @Override
    String getName() {
        return "AVL Tree";
    }

    @Override
    int size() {
        return size;
    }

    private int size;

    @Override
    void clear() {
        size = 0;
    }

    @Override
    E putOrUpdate(E elem, BiFunction<? super E, ? super E, ? extends E> remapping) {
        Objects.requireNonNull(elem);
        Objects.requireNonNull(remapping);

        AVLNode<E> newRoot = putOrUpdateNode(getRoot(), elem, remapping);
        setRoot(newRoot);
        return newRoot.getData();
    }

    private AVLNode<E> putOrUpdateNode(AVLNode<E> node, E elem, BiFunction<? super E, ? super E, ? extends E> remapping) {
        if (node == null) {
            ++size;
            return new AVLNode<>(remapping.apply(elem, null));
        }
        Comparator<? super E> comparator = getComparator();
        {
            int compResult = comparator.compare(elem, node.getData());
            if (compResult < 0) {
                node.setLeft(putOrUpdateNode(node.getLeft(), elem, remapping));
            } else if (compResult > 0) {
                node.setRight(putOrUpdateNode(node.getRight(), elem, remapping));
            } else {
                node.setData(remapping.apply(elem, node.getData()));
                return node;
            }
        }

        node.setHeight(1 + Integer.max(heightOfNode(node.getLeft()), heightOfNode(node.getRight())));
        int bf = getBalance(node);

        if (bf > 1) {
            int compWithLeft = comparator.compare(elem, node.getLeft().getData());
            if (compWithLeft < 0) {
                return rightRotate(node);
            } else if (compWithLeft > 0) {
                node.setLeft(leftRotate(node.getLeft()));
                return rightRotate(node);
            }
        } else if (bf < -1) {
            int compWithRight = comparator.compare(elem, node.getRight().getData());
            if (compWithRight > 0) {
                return leftRotate(node);
            } else if (compWithRight < 0) {
                node.setRight(rightRotate(node.getRight()));
                return leftRotate(node);
            }
        }

        return node;
    }

    @Override
    AVLNode<E> getRoot() {
        return (AVLNode<E>) super.getRoot();
    }

    private int heightOfNode(AVLNode<E> node) {
        if (node == null) {
            return 0;
        }
        return node.getHeight();
    }

    private AVLNode<E> rightRotate(AVLNode<E> node) {
        AVLNode<E> x = node.getLeft();
        AVLNode<E> t2 = x.getRight();

        x.setRight(node);
        node.setLeft(t2);

        node.height = Integer.max(heightOfNode(node.getLeft()), heightOfNode(node.getRight()) + 1);
        x.height = Integer.max(heightOfNode(x.getLeft()), heightOfNode(x.getRight()) + 1);

        return x;
    }

    private AVLNode<E> leftRotate(AVLNode<E> x) {
        AVLNode<E> y = x.getRight();
        AVLNode<E> t2 = y.getLeft();

        y.setLeft(x);
        x.setRight(t2);

        x.height = Integer.max(heightOfNode(x.getLeft()), heightOfNode(x.getRight()) + 1);
        y.height = Integer.max(heightOfNode(y.getLeft()), heightOfNode(y.getRight()) + 1);

        //noinspection SuspiciousNameCombination
        return y;
    }

    private int getBalance(AVLNode<E> node) {
        if (node == null) {
            return 0;
        }

        return heightOfNode(node.getLeft()) - heightOfNode(node.getRight());
    }

    private static class AVLNode<E> extends Node<E> {
        AVLNode(E data) {
            super(data);
        }

        AVLNode() {
            super();
        }

        int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        AVLNode<E> getLeft() {
            return (AVLNode<E>) super.getLeft();
        }

        @Override
        AVLNode<E> getRight() {
            return (AVLNode<E>) super.getRight();
        }
    }
}
