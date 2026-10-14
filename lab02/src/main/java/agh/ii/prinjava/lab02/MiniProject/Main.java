package agh.ii.prinjava.lab02.MiniProject;

public class Main {

    private static void runScenario(StackOfInts stack) {
        System.out.println("isEmpty (before) = " + stack.isEmpty());
        System.out.println("size    (before) = " + stack.size());

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("size    (after 3 pushes) = " + stack.size());
        System.out.println("peek    = " + stack.peek());

        System.out.println("pop     = " + stack.pop());
        System.out.println("pop     = " + stack.pop());
        System.out.println("pop     = " + stack.pop());

        System.out.println("isEmpty (after) = " + stack.isEmpty());
        System.out.println("size    (after) = " + stack.size());
    }

    private static void demo1() {
        System.out.println("demo1... (LinkedListBasedImpl)");
        runScenario(new LinkedListBasedImpl());
    }

    private static void demo2() {
        System.out.println("demo2... (ArrayBasedImpl)");
        runScenario(new ArrayBasedImpl());
    }

    private static void demo3() {
        System.out.println("demo3... (ArrayBasedImpl, growth/shrink)");
        StackOfInts stack = new ArrayBasedImpl();

        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }
        System.out.println("size after 1000 pushes = " + stack.size());

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        System.out.println("sum of popped values = " + sum);
        System.out.println("size after emptying = " + stack.size());
    }

    public static void main(String[] args) {
        demo1();
        System.out.println();

        demo2();
        System.out.println();

        demo3();
    }
}