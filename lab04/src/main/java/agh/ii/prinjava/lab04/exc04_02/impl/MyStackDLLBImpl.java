package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyStack;

import java.util.EmptyStackException;

/**
 * DLLB (Doubly Linked List Based) implementation of {@link MyStack}.
 *
 * <p>LIFO semantics: {@link #push(Object)} maps to {@link DLinkList#addFirst},
 * and {@link #pop()} / {@link #peek()} map to {@link DLinkList#removeFirst} /
 * {@link DLinkList#getFirst}. All operations are O(1).
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStackDLLBImpl<E> implements MyStack<E> {

    private final DLinkList<E> elems = new DLinkList<>();

    /**
     * {@inheritDoc}
     *
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (elems.isEmpty()) throw new EmptyStackException();
        return elems.removeFirst();
    }

    /** {@inheritDoc} */
    @Override
    public void push(E x) {
        elems.addFirst(x);
    }

    /** {@inheritDoc} */
    @Override
    public int numOfElems() {
        return elems.size();
    }

    /**
     * {@inheritDoc}
     *
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() {
        if (elems.isEmpty()) throw new EmptyStackException();
        return elems.getFirst();
    }
}