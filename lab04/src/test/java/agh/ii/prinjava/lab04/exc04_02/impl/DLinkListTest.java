package agh.ii.prinjava.lab04.exc04_02.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DLinkListTest {

    private DLinkList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new DLinkList<>();
    }

    @Test
    void newListIsEmpty() {
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void addFirstIncrementsSize() {
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        assertEquals(3, list.size());
        assertEquals(3, list.getFirst());
        assertEquals(1, list.getLast());
    }

    @Test
    void addLastIncrementsSize() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(3, list.getLast());
    }

    @Test
    void removeFirstReturnsAndRemovesHead() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        assertEquals(10, list.removeFirst());
        assertEquals(20, list.removeFirst());
        assertEquals(30, list.removeFirst());
        assertTrue(list.isEmpty());
    }

    @Test
    void removeLastReturnsAndRemovesTail() {
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        assertEquals(10, list.removeLast());
        assertEquals(20, list.removeLast());
        assertEquals(30, list.removeLast());
        assertTrue(list.isEmpty());
    }

    @Test
    void getFirstAndGetLastDoNotRemove() {
        list.addLast(1);
        list.addLast(2);
        assertEquals(1, list.getFirst());
        assertEquals(2, list.getLast());
        assertEquals(2, list.size());
    }

    @Test
    void removeFirstOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> list.removeFirst());
    }

    @Test
    void removeLastOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> list.removeLast());
    }

    @Test
    void getFirstOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> list.getFirst());
    }

    @Test
    void getLastOnEmptyThrows() {
        assertThrows(NoSuchElementException.class, () -> list.getLast());
    }
}