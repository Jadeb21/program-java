package agh.ii.prinjava.lab04.exc04_02.impl;

import java.util.NoSuchElementException;

/**
 * A doubly linked list with two sentinel nodes ({@code head} and {@code tail}).
 *
 * <p>The class is used as the building block of the DLLB ({@code Doubly Linked
 * List Based}) implementations of {@code MyStack} and {@code MyQueue}. All
 * structural operations are O(1): {@link #addFirst}, {@link #addLast},
 * {@link #removeFirst}, {@link #removeLast}, {@link #getFirst}, {@link #getLast}.
 *
 * @param <E> the type of elements held in this list
 */
public class DLinkList<E> {

    /**
     * A node of the doubly linked list.
     *
     * @param <T> the type of the element stored in the node
     */
    private static class Node<T> {
        T elem;
        Node<T> next;
        Node<T> prev;

        Node(T elem) {
            this.elem = elem;
        }
    }

    private final Node<E> head = new Node<>(null); // sentinel
    private final Node<E> tail = new Node<>(null); // sentinel
    private int size = 0;

    /**
     * Creates an empty list. The two sentinels are linked together so that
     * the empty list is a degenerate cycle {@code head <-> tail}.
     */
    public DLinkList() {
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Inserts an element at the front of this list.
     *
     * @param e the element to insert
     */
    public void addFirst(E e) {
        Node<E> n = new Node<>(e);
        n.next = head.next;
        n.prev = head;
        head.next.prev = n;
        head.next = n;
        size++;
    }

    /**
     * Inserts an element at the back of this list.
     *
     * @param e the element to insert
     */
    public void addLast(E e) {
        Node<E> n = new Node<>(e);
        n.prev = tail.prev;
        n.next = tail;
        tail.prev.next = n;
        tail.prev = n;
        size++;
    }

    /**
     * Removes and returns the first element of this list.
     *
     * @return the removed element
     * @throws NoSuchElementException if the list is empty
     */
    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        Node<E> n = head.next;
        head.next = n.next;
        n.next.prev = head;
        size--;
        n.next = n.prev = null; // help GC
        return n.elem;
    }

    /**
     * Removes and returns the last element of this list.
     *
     * @return the removed element
     * @throws NoSuchElementException if the list is empty
     */
    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        Node<E> n = tail.prev;
        tail.prev = n.prev;
        n.prev.next = tail;
        size--;
        n.next = n.prev = null; // help GC
        return n.elem;
    }

    /**
     * Returns, without removing, the first element of this list.
     *
     * @return the first element
     * @throws NoSuchElementException if the list is empty
     */
    public E getFirst() {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        return head.next.elem;
    }

    /**
     * Returns, without removing, the last element of this list.
     *
     * @return the last element
     * @throws NoSuchElementException if the list is empty
     */
    public E getLast() {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        return tail.prev.elem;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> cur = head.next;
        while (cur != tail) {
            sb.append(cur.elem);
            if (cur.next != tail) sb.append(", ");
            cur = cur.next;
        }
        return sb.append(']').toString();
    }
}