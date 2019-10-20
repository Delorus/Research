package ru.sherb.research.struct.tree;

import java.util.Iterator;
import java.util.Objects;

public final class PostOrderIterator<T> implements Iterator<T> {

    private BinaryTree<T> cursor;
    private boolean rightVisited;


    public PostOrderIterator(BinaryTree<T> tree) {
        this.cursor = Objects.requireNonNull(tree);
        initialNode();
    }

    @Override
    public boolean hasNext() {
        return !isRoot();
    }

    private void initialNode(){
        while (!isLeaf(cursor)) {
            if(cursor.leftChild() != null){
                goToLeft();
            } else {
                goToRight();
            }
        }
    }

    private boolean isLeaf(BinaryTree<T> cursor) {
        return cursor.leftChild() == null && cursor.rightChild() == null;
    }

    private void goToLeft(){
        cursor = cursor.leftChild();
        rightVisited = false;
    }

    private void goToRight(){
        cursor = cursor.rightChild();
        rightVisited = true;
    }

    private void cursorUp() {
        var prevNode = cursor;
        cursor = cursor.parent();
        if(!isRoot()) {
            rightVisited = cursor.rightChild() != null &&
                    prevNode == cursor.rightChild();
        }
    }

    private boolean isRoot() {
        return cursor == null;
    }

    @Override
    public T next() {
        var node = cursor;
        cursorUp();
        if(!isRoot()) {
            checkRightSubtree();
        }
        return node.value();
    }

    private void checkRightSubtree() {
        if(cursor.rightChild() != null && !rightVisited) {
            goToRight();
            initialNode();
        }
    }
}
