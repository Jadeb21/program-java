package agh.ii.prinjava.lab02.MiniProject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackOfIntsTest {

    protected StackOfInts stack;

    @BeforeEach
    void setUp() {
        stack = new LinkedListBasedImpl();
    }

    @Test
    void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushIncreasesSize() {
        stack.push(1);
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        stack.push(2);
        assertEquals(2, stack.size());
    }

    @Test
    void popReturnsLastPushedValue() {
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void peekDoesNotRemoveElement() {
        stack.push(42);
        assertEquals(42, stack.peek());
        assertEquals(42, stack.peek());
        assertEquals(1, stack.size());
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
    void pushAndPopManyElements() {
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }
        assertEquals(1000, stack.size());

        for (int i = 999; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushAfterEmptyingWorks() {
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(1, stack.size());
    }
}