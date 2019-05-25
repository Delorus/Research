package ru.sherb.research.struct;

import java.io.IOException;

public class RopeString implements CharSequence, Appendable {
    private Node root;
    private Node current;

    private static class Node implements CharSequence {
        private Node leftNode;
        private Node rightNode;
        private int length;
        private CharSequence sequence;

        public Node(Node leftNode, Node rightNode, CharSequence sequence) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.sequence = sequence;

            this.length = 0;
            if (sequence != null) this.length += sequence.length();
            if (leftNode != null) this.length += leftNode.length();
            if (rightNode != null) this.length += rightNode.length();
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public String getString() {
            if (sequence != null && this.length == sequence.length()) return sequence.toString();

            StringBuilder result = new StringBuilder(this.length);
            result.append(leftNode.getString());
            result.append(rightNode.getString());
            return result.toString();
        }

        public void setString(CharSequence sequence) {
            this.sequence = sequence;
        }

        @Override
        public int length() {
            return length;
        }

        @Override
        public char charAt(int index) {
            if (sequence != null && this.length == sequence.length()) return sequence.charAt(index);
            if (index < leftNode.length) return leftNode.charAt(index);

            return rightNode.charAt(index - leftNode.length());
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            //TODO write

            return null;
        }
    }

    public RopeString(CharSequence sequence) {
    this.root = new Node(null, null, sequence);
        this.current = this.root;
    }

    @Override
    public Appendable append(CharSequence csq) throws IOException {
        return append(csq, 0, csq.length());
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
        if (csq != null) {
            if (start < 0) throw new StringIndexOutOfBoundsException(start);
            if (end > csq.length()) throw new StringIndexOutOfBoundsException(end);
        }

        Node leftNode = current;
        Node rightNode = new Node(null, null, csq == null ? "null" : csq.subSequence(start, end));
        current = new Node(leftNode, rightNode, null);
        return this;
    }

    @Override
    public Appendable append(char c) throws IOException {
        return append(Character.toString(c));
    }

    @Override
    public int length() {
        return current.length();
    }

    @Override
    public char charAt(int index) {
        return current.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        //TODO write
        return null;
    }

    @Override
    public String toString() {
        return current.getString();
    }
}
