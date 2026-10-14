# Exercice 1 - Final variables, methods, and class

## 2.

Using constants in programming offers several benefits:

1. **Immutability**: Constants cannot be changed after initialization, which helps prevent accidental modifications and bugs in the code.
2. **Code Clarity**: By using constants, you make your code more readable and understandable. It clearly indicates which values are meant to remain unchanged, helping other developers (and yourself) understand the intent behind the code.
3. **Maintainability**: If a constant value needs to be updated, you only have to change it in one place. This reduces the risk of errors and makes maintenance easier.
4. **Self-Documentation**: Constants often have descriptive names (e.g., `MAX_USERS`, `PI_VALUE`), which serve as documentation for their purpose, making the code easier to follow.
5. **Optimization**: The compiler can optimize code better when it knows certain values will not change. This can lead to improved performance in some cases.
6. **Avoiding Magic Numbers**: Using constants helps eliminate "magic numbers" (literal values without context) in your code, making it clearer what those values represent.
7. **Type Safety**: Constants can help enforce type safety, especially when used in function parameters, ensuring that certain values are not modified within functions.

## 3.

The word "final" is a Java keyword for declaring a constant.

**Classes:**

A class that is declared final cannot be subclassed. This is useful when creating an immutable class like the String class

**Methods:**

A final method cannot be overridden by any subclass

Note: methods called from constructors should generally be declared final. If a constructor calls a non-final method, a subclass may redefine that method with surprising or undesirable results.

**Local variable:**
A final local variable - a constant declared inside a method

**Instance variable:**
A final instance variable - a constant declared inside a class (must be initialized by the end of every constructor)

**Static constants:**
A final class variable - a constant declared as static inside a class

## 4.

1. An immutable class in Java is a class whose objects cannot change state after creation, achieved by making the class final, fields private and final, removing setters, and defensively copying mutable inputs. so no, it s not necessary

2. Yes it s

## 5.

The following class is not immutable because the class and the field is not declared final, and not all fields are privates.

---

# Exercice 2

## 3.

The syntax and struture are correct, we use enum keyword to create singleton.

But it's not Thread-safe because the mutable value field is accessed and modified without synchronization.

---

# Exercice 3

## 2.

A *nested class* is any class whose declaration occurs within the body of another class or interface declaration.
A nested class may be:

- a static member class an inner class, which may be:

- a non-static member class (often referred to as *inner class)*

- a local class

- an anonymous class

A *local class* - a nested class whose declaration is contained by a block; typically, in the body of a method

An *anonymous class* - an inner class with no name

Java's four nested class types differ mainly in whether they hold an implicit reference to an enclosing instance (static member classes don't; non-static inner, local, and anonymous classes do), in their scope and where they can be declared (class level vs. inside a block), and in their restriction on static members (only static member classes can declare them).

## 3.

Working with inner (non-static member) classes

Since InnerClass belongs to the "instance-part" of OuterClass its instance have to be created in a different way:

- create an instance of OuterClass

- use it to create an instance of InnerClass

That is why the following code causes a compilation error:

```
OuterClass.InnerClass ic1 = new OuterClass.InnerClass(); // Error: OuterClass is not an enclosing class
```

1.

Yes, a nested class can be instantiated from another class if it is accessible (e.g., a public static nested class referenced by its outer class name, or a non-static inner class accessed through an enclosing instance).

2.

Yes, public, protected, private, and static modifiers can all be applied to nested classes, though static is only permitted for static nested classes and not for non-static inner classes.

---

# Exercice 4

An *abstract class* is a class that is:

- either incomplete (as `Shape`); typical case, *Abstract Data Type*)

- or to be considered incomplete (as `ToBeConsideredAbstract`); unusual, but possible

A class should be declared abstract only if the intent is that subclasses can be created to complete the implementation.

To prevent instantiation of a class it is recommended to declare only one parameterless constructor that is private

An *abstract method* declaration introduces the method (as a member), providing its:

- signature
- result type
- throws clause, if any but *does not provide an implementation*

*Note*: abstract and final cannot go together (since it makes no sense)

An *interface* is a mechanism for specifying a contract between two parties:

- the supplier of a service
- the classes that want their objects to be usable with the service.

Modifier 'abstract' is redundant for interfaces and interface methods

Modifier 'public' is redundant for interface methods

`An interface specifies a set of methods that an implementing class must provide`

`* A class can implement more than one interface`

A variable declared in an interface is public, static, and final. As it is constant, it must be initialised

- `public`, `static`, and `final` are redundant for variables declared in an interface
- static blocks are not allowed in interfaces

`An interface can extend (inherit from) more than one interface`

## 2.

Abstract classes can hold state (instance fields) and constructors and allow a class to extend only one of them,
while interfaces cannot hold instance state and allow a class to implement many of them, making interfaces better for defining capabilities and abstract classes better for sharing common code.

## 3.

1. Constant fields — implicitly public static final
2. Abstract methods — implicitly public abstract (no body)
3. Default methods — default methods with a body (Java 8+)
4. Static methods — static methods with a body (Java 8+)
5. Private methods — private methods with a body (Java 9+), used as helpers for default/static methods
6. Private static methods — private static methods with a body (Java 9+)
7. Nested types — nested classes, interfaces, enums, and records (implicitly public static)

## 4.

The correct definition of abstract class is :

`abstract class E { abstract void m1(); }`

## 5.

The correct definition of interface is :

`interface D { void m1(); }`

## 6.

- The family tree is the following:
  C -> D -> B, is true
  A -> B     , is true, so
- The final output are "b is an instance of A", and "b is an instance of C".

---

# Exercice 5

## 2.

A 'lambda' is an implementation of a functional interface that can be stored in a variable, passed around,
or returned, just like any object.

A 'functional' interface (exactly one abstract method) is the target type that a lambda,
or method reference can be assigned to, so no implements or child class is needed.

In other words, the difference is that a lambda is the implementation (the code/behavior),
while a functional interface is the target type (the contract) that the lambda is assigned to.

## 3.
- $void \rightarrow int$
``` java
@FunctionalInterface
interface IntSupplier {
    int get();
}
```
- $int \rightarrow void$
```java
@FunctionalInterface
interface IntConsumer {
    void accept(int value);
}
```
- $int \rightarrow int$
```java
@FunctionalInterface
interface IntUnaryOperator {
    int apply(int value);
}
```
- $(int, int) \rightarrow void$
```java
@FunctionalInterface
interface IntBiConsumer {
    void accept(int a, int b);
}
```

Anonymous class

```java
public class AnonymousClassDemo {
    public static void main(String[] args) {

        // void -> int
        IntSupplier supplier = new IntSupplier() {
            @Override
            public int get() {
                return 42;
            }
        };

        // int -> void
        IntConsumer consumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println("Consumed: " + value);
            }
        };

        // int -> int
        IntUnaryOperator operator = new IntUnaryOperator() {
            @Override
            public int apply(int value) {
                return value * value;
            }
        };

        // (int, int) -> void
        IntBiConsumer biConsumer = new IntBiConsumer() {
            @Override
            public void accept(int a, int b) {
                System.out.println("Sum: " + (a + b));
            }
        };

        // Usage
        System.out.println("Supplier: " + supplier.get());   // 42
        consumer.accept(10);                                  // Consumed: 10
        System.out.println("Operator: " + operator.apply(5)); // 25
        biConsumer.accept(3, 4);                              // Sum: 7
    }
}
```

lambda expressions

```java
public class LambdaDemo {
    public static void main(String[] args) {

        // void -> int
        IntSupplier supplier = () -> 42;

        // int -> void
        IntConsumer consumer = value -> System.out.println("Consumed: " + value);

        // int -> int
        IntUnaryOperator operator = value -> value * value;

        // (int, int) -> void
        IntBiConsumer biConsumer = (a, b) -> System.out.println("Sum: " + (a + b));

        // Usage
        System.out.println("Supplier: " + supplier.get());    // 42
        consumer.accept(10);                                  // Consumed: 10
        System.out.println("Operator: " + operator.apply(5)); // 25
        biConsumer.accept(3, 4);                              // Sum: 7
    }
}
```

---

# Exercice 6

1.

