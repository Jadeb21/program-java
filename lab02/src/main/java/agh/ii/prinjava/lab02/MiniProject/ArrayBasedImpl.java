package agh.ii.prinjava.lab02.MiniProject;

import java.util.EmptyStackException;

public class ArrayBasedImpl implements StackOfInts {

    private static final int INITIAL_CAPACITY = 10;
    private static final int MIN_CAPACITY = 4;

    private int[] elements;
    private int size;

    public ArrayBasedImpl() {
        this.elements = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void push(int value) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int value = elements[--size];
        if (size > 0 && size <= elements.length / 4) {
            shrink();
        }
        return value;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        int newCapacity = elements.length * 2;
        int[] newElements = new int[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void shrink() {
        int newCapacity = Math.max(MIN_CAPACITY, elements.length / 2);
        int[] newElements = new int[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }
}