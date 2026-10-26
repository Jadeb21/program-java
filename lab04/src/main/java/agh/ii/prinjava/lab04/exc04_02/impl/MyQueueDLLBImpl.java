package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyQueue;

import java.util.NoSuchElementException;

/**
 * DLLB (Doubly Linked List Based) implementation of {@link MyQueue}.
 *
 * <p>FIFO semantics: {@link #enqueue(Object)} maps to {@link DLinkList#addLast},
 * and {@link #dequeue()} / {@link #peek()} map to {@link DLinkList#removeFirst}
 * / {@link DLinkList#getFirst}. All operations are O(1).
 *
 * @param <E> the type of elements held in this queue
 */
public class MyQueueDLLBImpl<E> implements MyQueue<E> {

    private final DLinkList<E> elems = new DLinkList<>();

    /** {@inheritDoc} */
    @Override
    public void enqueue(E x) {
        elems.addLast(x);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E dequeue() {
        if (elems.isEmpty()) throw new NoSuchElementException("empty queue");
        return elems.removeFirst();
    }

    /** {@inheritDoc} */
    @Override
    public int numOfElems() {
        return elems.size();
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E peek() {
        if (elems.isEmpty()) throw new NoSuchElementException("empty queue");
        return elems.getFirst();
    }
}