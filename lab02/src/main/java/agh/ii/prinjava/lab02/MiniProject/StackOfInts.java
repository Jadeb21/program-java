package agh.ii.prinjava.lab02.MiniProject;

import java.util.EmptyStackException;

public interface StackOfInts {

    void push(int value);
    int pop();
    int peek();
    boolean isEmpty();
    int size();
}
