package agh.ii.prinjava.lab04.exc04_02;

import agh.ii.prinjava.lab04.exc04_02.impl.MyQueueDLLBImpl;

/**
 * A First-In-First-Out (FIFO) queue.
 *
 * <p>In this project the contract is: {@link #dequeue()} and {@link #peek()}
 * throw {@link java.util.NoSuchElementException} when the queue is empty.
 *
 * @param <E> the type of elements held in this queue
 */
public interface MyQueue<E> {

    /**
     * Adds an element at the tail of the queue.
     *
     * @param x the element to add
     */
    void enqueue(E x);

    /**
     * Removes and returns the element at the head of the queue.
     *
     * @return the element previously at the head of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    E dequeue();

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue is empty
     */
    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     * Returns the number of elements currently in this queue.
     *
     * @return the current size of this queue
     */
    int numOfElems();

    /**
     * Returns, without removing, the element at the head of the queue.
     *
     * @return the element at the head of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    E peek();

    /**
     * Static factory method returning a new empty {@link MyQueue} backed by a
     * doubly linked list ({@link MyQueueDLLBImpl}).
     *
     * <p>See {@link MyStack#create()} for the full list of pros and cons of a
     * static factory method declared in the interface. Summary:
     * <ul>
     *   <li><b>Pros:</b> decouples clients from the concrete implementation,
     *       allows the interface to control/switch implementations, good name,
     *       works with type inference.</li>
     *   <li><b>Cons:</b> static methods cannot be overridden, couples the
     *       interface to the {@code impl} package, less discoverable than a
     *       constructor, hard to mock, slightly breaks the separation of
     *       contract vs. creation.</li>
     * </ul>
     *
     * @param <T> the type of elements held in the created queue
     * @return a new empty queue backed by a doubly linked list
     */
    static <T> MyQueue<T> create() {
        return new MyQueueDLLBImpl<>();
    }
}