package ru.sherb.research.struct.tree;

import java.util.Arrays;

/**
 *       (  1   )
 *      /        \
 *    (2)        (3)
 *   /   \ /     /
 * (42) (634) (432)
 *
 *
 * * "(" родителя должна быть на один символ " " дальше чем "/"
 * * ")" родителя должна быть на один символ " " ближе чем "\"
 * * символы "\" и "/" должны указывать прямо на центр дочернего узла,
 *     - т.е. если размер узла 4, то символ "\" должен находится на второй, а "/" на третьей позиции
 *     - если размер 5, то "\" -- на второй, а "/" -- на пятой позиции.
 * * как минимум один пробел между узлами
 * * значение узла должно быть в центре узла:
 *     - расстояние между больше (6), чем размер значения (4):
 *     делим расстояние на два, получаем 3
 *     делим размер значения на два, получаем 2
 *     вычитаем 3 - 2 = 1, с этой позиции вставляем значение
 *     ( 4444 )
 *     - расстояние между скобками меньше (5), чем размер значения (6)
 *     увеличиваем расстояние между скобками в право
 *     вставляем значение
 *     (666666)
 *
 * 1. начинаем рисовать дерево с самого левого нижнего конца
 * 2.
 *
 * @author maksim
 * @since 25.05.19
 */
public class PrettyFormatter {

    private final BinaryTree<?> tree;

    public PrettyFormatter(BinaryTree<?> tree) {
        this.tree = tree;
    }

    public String format() {
        var root = new Tree(tree.value());

        return "";
    }

    private BinaryTree<?> findLastLeftChild(BinaryTree<?> tree) {
        var child = tree;
        while (child.leftChild() != null) {
            child = tree.leftChild();
        }
        return child;
    }

    static final class Tree {

        private Leaf leaf;

        private Tree parent;
        private Tree leftChild;
        private Tree rightChild;

        private Tree left;
        private Tree right;

        public Tree(Object value) {
            this.leaf = new Leaf(value);
        }

        public String format() {
            var result = new StringBuilder();

            result.append(leaf.format()).append("\n");

            formatBranch(result);

            if (leftChild != null) {
                result.append(leftChild.format());
            }

            if (rightChild != null) {
                result.append(rightChild.format());
            }

            return result.toString();
        }

        private void formatBranch(StringBuilder result) {
            boolean hasChild = false;
            if (leftChild != null) {
                hasChild = true;
                result.append(indent(leaf.indent - 1)).append('/');
            }

            if (rightChild != null) {
                hasChild = true;
                result.append(indent(leaf.size())).append('\\');
            }

            if (hasChild) {
                result.append("\n");
            }
        }
    }

    static final class Leaf {
        private final String value;

        private int indent;
        private int padding;

        Leaf(Object value) {
            this.value = value.toString();
        }

        void setIndent(int indent) {
            this.indent = indent;
        }

        void setPadding(int padding) {
            this.padding = padding;
        }

        String format() {
            return indent(indent) + '(' + indent(padding) + value + indent(padding) + ')';
        }

        public int size() {
            return 2 /*brackets*/ + padding + value.length();
        }
    }

    private static String indent(int count) {
        return fill(count, ' ');
    }

    private static String fill(int count, char c) {
        var chs = new char[count];
        Arrays.fill(chs, c);
        return new String(chs);
    }
}
