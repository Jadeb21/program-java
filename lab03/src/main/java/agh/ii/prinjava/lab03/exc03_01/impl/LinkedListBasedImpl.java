package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;
import java.util.NoSuchElementException;


/**
 * A {@link QueueOfInts} backed by a singly linked list.
 *
 * <p>The list is kept as simple as possible:
 * <ul>
 *     <li>elements are inserted at the <em>head</em> ({@code first}) — O(1);</li>
 *     <li>elements are removed from the <em>tail</em> ({@code last}) — O(1).</li>
 * </ul>
 *
 * <p>This gives FIFO semantics with constant-time {@code enqueue}/{@code dequeue}.
 *
 * <p>The class is mutable <strong>not</strong> thread-safe.
 */

public class LinkedListBasedImpl implements QueueOfInts {

    /**
     * Creates an empty queue.
     */
    LinkedListBasedImpl() {
    }


    /**
     * {@inheritDoc}
     * Adds a new element at the back of this queue.
     *
     * @param x the value to be added
     */
    @Override
    public void enqueue(int x) {
        Node n = new Node(x,first,null);
        if (first !=null) {
            first.prev = n;
        }
        else {
            last = n;
        }
        first = n;
        numOfElems++;
    }


    /**
     * {@inheritDoc}
     * Removes and returns the element at the front of this queue.
     *
     * @return the element that was at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue");
        }
        Node old = last;
        int value = old.elem;
        last = old.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        numOfElems--;
        return value;

    }


    /**
     * {@inheritDoc}
     * Returns the number of elements currently stored in this queue.
     *
     * @return the current size of the queue (always {@code >= 0})
     */
    @Override
    public int numOfElems() {
        return numOfElems;
    }


    /**
     * {@inheritDoc}
     * Returns, but does <em>not</em> remove, the element at the front of this queue.
     *
     * @return the element at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @Override
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek an empty queue");
        }
        return last.elem;
    }
    /**
     * A single node of the internal linked list.
     *
     * <p>{@code next} points towards the front of the queue (elements inserted earlier),
     * {@code prev} towards the back (elements inserted later).
     * Convention: {@code first} = most recently inserted element (head);
     * {@code last} = oldest element (tail) — next one to be dequeued.
     */
    private static class Node {

        int elem;
        Node next;
        Node prev;

        public Node(int elem) {
            this.elem = elem;
        }

        public Node(int elem, Node next, Node prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }
    /** Most recently inserted element (head of the list). {@code null} iff the queue is empty. */
    private Node first = null;

    /** Oldest element (tail of the list). {@code null} iff the queue is empty. */
    private Node last = null;

    /** Number of elements currently in the queue. */
    private int numOfElems = 0;
}
