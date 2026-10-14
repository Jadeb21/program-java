package agh.ii.prinjava.lab02.MiniProject;

import org.junit.jupiter.api.BeforeEach;

class LinkedListBasedImplTest extends StackOfIntsTest {

    @BeforeEach
    void setUp() {
        stack = new LinkedListBasedImpl();
    }
}