
# Lambda Expressions – Real Java 8 Examples

This document contains **real, practical Java 8 lambda examples**
that you will see in **production code**, interviews, and daily development.

These are not toy examples — each one solves a real problem.

---

## Example 1: Runnable (The Classic Java 8 Entry Point)

### Before Java 8
```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Processing order");
    }
};

new Thread(task).start();
````

❌ Too verbose  
❌ Boilerplate hides intent

***

### Java 8 Using Lambda

```java
Runnable task = () -> System.out.println("Processing order");
new Thread(task).start();
```

✅ Clear intent  
✅ Less code  
✅ Easy to read

***

## Example 2: Sorting a List

### Scenario

You want to sort users by name.

***

### Before Java 8

```java
Collections.sort(users, new Comparator<User>() {
    @Override
    public int compare(User u1, User u2) {
        return u1.getName().compareTo(u2.getName());
    }
});
```

***

### Java 8 Using Lambda

```java
Collections.sort(users,
        (u1, u2) -> u1.getName().compareTo(u2.getName()));
```

✅ Business logic is visible  
✅ Comparator boilerplate removed

***

## Example 3: Using Lambda with forEach

### Scenario

Print all usernames.

```java
users.forEach(user -> System.out.println(user.getName()));
```

✅ Clean  
✅ No explicit loop  
✅ Common in real codebases

***

## Example 4: Filtering Data (Very Real‑World)

### Scenario

Get all active users.

```java
List<User> activeUsers =
        users.stream()
             .filter(user -> user.isActive())
             .collect(Collectors.toList());
```

What the lambda does:

```java
user -> user.isActive()
```

✅ Clearly expresses business rule  
✅ Easy to modify

***

## Example 5: Lambda with Business Logic

### Scenario

Apply discount only if amount is high.

```java
Function<Integer, Integer> discountCalculator =
        amount -> {
            if (amount > 10_000) {
                return amount - 1000;
            }
            return amount;
        };
```

✅ Lambda can have multiple statements  
✅ Use braces `{}` when logic grows

***

## Example 6: Lambda as Method Parameter (Very Common)

### Scenario

Validate input dynamically.

```java
Predicate<String> isEmailValid =
        email -> email.contains("@") && email.contains(".");
```

Usage:

```java
if (isEmailValid.test("test@example.com")) {
    System.out.println("Valid email");
}
```

✅ Reusable  
✅ Testable  
✅ Clean design

***

## Example 7: Lambda in Streams (Production Style)

```java
orders.stream()
      .filter(order -> order.getAmount() > 5_000)
      .map(order -> order.getCustomerName())
      .forEach(name -> System.out.println(name));
```

Each lambda does **one simple thing**:

*   Filter
*   Transform
*   Consume

This is **modern Java**.

***

## When NOT to Use Lambda (Important)

❌ When logic is complex  
❌ When readability suffers  
❌ When debugging becomes hard

Bad example:

```java
orders.forEach(o -> {
    // too much logic here
});
```

✅ Extract to a method instead.

***

## Best Practices for Lambdas

✅ Keep lambdas small  
✅ Prefer readability over cleverness  
✅ Use method references when lambda only calls a method  
✅ One responsibility per lambda

***

## Key Takeaways from These Examples

✅ Lambdas replace anonymous classes  
✅ Lambdas express behavior clearly  
✅ Lambdas are everywhere in Java 8+ code  
✅ Lambdas enable Streams and modern APIs

> **If your Java code does not use lambdas,
> you are not really writing Java 8.**

## What’s Next

So far, we’ve seen lambdas in isolation.

Next, we will see how they **power real data pipelines**
using the **Streams API**.
