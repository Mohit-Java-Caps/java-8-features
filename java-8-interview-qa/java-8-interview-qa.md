
# Java 8 Interview Questions & Answers (Real‑World Focus)

This document contains **frequently asked Java 8 interview questions**
with **clear, practical answers** based on real project experience.

These answers are designed to:
- Help you explain concepts confidently
- Show understanding beyond syntax
- Match real interview expectations

---

## 1. Why was Java 8 introduced?

Java 8 was introduced to solve long‑standing problems in Java related to:

- Verbose syntax
- Difficult data processing
- Poor parallelism support
- Error‑prone date handling
- Excessive boilerplate code

Java 8 introduced **functional programming concepts** while keeping Java backward‑compatible.

👉 It focused on **writing intent‑driven code instead of loop‑driven code**.

---

## 2. What are Lambda Expressions?

Lambda expressions allow you to **pass behavior as a parameter**.

They replace anonymous inner classes with **concise, readable expressions**.

Example:
```java
(user) -> user.isActive()
````

They are heavily used in:

*   Streams
*   Callbacks
*   Event handling
*   Functional programming APIs

***

## 3. What is a Functional Interface?

A functional interface is an interface with **exactly one abstract method**.

Lambda expressions work **only with functional interfaces**.

Example:

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}
```

Java provides built‑in functional interfaces like:

*   `Predicate`
*   `Function`
*   `Consumer`
*   `Supplier`

***

## 4. Difference between Predicate, Function, and Consumer?

| Interface      | Input | Output  | Use Case           |
| -------------- | ----- | ------- | ------------------ |
| Predicate<T>   | T     | boolean | Filtering          |
| Function\<T,R> | T     | R       | Transformation     |
| Consumer<T>    | T     | void    | Performing actions |

Example:

```java
Predicate<User> isActive = user -> user.isActive();
```

***

## 5. What is the Streams API?

Streams API allows processing collections in a **functional, declarative way**.

Streams:

*   Do not store data
*   Do not modify original collections
*   Focus on transformations

Example:

```java
orders.stream()
      .filter(Order::isCompleted)
      .map(Order::getAmount)
      .sum();
```

***

## 6. Difference between Collection and Stream?

| Collection      | Stream         |
| --------------- | -------------- |
| Stores data     | Processes data |
| Eager execution | Lazy execution |
| Can be modified | Immutable      |

Streams execute **only when a terminal operation is called**.

***

## 7. What is Lazy Evaluation in Streams?

Operations like `filter()` and `map()` do not execute immediately.

Execution happens **only after a terminal operation** like:

*   `forEach`
*   `collect`
*   `findFirst`

This improves performance and reduces unnecessary computation.

***

## 8. Difference between map() and flatMap()?

*   `map()` transforms one element into one element
*   `flatMap()` flattens nested structures

Example:

```java
List<List<String>> data;
data.stream().flatMap(List::stream);
```

Used when dealing with:

*   Nested collections
*   Optional of Optional
*   Complex data pipelines

***

## 9. What is Optional and why is it important?

`Optional` represents a value that **may or may not be present**.

It solves `NullPointerException` by:

*   Making absence explicit
*   Forcing handling at compile‑time

Example:

```java
Optional<User> user = repository.findById(id);
```

Used mainly as:
✅ Return types  
❌ Not for fields or parameters

***

## 10. Difference between orElse and orElseGet?

*   `orElse()` eagerly evaluates default value
*   `orElseGet()` lazily evaluates default supplier

Use `orElseGet()` when fallback computation is expensive.

***

## 11. What are Method References?

Method references are a **compact version of lambdas** that call methods.

Example:

```java
users.forEach(System.out::println);
```

Types:

*   Static method reference
*   Instance method reference
*   Constructor reference

Used for cleaner and readable code.

***

## 12. What are Default Methods?

Default methods allow **method implementations inside interfaces**.

They were introduced to:

*   Maintain backward compatibility
*   Allow interface evolution

Example:

```java
default void log() {
    System.out.println("Logging");
}
```

Essential for:

*   Java Collections
*   Streams API

***

## 13. Can interfaces have state in Java 8?

❌ No.

Interfaces cannot have:

*   Instance fields
*   Constructors

Default methods provide behavior, **not state**.

***

## 14. What is the Java 8 Date & Time API?

The Java 8 Date & Time API (`java.time`) replaces `Date` and `Calendar`.

Key features:
✅ Immutable  
✅ Thread‑safe  
✅ Clear API design

Core classes:

*   LocalDate
*   LocalTime
*   LocalDateTime
*   ZonedDateTime
*   Instant

***

## 15. Difference between LocalDateTime and ZonedDateTime?

*   `LocalDateTime` → Date + Time (no zone)
*   `ZonedDateTime` → Date + Time + Timezone

Use `ZonedDateTime` for:

*   Global applications
*   Timezone‑aware logic

***

## 16. What is Parallel Stream?

Parallel Streams allow processing data using **multiple CPU cores**.

Example:

```java
orders.parallelStream()
      .forEach(this::process);
```

⚠️ Use carefully:

*   Avoid shared mutable state
*   Only for CPU‑heavy tasks

***

## 17. Why Java 8 Streams are preferred over loops?

Streams:
✅ Improve readability  
✅ Reduce bugs from mutation  
✅ Enable parallelism easily  
✅ Encourage functional thinking

***

## 18. Most Common Java 8 Interview Mistakes

❌ Saying “Streams are faster by default”  
❌ Using Optional everywhere  
❌ Overusing parallel streams  
❌ Writing complex lambdas

***

## 19. How Java 8 is used in real projects?

Java 8 features are used in:

*   Spring Boot services
*   Microservices
*   Data pipelines
*   Batch processing
*   REST APIs

You cannot work on modern Java code **without Java 8 knowledge**.

***

## 20. Final Interview Tip

Interviewers are not looking for:
❌ Syntax memorization

They are looking for:
✅ Clarity of thought  
✅ Real‑world understanding  
✅ Ability to explain “why”

> **Java 8 is more about mindset than syntax.**
