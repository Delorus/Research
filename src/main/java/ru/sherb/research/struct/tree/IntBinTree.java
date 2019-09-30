package ru.sherb.research.struct.tree;

/**
 * @author maksim
 * @since 25.05.19
 */
public class IntBinTree {

    public static IntBinTree create(int... values) {
        var root = new IntBinTree();
        root.value = values[0];

        for (int i = 1; i < values.length; i++) {
            var node = new IntBinTree();
            node.value = values[i];

            var child = root;
            while (true) {
                if (child.value > node.value) {
                    if (child.leftChild == null) {
                        child.leftChild = node;
                        node.parent = child;
                        break;
                    } else {
                        child = child.leftChild;
                    }

                } else {
                    if (child.rightChild == null) {
                        child.rightChild = node;
                        node.parent = child;
                        break;
                    } else {
                        child = child.rightChild;
                    }
                }
            }
        }

        return root;
    }

    IntBinTree parent;

    IntBinTree leftChild;
    IntBinTree rightChild;

    Integer value;

    public String prettyFormat() {

        return new PrettyFormatter().format();
    }

    private class PrettyFormatter {

        public String format() {
            var result = new StringBuilder();
            return null;
        }

        private String leaf(Integer value) {
            return "(" + value + ")";
        }
    }
}
