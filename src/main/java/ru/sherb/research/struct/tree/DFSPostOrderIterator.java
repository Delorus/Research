package ru.sherb.research.struct.tree;

import java.util.*;

/**
 * Post-order обход используется когда нам нужно начать с листов
 * и завершить главным узлом — то есть разложить дерево на то, как оно строилось.
 *
 * @author maksim
 * @since 26.05.19
 */
public final class DFSPostOrderIterator<T> implements Iterator<T> {
    private Stack<BinaryTree<T>> buffer = new Stack<>();
    private Iterator<T> iterator;

    public DFSPostOrderIterator(BinaryTree<T> tree) {
        Objects.requireNonNull(tree);
        buffer.push(tree);
        simplePostOrder();
    }

    private void simplePostOrder(){
        var result = new LinkedList<T>();
        while (!buffer.empty()) {
            var node = buffer.pop();
            result.push(node.value());

            if(node.leftChild() != null){
                buffer.push(node.leftChild());
            }
            if(node.rightChild() != null) {
                buffer.push(node.rightChild());
            }
        }
        iterator = result.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return iterator.next();
    }
}
