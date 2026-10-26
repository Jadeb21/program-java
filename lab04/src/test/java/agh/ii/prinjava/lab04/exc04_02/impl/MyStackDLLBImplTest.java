package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackDLLBImplTest {

    private MyStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = MyStack.create(); // exercises the static factory
    }

    @Test
    void newStackIsEmpty() {
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.numOfElems());
    }

    @Test
    void pushPopIsLifo() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void peekDoesNotRemove() {
        stack.push(42);
        assertEquals(42, stack.peek());
        assertEquals(42, stack.peek());
        assertEquals(1, stack.numOfElems());
    }

    @Test
    void popOnEmptyThrowsEmptyStackException() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    void peekOnEmptyThrowsEmptyStackException() {
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }
}