package ru.sherb.research.struct.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostOrderIteratorTest {

    private static class ManualTree implements BinaryTree<Integer> {
        private final int value;

        private ManualTree parent;
        private ManualTree leftChild;
        private ManualTree rightChild;

        private ManualTree(int value) {
            this.value = value;
        }

        public ManualTree left(int value) {
            var child = new ManualTree(value);
            child.parent = this;
            this.leftChild = child;
            return child;
        }

        public ManualTree right(int value) {
            var child = new ManualTree(value);
            child.parent = this;
            this.rightChild = child;
            return child;
        }

        public ManualTree back() {
            return parent;
        }

        @Override
        public Integer value() {
            return value;
        }

        @Override
        public BinaryTree<Integer> parent() {
            return parent;
        }

        @Override
        public BinaryTree<Integer> leftChild() {
            return leftChild;
        }

        @Override
        public BinaryTree<Integer> rightChild() {
            return rightChild;
        }

        @Override
        public String toString() {
            return "{v=" + value + "; l=" + opt(leftChild) + "; r=" + opt(rightChild) + '}';
        }

        private static String opt(ManualTree tree) {
            if (tree == null) {
                return "<empty>";
            }
            return String.valueOf(tree.value);
        }
    }

    @Test
    public void nullTest(){
        assertThrows(NullPointerException.class, () -> new PostOrderIterator<Integer>(null));
    }

    @Test
    public void singleNode(){
        var tree = new ManualTree(1);
        var expected = new Integer[]{1};

        assertIterator(tree, expected);
    }

    @Test
    public void simpleTree(){
        var tree = new ManualTree(1)
                .left(2)
                    .left(4)
                        .back()
                    .right(5)
                        .left(7)
                            .left(10)
                                .back()
                            .back()
                        .right(8)
                            .back()
                        .back()
                    .back()
                .right(3)
                    .right(6)
                        .left(9)
                            .back()
                        .back()
                    .back();
        // @formatter:on
        var expected = new Integer[]{4, 10, 7, 8, 5, 2, 9, 6, 3, 1};
        assertIterator(tree, expected);
    }

    @Test
    public void hardTree(){
        var tree =
            new ManualTree(1)
                .left(2)
                    .right(4)
                        .back()
                    .back()
                .right(5)
                    .left(7)
                        .back()
                    .back();

        var expected = new Integer[]{4, 2, 7, 5, 1};
        assertIterator(tree, expected);
    }

    private void assertIterator(BinaryTree<Integer> tree, Integer[] expected){
        var iterator = new PostOrderIterator<>(tree);
        assertArrayEquals(expected, toArray(iterator));
    }

    private Integer[] toArray(Iterator<Integer> iterator) {
        List<Integer> result = new ArrayList<>();
        iterator.forEachRemaining(result::add);
        return result.toArray(Integer[]::new);
    }
}