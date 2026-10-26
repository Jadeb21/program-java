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