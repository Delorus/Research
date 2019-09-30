package ru.sherb.research.struct.tree;

import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 * @author maksim
 * @since 27.05.19
 */
public final class DFSPostOrderIterator2<T> implements Iterator<T> {

    private final Stack<BinaryTree<T>> stack = new Stack<>();

    public DFSPostOrderIterator2(BinaryTree<T> tree) {
        Objects.requireNonNull(tree);
        stack.push(tree);
        leftBranchInStack();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        var node = stack.pop();
        if (!stack.isEmpty() && stack.peek().rightChild() != node) {
            stack.push(stack.peek().rightChild());
            leftBranchInStack();
        }
        return node.value();
    }

    private void leftBranchInStack(){
        var tree = stack.peek();
        while (tree.leftChild() != null){
            tree = tree.leftChild();
            stack.push(tree);
        }
    }
}
