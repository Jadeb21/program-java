package agh.ii.prinjava.lab02.MiniProject;

import java.util.EmptyStackException;

public class LinkedListBasedImpl implements StackOfInts {

    private static final class Node {
        private final int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node top;
    private int size;

    public LinkedListBasedImpl() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(int value) {
        top = new Node(value, top);
        size++;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int value = top.value;
        top = top.next;
        size--;
        return value;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        return size;
    }
}