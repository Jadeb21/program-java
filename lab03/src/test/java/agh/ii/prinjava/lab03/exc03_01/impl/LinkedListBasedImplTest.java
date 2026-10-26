package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListBasedImplTest {

    private QueueOfInts queue;

    @BeforeEach
    void setUp() {
        queue = QueueOfIntsFtry.create(QueueOfIntsFtry.Impln.L_LIST_B);
    }

    @Test
    void newQueueIsEmpty() {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.numOfElems());
    }

    @Test
    void dequeueOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void peekOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> queue.peek());
    }

    @Test
    void enqueueIncreasesSizeAndMakesNonEmpty() {
        queue.enqueue(42);
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.numOfElems());
    }

    @Test
    void fifoOrderIsRespected() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void peekDoesNotRemove() {
        queue.enqueue(7);
        queue.enqueue(8);
        assertEquals(7, queue.peek());
        assertEquals(7, queue.peek());
        assertEquals(2, queue.numOfElems());
    }

    @Test
    void sizeTracksEnqueueAndDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(2, queue.numOfElems());
        queue.dequeue();
        assertEquals(1, queue.numOfElems());
        queue.dequeue();
        assertEquals(0, queue.numOfElems());
        assertTrue(queue.isEmpty());
    }

    @Test
    void manyElementsKeepFifoOrder() {
        int n = 1000;
        for (int i = 0; i < n; i++) queue.enqueue(i);
        assertEquals(n, queue.numOfElems());
        for (int i = 0; i < n; i++) assertEquals(i, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}