package agh.ii.prinjava.lab02.MiniProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBasedImplTest {

    private StackOfInts stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayBasedImpl();
    }

    @Test
    void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void popReturnsLastPushedValue() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
    }

    @Test
    void popOnEmptyStackThrows() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    void peekOnEmptyStackThrows() {
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Test
    void arrayGrowsWhenFull() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
    }

    @Test
    void arrayShrinksWhenMostlyEmpty() {
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        for (int i = 99; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());

        stack.push(7);
        assertEquals(7, stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void pushAndPopManyElements() {
        for (int i = 0; i < 10_000; i++) {
            stack.push(i);
        }
        assertEquals(10_000, stack.size());

        for (int i = 9_999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void alternatingPushPopKeepsOrder() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }
}