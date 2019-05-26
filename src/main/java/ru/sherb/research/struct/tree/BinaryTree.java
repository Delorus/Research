package ru.sherb.research.struct.tree;

/**
 * @author maksim
 * @since 25.05.19
 */
public interface BinaryTree<T> {

    T value();

    BinaryTree<T> parent();
    BinaryTree<T> leftChild();
    BinaryTree<T> rightChild();
}
