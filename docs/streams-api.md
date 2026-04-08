
# Streams API – Thinking in Data Pipelines, Not Loops

The Streams API is the **most powerful and widely used feature of Java 8**.

Streams fundamentally change how you process data in Java:
- You stop thinking in terms of *loops and counters*
- You start thinking in terms of *data flow and transformations*

If Lambda expressions are the **foundation**,  
Streams are the **engine** of Java 8.

---

## The Core Problem Streams Solve

Before Java 8, data processing involved:
- Nested loops
- Temporary variables
- Mutable collections
- Error‑prone logic

### Real‑World Problem (Before Java 8)

Imagine an application that:
- Stores a list of orders
- Needs only completed orders
- Wants total revenue

```java
int total = 0;

for (Order order : orders) {
    if (order.isCompleted()) {
        total += order.getAmount();
    }
}
````

✅ Works  
❌ Logic mixed with state changes  
❌ Hard to parallelize  
❌ Hard to modify later

***

## Java 8 Solution: Streams API

The same logic using Streams:

```java
int total = orders.stream()
                  .filter(order -> order.isCompleted())
                  .mapToInt(Order::getAmount)
                  .sum();
```

✅ Clear intention  
✅ No mutation  
✅ Easy to read  
✅ Safe and maintainable

This is the **real power** of Streams.

***

## What Is a Stream?

A **Stream** is:

*   Not a data structure
*   Not a collection
*   Not something that stores data

A Stream is a **pipeline of operations** applied to data.

### Important Principle

> **Streams do not modify collections.  
> They transform data into new results.**

***

## How Streams Work (Mental Model)

Think of Streams like an **assembly line**:

    Source → Filter → Transform → Result

Example:

```java
orders.stream()
      .filter(order -> order.isCompleted())
      .map(Order::getCustomer)
      .forEach(System.out::println);
```

Each step:

*   Does one thing
*   Is easy to reason about
*   Can be reordered or modified safely

***

## Real‑World Stream Operations

### 1️⃣ filter – Apply Business Rules

```java
orders.stream()
      .filter(order -> order.getAmount() > 10000);
```

Used for:

*   Business validations
*   User filtering
*   Search results

***

### 2️⃣ map – Transform Data

```java
orders.stream()
      .map(Order::getAmount);
```

Used for:

*   DTO conversion
*   Extracting fields
*   Data reshaping

***

### 3️⃣ forEach – Perform Final Action

```java
users.stream()
     .forEach(user -> emailService.send(user));
```

Used for:

*   Notifications
*   Logging
*   Side effects (carefully)

***

## Streams API in Real Projects

Streams are heavily used in:

*   Service layer logic
*   Repository result processing
*   Reporting modules
*   Data transformation pipelines

Example from real applications:

```java
users.stream()
     .filter(User::isActive)
     .map(User::getEmail)
     .distinct()
     .collect(Collectors.toList());
```

This is **production‑level Java**, not academic code.

***

## Intermediate vs Terminal Operations

### Intermediate Operations

*   filter()
*   map()
*   distinct()
*   sorted()

They:
✅ Return a stream  
✅ Are lazy

***

### Terminal Operations

*   forEach()
*   collect()
*   reduce()
*   sum()

They:
✅ Trigger execution  
✅ Produce a result

Important:

> **Nothing executes until a terminal operation is called.**

***

## Laziness – A Powerful Feature

Streams use **lazy evaluation**.

That means:

*   Operations are evaluated only when needed
*   Performance is optimized automatically

Example:

```java
orders.stream()
      .filter(order -> {
          System.out.println("Filtering");
          return order.isCompleted();
      })
      .findFirst();
```

Only as many elements as required are processed.

***

## Parallel Streams (Use Carefully)

Java 8 makes parallel processing easy:

```java
orders.parallelStream()
      .filter(order -> order.isCompleted())
      .forEach(System.out::println);
```

✅ Uses multiple CPU cores  
❌ Requires care with shared state

### Rule of Thumb

> Use parallel streams **only when operations are stateless and expensive**.

***

## Common Mistakes with Streams

❌ Modifying collections inside streams  
❌ Overusing `forEach`  
❌ Mixing imperative and functional styles  
❌ Using streams just for the sake of it

Streams are meant to:

> **Express transformations, not side effects.**

***

## Why Streams Matter in Modern Java

Streams:
✅ Make code readable  
✅ Reduce bugs from mutation  
✅ Enable easy parallel processing  
✅ Encourage functional thinking

Frameworks like Spring assume:

> **You are comfortable with Streams.**

***

## Key Takeaway

✅ Streams express data transformations clearly  
✅ Streams replace loops with intent  
✅ Streams work seamlessly with lambdas  
✅ Streams are central to Java 8 mastery

> **If your Java code still relies heavily on loops,  
> you are not using Java 8 to its full potential.**

***

## What’s Next

Streams often deal with data that **may or may not be present**.

That leads to a common Java pain point:
➡️ **NullPointerException**

Java 8 introduced a dedicated solution for this:

📄 **`docs/optional.md`**
