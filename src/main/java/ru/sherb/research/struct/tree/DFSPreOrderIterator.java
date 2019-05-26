package ru.sherb.research.struct.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Pre-order стоит использовать именно тогда,
 * когда вы знаете что вам нужно проверить руты перед тем как проверять их листья.
 *
 * @author maksim
 * @since 25.05.19
 */
public final class DFSPreOrderIterator<T> implements Iterator<T> {

    private BinaryTree<T> cursor;
    private LinkedList<BinaryTree<T>> stack = new LinkedList<>();

    public DFSPreOrderIterator(BinaryTree<T> tree) {
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

        var value = cursor.value();
        if (cursor.rightChild() != null) {
            stack.push(cursor.rightChild());
        }
        cursor = cursor.leftChild();
        if (cursor == null && !stack.isEmpty()) {
            cursor = stack.pop();
        }
        return value;
    }
}
