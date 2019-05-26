package ru.sherb.research.struct.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * In-order обход используется когда нам надо проверять в начале детей
 * и только потом подыматься к родительским узлам.
 *
 * @author maksim
 * @since 25.05.19
 */
public final class DFSInOrderIterator<T> implements Iterator<T> {
    private BinaryTree<T> cursor;
    private LinkedList<BinaryTree<T>> stack = new LinkedList<>();

    private boolean fromLeft;

    public DFSInOrderIterator(BinaryTree<T> tree) {
        Objects.requireNonNull(tree);
        this.cursor = tree;
    }

    @Override
    public boolean hasNext() {
        return !(cursor == null && stack.isEmpty());
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        while (!fromLeft && cursor.leftChild() != null) {
            stack.push(cursor);
            cursor = cursor.leftChild();
        }
        fromLeft = false;

        var value = cursor.value();

        cursor = cursor.rightChild();
        if (cursor == null && !stack.isEmpty()) {
            cursor = stack.pop();
            fromLeft = true;
        }
        return value;
    }
}
