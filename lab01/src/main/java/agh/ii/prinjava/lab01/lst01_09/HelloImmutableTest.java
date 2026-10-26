/* package agh.ii.prinjava.lab01.lst01_09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloImmutableTest {

    @Test
    void shouldReturnValuesThroughGetters() {
        HelloImmutable hello = new HelloImmutable(10, "Hello");

        assertEquals(10, hello.getI1());
        assertEquals("Hello", hello.getS1());
    }

    @Test
    void shouldConsiderObjectsWithSameValuesEqual() {
        HelloImmutable hello1 = new HelloImmutable(10, "Hello");
        HelloImmutable hello2 = new HelloImmutable(10, "Hello");

        assertEquals(hello1, hello2);
    }

    @Test
    void equalObjectsShouldHaveSameHashCode() {
        HelloImmutable hello1 = new HelloImmutable(10, "Hello");
        HelloImmutable hello2 = new HelloImmutable(10, "Hello");

        assertEquals(hello1.hashCode(), hello2.hashCode());
    }

    @Test
    void objectsWithDifferentValuesShouldNotBeEqual() {
        HelloImmutable hello1 = new HelloImmutable(10, "Hello");
        HelloImmutable hello2 = new HelloImmutable(20, "Hello");

        assertNotEquals(hello1, hello2);
    }

    @Test
    void shouldReturnExpectedStringRepresentation() {
        HelloImmutable hello = new HelloImmutable(10, "Hello");

        assertEquals(
                "HelloImmutable{i1=10, s1='Hello'}",
                hello.toString()
        );
    }
}
*/