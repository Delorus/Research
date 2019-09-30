package ru.sherb.research.struct.tree;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

/**
 * Post-order обход используется когда нам нужно начать с листов
 * и завершить главным узлом — то есть разложить дерево на то, как оно строилось.
 *
 * @author maksim
 * @since 26.05.19
 */
public final class DFSPostOrderIterator<T> implements Iterator<T> {
    private BinaryTree<T> cursor;
    private LinkedList<BinaryTree<T>> stack = new LinkedList<>();
    private boolean fromLeft;
    private boolean fromRight;

    public DFSPostOrderIterator(BinaryTree<T> tree) {
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

        return next3();
    }

    //todo not working
    private T nextNotWork() {
        while (!fromLeft && cursor.leftChild() != null) {
            stack.push(cursor);
            cursor = cursor.leftChild();
            fromRight = false;
        }
        fromLeft = true;

        if (!fromRight && cursor.rightChild() != null) {
            stack.push(cursor);
            cursor = cursor.rightChild();
            fromLeft = false;
            var value = nextNotWork();

            fromRight = true;
            return value;
        }

        var value = cursor.value();
        cursor = stack.pop();
        if (stack.isEmpty()) {
            fromRight = false;
        }

        return value;
    }

    //todo not working
    private T next2() {
        while (!fromLeft && cursor.leftChild() != null) {
            stack.push(cursor);
            cursor = cursor.leftChild();
        }

        if (!fromRight && cursor.rightChild() != null) {
            stack.push(cursor);
            cursor = cursor.rightChild();
            fromLeft = false;
            var value = next2();
            fromRight = true;
            return value;
        }

        var value = cursor.value();
        fromLeft = true;
        if (!stack.isEmpty()) {
            cursor = stack.pop();
        } else {
            fromRight = false;
        }
        return value;
    }

    private final Set<BinaryTree<T>> set = Collections.newSetFromMap(new IdentityHashMap<>());

    private T next3() {
        while (cursor.leftChild() != null && !set.contains(cursor.leftChild())) {
            stack.push(cursor);
            // optimization: more mem, less cpu and time
//            if (cursor.rightChild() != null) {
//                stack.push(cursor.rightChild());
//            }
            cursor = cursor.leftChild();
        }

        if (cursor.rightChild() != null && !set.contains(cursor.rightChild())) {
            stack.push(cursor);
            cursor = cursor.rightChild();
            return next3();
        }

        var value = cursor.value();
        set.add(cursor);

        if (!stack.isEmpty()) {
            cursor = stack.pop();
        } else {
            cursor = null;
        }
        return value;
    }
}
