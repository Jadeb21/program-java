# Java - elements of generic programming (IV)

## my notes

- Generic programming - programming in terms of a generic type instead of a specific type.


- Parametric polymorphism - parts of the program (methods and/or classes/interfaces) can be parameterised by types.
  A type is (parametrically) polymorphic if its declaration contains at least one type parameter/variable.


- Type parameter (e.g., the "T" in {@code GenClass<T>}) vs.
- type argument (e.g., {@code Double} in {@code GenClass<Double> r;})


Semantically, Java generics are defined by erasure, whereas C++ templates are defined by expansion.
In C++ templates, each instance of a template for a new type is compiled separately.


- Cast-iron guarantee: the implicit casts added by the compilation of generics never fail

## Exercice 1 

- Generic classes : is a class that declares one or more type variables


- Generic Methods : is a method that declares one or more type variables


- Generic Interfaces : is an interface that uses type parameters, allowing it to adapt to different data types while maintaining type safety. Just like generic classes, generic interfaces enable you to write code that works with various types without sacrificing compile-time type checking.

## Exercice 2

1.

- Generic type is a generic class or interface that is parameterised over types.
  A generic type can be considered as a type constructor (vs. data/instance constructor).
A generic type is compiled into a raw type (type erasure) - at the bytecode level,
code that does not use generics looks just like code that does.
So a generic type is more flexible and type-code safe.

2.

The syntax of :


- generic class declaration : Class name<T1, T2, ..., Tn>


- Just like type declarations, method declarations can be generic, i.e., parameterized by one or more type parameters.
Generic methods allow type parameters to be used to express dependencies among the types of one or more arguments of a method and/or its return type.
  **method body <type-parameter-list>**


3.
A raw type - a generic class or interface used without specifying a concrete type
(this enables backward compatibility with earlier versions of Java, i.e., pre-Java5).
Raw types bypass generics, allowing unsafe operations like adding elements of the wrong type.

4.



```java
// (a)
GenBox gb1 = new GenBox(1);
gb1.setX("abc");
gb1.setX(new GenBox(true));

```
GenBox is used as a raw type, so its members are erased: setX effectively becomes setX(Object).

Therefore setX("abc") and setX(new GenBox(true)) both compile — a String and a GenBox are both Objects.

The compiler emits unchecked warnings (raw type usage, unchecked call to setX), but no errors.

This is legal but type-unsafe — nothing stops you from mixing types.

**Result: compiles (with unchecked warnings).**

```java

// (b)
GenBox<Integer> gb2 = new GenBox(1);
gb2.setX("abc");
gb2.setX(new GenBox(true));

```

gb2 has static type GenBox<Integer>, so setX expects an Integer.

"abc" is a String → incompatible types: String cannot be converted to Integer → compile error.

new GenBox(true) has type GenBox (raw), not Integer → incompatible types → compile error.

The first line still compiles (the new GenBox(1) on the right is raw, giving only an unchecked conversion warning), but the two setX calls fail.

**Result: does NOT compile — two type errors on the setX calls.**


5.

```java
public class Main {
public static void main(String[] args ) {
Integer[] ints = {1, 2, 3};
String[] strs = {"A", "B", "C"};

        print(ints);
        print(strs);
    }

    public static <T> void print(T[] elems) { // <- complete this line
    for (int i = 0; i < elems.length; i++)
        System.out.print(elems[i] + " ");
    System.out.println();
    }
}  
```

7.
A terminer


## Exercice 3

1.
Bounded type parameters in Java Generics restrict the types that can be used as arguments for a type parameter. They are useful when generic code needs to operate only on a specific type or its subtypes. Bounds are specified using the extends keyword.

A multiple-bound type parameter, T (being a subtype of two types, here they are interfaces)

2.

- **Interface bounds** : A type variable may have many interface bounds separate with & :


- **Class bounds** : A type variable may have at most one class bound, and if present it must come first:

```java
public class MoveAll{
    void goTo(double x, double y);
}



// Moving all elements of the array to point (x,y)
private static <T> void moveAll(T[] elems, double x, double y) {
    for (var e : elems) {
        e.goTo(x, y);
    }
}

```

## Exercice 4 

1.


```
A generic type (or type constructor) is said to be invariant with respect to its type parameter(s).
Take a type constructor such as GenBox, and suppose B is a subtype of A. Three cases are then possible:

- Covariant: GenBox<B> is a subtype of GenBox<A> - subtyping goes in the same direction as between B and A.

- Contravariant: GenBox<A> is a subtype of GenBox<B> - the relationship goes in the opposite direction.

- Invariant: neither of the above holds.

In Java, generic types are invariant: GenBox<B> is not a subtype of GenBox<A>, and GenBox<String> is not a GenBox<Object>.
And that's a good thing! If it were otherwise, a variable of type GenBox<Object> could insert any object into a box meant to hold only strings — which would break type safety.

```

2.

- ? extends T (subtype wildcard): You can read elements as T, but you cannot add anything (except null), because the actual type is some unknown subtype of T.

- ? super T (supertype wildcard): You can add elements of type T (or its subclasses), but you can only read them as Object, because the actual type is some unknown supertype of T.

- <?> (unbounded wildcard): You can only read elements as Object and cannot add anything (except null), because the actual type is completely unknown.

3.

```java
class A {}
class B extends A {}
class C extends B {}
class GenBox<T> {
private T x;
public T getX() { return x; }
public void setX(T x) { this.x = x; }
//...
}

GenBox<B> gb1 = new GenBox<B>(); //ok B is a B
GenBox<B> gb2 = new GenBox<C>(); // error, C is child of B but isn't B so don't run
GenBox<B> gb3 = new GenBox<A>(); // error A is a parent of B but isn't B so don't run 
B b1 = gb1.getX();
gb1.setX(new B()); // we can write because is a B on the B box

GenBox<? extends B> gb4 = new GenBox<B>(); // yes B is part of B
GenBox<? extends B> gb5 = new GenBox<C>(); // yes C is part of B because is this child
GenBox<? extends B> gb6 = new GenBox<A>(); // no, A is a parent so don't run
B b2 = gb5.getX();
gb5.setX(new B()); // not write, we can't put a B on the C box
gb5.setX(new C()); // no because the compiler see B

GenBox<? super B> gb7 = new GenBox<B>(); // good
GenBox<? super B> gb8 = new GenBox<C>(); // error, C is a child not a parent
GenBox<? super B> gb9 = new GenBox<A>(); // Good A is parent of B
B b3 = gb9.getX(); //no
gb9.setX(new B()); //not good, we can't write a B on the A box
gb9.setX(new C()); // not good, we can't a C on the A box
 
GenBox<?> gb10 = new GenBox<B>();  //yes
GenBox<?> gb11 = new GenBox<C>(); //yes
GenBox<?> gb12 = new GenBox<A>(); //yes
B b4 = gb10.getX(); // read only as object
gb10.setX(new B()); //no 

```






## Exercice Mini project


MyStack
```java
package agh.ii.prinjava.lab04.exc04_02;

import agh.ii.prinjava.lab04.exc04_02.impl.MyStackDLLBImpl;

/**
 * A Last-In-First-Out (LIFO) stack.
 *
 * <p>Implementations must document their behavior on an empty stack. In this project
 * the contract is: {@link #pop()} and {@link #peek()} throw
 * {@link java.util.EmptyStackException} when the stack is empty.
 *
 * @param <E> the type of elements held in this stack
 */
public interface MyStack<E> {

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element previously at the top of the stack
     * @throws java.util.EmptyStackException if the stack is empty
     */
    E pop();

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param x the element to push (may be {@code null} for DLLB implementations)
     */
    void push(E x);

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack is empty
     */
    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     * Returns the number of elements currently in this stack.
     *
     * @return the current size of this stack
     */
    int numOfElems();

    /**
     * Returns, without removing, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws java.util.EmptyStackException if the stack is empty
     */
    E peek();

    /**
     * Static factory method returning a new empty {@link MyStack} backed by a
     * doubly linked list ({@link MyStackDLLBImpl}).
     *
     * <p><b>Pros of a static factory method declared in the interface:</b>
     * <ul>
     *   <li>Client code only depends on the interface, not on a concrete class:
     *       the implementation can be swapped later without touching callers.</li>
     *   <li>The factory can return a subtype, a cached/shared instance, or a
     *       specialized implementation depending on configuration.</li>
     *   <li>Well-named creation method (vs. a constructor).</li>
     *   <li>Works with type inference: {@code MyStack.create()} infers the element type.</li>
     *   <li>The interface controls the default implementation choice.</li>
     * </ul>
     *
     * <p><b>Cons of a static factory method declared in the interface:</b>
     * <ul>
     *   <li>Static interface methods cannot be overridden: no polymorphism, no way
     *       for an alternative implementation to plug its own factory.</li>
     *   <li>The interface now depends on the implementation package
     *       ({@code impl}) — the abstraction knows about a concrete class,
     *       which is a form of coupling and creates a cycle between packages.</li>
     *   <li>Less discoverable than a public constructor in IDEs / Javadoc.</li>
     *   <li>Hard to substitute (e.g. with a mock) when unit-testing the factory
     *       itself; dependency injection is not possible.</li>
     *   <li>Breaks strict separation of concerns: an interface should describe
     *       a contract, not decide how instances are created.</li>
     * </ul>
     *
     * @param <T> the type of elements held in the created stack
     * @return a new empty stack backed by a doubly linked list
     */
    static <T> MyStack<T> create() {
        return new MyStackDLLBImpl<>();
    }
}
```




MyQueue

```java
package agh.ii.prinjava.lab04.exc04_02;

import agh.ii.prinjava.lab04.exc04_02.impl.MyQueueDLLBImpl;

/**
 * A First-In-First-Out (FIFO) queue.
 *
 * <p>In this project the contract is: {@link #dequeue()} and {@link #peek()}
 * throw {@link java.util.NoSuchElementException} when the queue is empty.
 *
 * @param <E> the type of elements held in this queue
 */
public interface MyQueue<E> {

    /**
     * Adds an element at the tail of the queue.
     *
     * @param x the element to add
     */
    void enqueue(E x);

    /**
     * Removes and returns the element at the head of the queue.
     *
     * @return the element previously at the head of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    E dequeue();

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue is empty
     */
    default boolean isEmpty() {
        return numOfElems() == 0;
    }

    /**
     * Returns the number of elements currently in this queue.
     *
     * @return the current size of this queue
     */
    int numOfElems();

    /**
     * Returns, without removing, the element at the head of the queue.
     *
     * @return the element at the head of the queue
     * @throws java.util.NoSuchElementException if the queue is empty
     */
    E peek();

    /**
     * Static factory method returning a new empty {@link MyQueue} backed by a
     * doubly linked list ({@link MyQueueDLLBImpl}).
     *
     * <p>See {@link MyStack#create()} for the full list of pros and cons of a
     * static factory method declared in the interface. Summary:
     * <ul>
     *   <li><b>Pros:</b> decouples clients from the concrete implementation,
     *       allows the interface to control/switch implementations, good name,
     *       works with type inference.</li>
     *   <li><b>Cons:</b> static methods cannot be overridden, couples the
     *       interface to the {@code impl} package, less discoverable than a
     *       constructor, hard to mock, slightly breaks the separation of
     *       contract vs. creation.</li>
     * </ul>
     *
     * @param <T> the type of elements held in the created queue
     * @return a new empty queue backed by a doubly linked list
     */
    static <T> MyQueue<T> create() {
        return new MyQueueDLLBImpl<>();
    }
}
```

DLinkList
````java
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
````


MyStackDLLImpl

````java
package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyStack;

import java.util.EmptyStackException;

/**
 * DLLB (Doubly Linked List Based) implementation of {@link MyStack}.
 *
 * <p>LIFO semantics: {@link #push(Object)} maps to {@link DLinkList#addFirst},
 * and {@link #pop()} / {@link #peek()} map to {@link DLinkList#removeFirst} /
 * {@link DLinkList#getFirst}. All operations are O(1).
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStackDLLBImpl<E> implements MyStack<E> {

    private final DLinkList<E> elems = new DLinkList<>();

    /**
     * {@inheritDoc}
     *
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E pop() {
        if (elems.isEmpty()) throw new EmptyStackException();
        return elems.removeFirst();
    }

    /** {@inheritDoc} */
    @Override
    public void push(E x) {
        elems.addFirst(x);
    }

    /** {@inheritDoc} */
    @Override
    public int numOfElems() {
        return elems.size();
    }

    /**
     * {@inheritDoc}
     *
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public E peek() {
        if (elems.isEmpty()) throw new EmptyStackException();
        return elems.getFirst();
    }
}
````


MyQueueDLLImpl

```java
package agh.ii.prinjava.lab04.exc04_02.impl;

import agh.ii.prinjava.lab04.exc04_02.MyQueue;

import java.util.NoSuchElementException;

/**
 * DLLB (Doubly Linked List Based) implementation of {@link MyQueue}.
 *
 * <p>FIFO semantics: {@link #enqueue(Object)} maps to {@link DLinkList#addLast},
 * and {@link #dequeue()} / {@link #peek()} map to {@link DLinkList#removeFirst}
 * / {@link DLinkList#getFirst}. All operations are O(1).
 *
 * @param <E> the type of elements held in this queue
 */
public class MyQueueDLLBImpl<E> implements MyQueue<E> {

    private final DLinkList<E> elems = new DLinkList<>();

    /** {@inheritDoc} */
    @Override
    public void enqueue(E x) {
        elems.addLast(x);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E dequeue() {
        if (elems.isEmpty()) throw new NoSuchElementException("empty queue");
        return elems.removeFirst();
    }

    /** {@inheritDoc} */
    @Override
    public int numOfElems() {
        return elems.size();
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException if the queue is empty
     */
    @Override
    public E peek() {
        if (elems.isEmpty()) throw new NoSuchElementException("empty queue");
        return elems.getFirst();
    }
}
```

