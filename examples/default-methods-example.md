
# Default Methods – Real Java 8 Examples

Default methods were introduced to solve a **serious backward‑compatibility problem**
that Java developers faced for years.

This document shows **how default methods are used in real projects**, not theory.

---

## Example 1: The Breaking Change Problem (Before Java 8)

### Scenario
An interface is used by many classes.

```java
interface PaymentService {
    void processPayment();
}
````

Multiple implementations exist:

```java
class CardPaymentService implements PaymentService {
    public void processPayment() {
        System.out.println("Card payment");
    }
}
```

Now the business asks for **refund support**.

***

### ❌ Adding a Method (Before Java 8)

```java
interface PaymentService {
    void processPayment();
    void refund();
}
```

Result:
❌ All implementations break  
❌ Massive refactoring needed  
❌ Risky deployment

***

## Example 2: Java 8 Solution Using Default Method

```java
interface PaymentService {
    void processPayment();

    default void refund() {
        System.out.println("Refund not supported");
    }
}
```

✅ Existing classes continue working  
✅ New functionality added safely  
✅ No breaking changes

***

## Example 3: Real‑World Use Case (Logging Support)

### Scenario

Add logging behavior to multiple services.

```java
interface Logger {
    void log(String message);

    default void logWithTime(String message) {
        System.out.println(
            LocalDateTime.now() + " :: " + message
        );
    }
}
```

Usage:

```java
class OrderService implements Logger {
    public void log(String message) {
        System.out.println(message);
    }
}
```

✅ No code duplication  
✅ Clean extension

***

## Example 4: Overriding Default Method

Default methods are **optional** to override.

```java
class AdvancedPaymentService implements PaymentService {
    @Override
    public void refund() {
        System.out.println("Refund processed");
    }

    public void processPayment() {
        System.out.println("Payment processed");
    }
}
```

✅ Default behavior overridden  
✅ Custom logic applied

***

## Example 5: Multiple Interfaces Conflict

### Scenario

Two interfaces define the same default method.

```java
interface A {
    default void show() {
        System.out.println("A");
    }
}

interface B {
    default void show() {
        System.out.println("B");
    }
}
```

A class implementing both must decide:

```java
class C implements A, B {
    @Override
    public void show() {
        A.super.show();
    }
}
```

✅ Java avoids ambiguity  
✅ Decision is explicit

***

## Example 6: Default Methods in Java Collections (Real Usage)

You already use default methods daily:

```java
list.forEach(System.out::println);
```

`forEach` is a **default method** in the `Iterable` interface.

Without default methods:
❌ Streams could not integrate with collections  
❌ Lambdas would not work smoothly

***

## When Default Methods Should Be Used

✅ Adding new behavior to an existing interface  
✅ Maintaining backward compatibility  
✅ Library or framework evolution

***

## When NOT to Use Default Methods

❌ To store state  
❌ For complex business logic  
❌ As a replacement for inheritance

Default methods are for **extension**, not **design shortcuts**.

***

## Real‑World Impact of Default Methods

Default methods allow:
✅ Java APIs to evolve  
✅ Frameworks to add features safely  
✅ Large systems to upgrade peacefully

They made **Java 8 itself possible**.

***

## Common Mistakes Developers Make

❌ Heavy logic inside default methods  
❌ Ignoring method conflicts  
❌ Treating default methods like abstract classes

Keep default methods:
✅ Simple  
✅ Small  
✅ Focused

***

## Key Takeaways

✅ Default methods prevent breaking changes  
✅ Enable safe interface evolution  
✅ Widely used in Java core libraries  
✅ Essential for Java 8 features

> **Default methods protect large systems from accidental breakage.**
