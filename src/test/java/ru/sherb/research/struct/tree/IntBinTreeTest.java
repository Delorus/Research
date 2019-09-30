package ru.sherb.research.struct.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author maksim
 * @since 25.05.19
 */
public class IntBinTreeTest {

    /**
     * (1)
     *   \
     *   (2)
     *     \
     *     (3)
     *       \
     *       (4)
     *         \
     *         (5)
     *           \
     *           (6)
     */
    @Test
    public void createSimpleTree() {
        var ints = new int[]{1, 2, 3, 4, 5, 6};

        var tree = IntBinTree.create(ints);
        assertEquals(1, tree.value);
        assertEquals(2, tree.rightChild.value);
        assertEquals(3, tree.rightChild.rightChild.value);
        assertEquals(4, tree.rightChild.rightChild.rightChild.value);
        assertEquals(5, tree.rightChild.rightChild.rightChild.rightChild.value);
        assertEquals(6, tree.rightChild.rightChild.rightChild.rightChild.rightChild.value);
    }

    @Test
    public void createSimpleTree2() {
        var ints = new int[]{90, 95, 42, 75, 23, 10, 7};

        var tree = IntBinTree.create(ints);
        assertEquals(90, tree.value);
        assertEquals(95, tree.rightChild.value);
        assertEquals(42, tree.leftChild.value);
        assertEquals(4, tree.rightChild.rightChild.rightChild.value);
        assertEquals(5, tree.rightChild.rightChild.rightChild.rightChild.value);
        assertEquals(6, tree.rightChild.rightChild.rightChild.rightChild.rightChild.value);
    }
}