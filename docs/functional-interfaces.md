
# Functional Interfaces – The Contract Behind Lambda Expressions

Lambda expressions do not work alone.

They need a **contract** that tells Java:
- What inputs are expected
- What output is returned
- Which method the lambda represents

That contract is called a **Functional Interface**.

Without functional interfaces, **lambda expressions would not exist in Java**.

---

## What Is a Functional Interface?

A **functional interface** is an interface that has **exactly one abstract method**.

That single method becomes the **target** for a lambda expression.

### In Simple Terms

> A functional interface tells Java  
> **“This lambda belongs here.”**

---

## Before Java 8: Why This Was Needed

Before Java 8, behavior had to be wrapped inside:
- Concrete classes
- Anonymous inner classes

This made simple actions:
- Verbose
- Hard to read
- Difficult to reuse

Java 8 solved this by combining:
✅ Functional interfaces  
✅ Lambda expressions  

---

## A Simple Functional Interface Example

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
````

Only **one abstract method** ✅

Now we can use a lambda:

```java
Calculator calc = (a, b) -> a + b;
```

Java knows:

*   Which method to execute
*   What parameters and return type are expected

***

## The @FunctionalInterface Annotation

This annotation is **not mandatory**, but highly recommended.

```java
@FunctionalInterface
interface Printer {
    void print(String message);
}
```

### Why It Matters

✅ Compile‑time safety  
✅ Prevents accidental addition of more abstract methods  
✅ Clear intention to other developers

If someone adds another abstract method,  
👉 **Compilation fails immediately**.

***

## Real‑World Use Case: Validation Logic

Imagine validating user input in a service.

```java
@FunctionalInterface
interface Validator<T> {
    boolean validate(T input);
}
```

Usage with lambda:

```java
Validator<String> emailValidator =
        email -> email.contains("@");
```

✅ Clean  
✅ Testable  
✅ Reusable

This pattern is everywhere in real systems.

***

## Built‑in Functional Interfaces (Very Important)

Java 8 provides many **ready‑to‑use functional interfaces** so you don’t need to create your own.

### Most Common Ones

| Interface       | Purpose        | Method      |
| --------------- | -------------- | ----------- |
| Predicate<T>    | Test condition | test(T t)   |
| Function\<T, R> | Transform data | apply(T t)  |
| Consumer<T>     | Consume data   | accept(T t) |
| Supplier<T>     | Supply data    | get()       |

***

## Predicate – Real‑World Example

```java
Predicate<User> isActiveUser =
        user -> user.isActive();
```

Used heavily in:

*   Filtering data
*   Business rules
*   Stream APIs

***

## Function – Data Transformation

```java
Function<User, String> getEmail =
        user -> user.getEmail();
```

Often used in:

*   Mapping
*   Data pipelines
*   DTO conversions

***

## Consumer – Performing Actions

```java
Consumer<String> logger =
        msg -> System.out.println(msg);
```

Used in:

*   Logging
*   Notifications
*   Iterations (`forEach`)

***

## Supplier – Lazy Data Creation

```java
Supplier<UUID> uuidSupplier =
        () -> UUID.randomUUID();
```

Used when:

*   Value should be generated on demand
*   Expensive objects are delayed

***

## Functional Interfaces + Streams = Power Combo

```java
orders.stream()
      .filter(order -> order.isCompleted())
      .map(Order::getAmount)
      .forEach(System.out::println);
```

Behind the scenes:

*   `filter` → Predicate
*   `map` → Function
*   `forEach` → Consumer

This is **everyday production Java**, not theory.

***

## Common Mistakes Developers Make

❌ Creating custom functional interfaces unnecessarily  
❌ Ignoring built‑in functional interfaces  
❌ Adding more than one abstract method  
❌ Overcomplicating simple lambdas

### Rule of Thumb

> If Java already provides a functional interface, **use it**.

***

## Why Functional Interfaces Matter So Much

Without functional interfaces:

*   Lambdas don’t work
*   Streams don’t work
*   Functional programming fails

They are the **invisible backbone of Java 8**.

***

## Key Takeaway

✅ Functional interfaces define lambda contracts  
✅ Exactly one abstract method is allowed  
✅ Java provides rich built‑in interfaces  
✅ Essential for Streams and functional programming

> **Lambdas express behavior.  
> Functional Interfaces give them meaning.**

