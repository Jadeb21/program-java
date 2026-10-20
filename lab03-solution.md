# Lab03 - Java - elements of OOP (III)
## Exercice 0

There are warning in this module :
- Gradle
- JVM languages
- Java
- Logpoints
- Markdown
- Proofreading


## Exercice 2 

1.

throws clause :
Methods or constructors that fail to handle exceptional conditions thrown as checked exceptions in their bodies will normally cause compile-time errors if they lack proper exception types in their throws clauses. The Java programming language thus encourages a programming style where rare and otherwise truly exceptional conditions are documented in this way.

throw statement :
A throw statement causes an exception to be thrown. The result is an immediate transfer of control that may exit a constructor, an instance or static initializer, a field initializer, and a method invocation until a try statement is found that catches the thrown value. If no such try statement is found, then execution of the thread that executed the throw is terminated after invocation of the uncaught. Exception method for the thread group to which the thread belongs.

try statement :
A try statement executes a block. If an exception is thrown and the try statement has one or more catch clauses that can catch it, then control will be transferred to the first such catch clause.

2.

- 'Checked exceptions' are checked by the compiler at compile time. 
- 'Unchecked exceptions' occur at runtime and are not required to be handled or declared. Checked exceptions generally represent conditions that a program may reasonably need to recover from, such as file or database errors.

3.

Java provides specific keywords to handle and manage exceptions effectively. 
- 'throw' is used to explicitly throw an exception. 
- 'throws' is used to declare exceptions in a method signature. 

The throw keyword in Java is used to explicitly throw an exception from a method or any block of code.

## Exercice 3

The **AutoCloseable interface** in Java is a functional interface that defines a single method, close(). This interface is primarily used for resource management, particularly in try-with-resources statements, which ensure that resources are closed automatically after their operations have completed.

## Exercice 4

When you excecute the program in the idea intellig, the program can't excecute the console :


```
> Task :lab03:agh.ii.prinjava.lab03.lst03_05.Main.main()
Enter an int: 4
4
Enter a double (x.y): 3.14159
3.14159
Enter your name: jade
jade
No console available
```

After configuring the cmd, the console windows excecute perfectly the program :
```
Enter an int: 4
4
Enter a double (x.y): 3.14159
3.14159
Enter your name: Jade
Jade
Enter username : Brana
Username : Brana
Enter password :

Password :Cake

C:\Users\Jade Brana\IdeaProjects\program-java>
```

## Exercice 6

- BufferedReader : Reads text efficiently by buffering characters. Used with readLine() for line-by-line reading of files or console input (InputStreamReader(System.in)).


- BufferedWriter : Buffers character output for efficient writing. Used with newLine() for writing text line-by-line to files.


- FileReader : Convenience class for reading character files using the platform's default charset. Typically wrapped in a BufferedReader.


- FileWriter : Convenience class for writing character files. Typically wrapped in a BufferedWriter or PrintWriter.


- PrintWriter : Provides formatted printing (print, println, printf) to a text output stream. Commonly used for writing formatted text to files or over network sockets.


- FileInputStream : Low-level solution as the read methods of a {@link FileInputStream}
can read ONLY individual bytes and byte arrays
  

- FileOutputStream are for reading/writing bytes from/to files.


- DataInputStream : It converts an input stream of bytes into primitive data-type values and strings


- DataOutputStream : It enables you to write primitive data-type values and strings into an output stream


- ObjectInputStream : allows you to read primitive data types and objects from an input stream.


- ObjectOutputStream :  is used to serialize objects, allowing them to be written to an output stream. Serialization converts an object into a byte stream, enabling it to be stored in a file, sent over a network, or transferred between processes. This serialized data can later be deserialized using ObjectInputStream to reconstruct the original object.


- Files : tility class with static methods for common file operations: readAllLines, write, copy, move, delete, exists, createDirectories, etc.


- Path : Represents a file-system path (location), not the file's contents. Used with Files for all path-based operations.


- File : Legacy class representing a file or directory path. Used for basic metadata (exists(), length(), listFiles()) and older APIs. Largely superseded by Path/Files.


2.
There are 1 excecusion : 

```
> Task :lab03:agh.ii.prinjava.lab03.lst03_07.Main.main()

demo1...

demo2...
1.1
2.2
3.3
4.4
5.5

Demo file deleted

demo3...

demo4...
1.1
2.2
3.3
4.4
5.5

Demo file deleted

BUILD SUCCESSFUL in 881ms
2 actionable tasks: 1 executed, 1 up-to-date
```


## Exercice 7

- Serialization - a process/mechanism of converting object's state along with its class information
into a byte stream (sequence of bytes), as well as the process of rebuilding those bytes into
a live object at some future time (often called deserialization).

Note: serialization used to be important, but in contemporary applications is not used very often {@link Serializable} is only a marker interface - it simply allows the serialization mechanism to verify
that the class can be persisted

- The {@link Cloneable} marker interface specifies that the objects of this class can be cloned. To define a custom class that implements the {@code Cloneable} interface, the class must override
the {@code clone()} method (from the {@code Object} class).


## Exercice 8

1.
With enqueue : add a new node at the front 'first'
With dequeue : Remove a node from the back 'last'

I have choice 'NoSuchElementException' (unchecked) for dequeue and peek on the empty queue.




### LinkedListBasedImpl

```java
package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;
import java.util.NoSuchElementException;


/**
 * A {@link 'QueueOfInts'} backed by a singly linked list.
 *
 * <p>The list is kept as simple as possible:
 * <ul>
 *     <li>elements are inserted at the <em>head</em> ({@code first}) — O(1);</li>
 *     <li>elements are removed from the <em>tail</em> ({@code last}) — O(1).</li>
 * </ul>
 *
 * <p>This gives FIFO semantics with constant-time {@code enqueue}/{@code dequeue}.
 *
 * <p>The class is mutable <strong>not</strong> thread-safe.
 */

public class LinkedListBasedImpl implements QueueOfInts {

    /**
     * Creates an empty queue.
     */
    LinkedListBasedImpl() {
    }


    /**
     * {@inheritDoc}
     * Adds a new element at the back of this queue.
     *
     * @param x the value to be added
     */
    @Override
    public void enqueue(int x) {
        Node n = new Node(x,first,null);
        if (first !=null) {
            first.prev = n;
        }
        else {
            last = n;
        }
        first = n;
        numOfElems++;
    }


    /**
     * {@inheritDoc}
     * Removes and returns the element at the front of this queue.
     *
     * @return the element that was at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from an empty queue");
        }
        Node old = last;
        int value = old.elem;
        last = old.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        numOfElems--;
        return value;

    }


    /**
     * {@inheritDoc}
     * Returns the number of elements currently stored in this queue.
     *
     * @return the current size of the queue (always {@code >= 0})
     */
    @Override
    public int numOfElems() {
        return numOfElems;
    }


    /**
     * {@inheritDoc}
     * Returns, but does <em>not</em> remove, the element at the front of this queue.
     *
     * @return the element at the front of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    @Override
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek an empty queue");
        }
        return last.elem;
    }
    /**
     * A single node of the internal linked list.
     *
     * <p>{@code next} points towards the front of the queue (elements inserted earlier),
     * {@code prev} towards the back (elements inserted later).
     * Convention: {@code first} = most recently inserted element (head);
     * {@code last} = oldest element (tail) — next one to be dequeued.
     */
    private static class Node {

        int elem;
        Node next;
        Node prev;

        public Node(int elem) {
            this.elem = elem;
        }

        public Node(int elem, Node next, Node prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }
    /** Most recently inserted element (head of the list). {@code null} iff the queue is empty. */
    private Node first = null;

    /** Oldest element (tail of the list). {@code null} iff the queue is empty. */
    private Node last = null;

    /** Number of elements currently in the queue. */
    private int numOfElems = 0;
}

```
### QueueOfIntsFtry

```java
package agh.ii.prinjava.lab03.exc03_01.impl;

import agh.ii.prinjava.lab03.exc03_01.QueueOfInts;

final class QueueOfIntsFtry {
private QueueOfIntsFtry() {
}

    enum Impln {
        /**
         * (Custom) Linked List Based
         */
        L_LIST_B
    }

    static QueueOfInts create(Impln s) {
        return switch (s) {
            case L_LIST_B -> new LinkedListBasedImpl();
        };
    }
}
```
### QueueOfInts

```java
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

```
### LinkedListBasedImplTest

```java
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
```