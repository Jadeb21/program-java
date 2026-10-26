package agh.ii.prinjava.lab03.exc03_01;

/**
 * A queue (FIFO — First In, First Out) of {@code int} values.
 *
 * <p>Elements are added at the <em>back</em> of the queue (see {@link #enqueue(int)})
 * and removed from the <em>front</em> (see {@link #dequeue()}).
 *
 * <p>Implementations of this interface are expected to be mutable and
 * <strong>not thread-safe</strong>.
 */
public interface QueueOfInts {
    /**
     * Adds a new element at the back of this queue.
     *
     * @param x the value to be added
     */
    void enqueue(int x);
    /**
     * Removes and returns the element at the front of this queue.
     *
     * @return the element that was at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    int dequeue();
    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * <p>Default implementation relies on {@link #numOfElems()}.
     *
     * @return {@code true} if this queue is empty, {@code false} otherwise
     */
    default boolean isEmpty() {
        return numOfElems() == 0;
    }
    /**
     * Returns the number of elements currently stored in this queue.
     *
     * @return the current size of the queue (always {@code >= 0})
     */
    int numOfElems();
    /**
     * Returns, but does <em>not</em> remove, the element at the front of this queue.
     *
     * @return the element at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    int peek();
}
