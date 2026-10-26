/* package agh.ii.prinjava.lab01.lst01_09;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloJavaRecordTest {

    @Test
    void shouldReturnValuesThroughAccessors() {
        HelloJavaRecord hello = new HelloJavaRecord(10, "Hello");

        assertEquals(10, hello.i1());
        assertEquals("Hello", hello.s1());
    }

    @Test
    void shouldConsiderRecordsWithSameValuesEqual() {
        HelloJavaRecord hello1 = new HelloJavaRecord(10, "Hello");
        HelloJavaRecord hello2 = new HelloJavaRecord(10, "Hello");

        assertEquals(hello1, hello2);
    }

    @Test
    void equalRecordsShouldHaveSameHashCode() {
        HelloJavaRecord hello1 = new HelloJavaRecord(10, "Hello");
        HelloJavaRecord hello2 = new HelloJavaRecord(10, "Hello");

        assertEquals(hello1.hashCode(), hello2.hashCode());
    }

    @Test
    void recordsWithDifferentValuesShouldNotBeEqual() {
        HelloJavaRecord hello1 = new HelloJavaRecord(10, "Hello");
        HelloJavaRecord hello2 = new HelloJavaRecord(20, "Hello");

        assertNotEquals(hello1, hello2);
    }
}
*/