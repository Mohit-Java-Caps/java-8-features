
# Streams API – Real Java 8 Data Processing Examples

This document shows **real‑world usage of Java 8 Streams** as you would see in
production systems, not classroom examples.

Streams help you **focus on what you want to do with data**, not how to loop over it.

---

## Example 1: Filtering Data (The Most Common Use Case)

### Scenario
You have a list of users and want **only active users**.

---

### Before Java 8
```java
List<User> activeUsers = new ArrayList<>();

for (User user : users) {
    if (user.isActive()) {
        activeUsers.add(user);
    }
}
````

❌ Boilerplate  
❌ Mutable state  
❌ Error‑prone

***

### Java 8 Streams

```java
List<User> activeUsers =
        users.stream()
             .filter(user -> user.isActive())
             .collect(Collectors.toList());
```

✅ Clear intent  
✅ No mutation  
✅ Easy to modify

***

## Example 2: Mapping Data (Very Real‑World)

### Scenario

Extract email IDs from user objects.

```java
List<String> emails =
        users.stream()
             .map(User::getEmail)
             .collect(Collectors.toList());
```

This is used everywhere:

*   Notifications
*   Reports
*   API responses

***

## Example 3: Filter + Map Together (Daily Production Code)

### Scenario

Send emails only to active users.

```java
users.stream()
     .filter(User::isActive)
     .map(User::getEmail)
     .forEach(emailService::send);
```

✅ Reads like a sentence  
✅ Very common in service layers

***

## Example 4: Aggregation (Summing Amounts)

### Scenario

Calculate total order amount for completed orders.

***

### Before Java 8

```java
int total = 0;

for (Order order : orders) {
    if (order.isCompleted()) {
        total += order.getAmount();
    }
}
```

***

### Java 8 Streams

```java
int total =
        orders.stream()
              .filter(Order::isCompleted)
              .mapToInt(Order::getAmount)
              .sum();
```

✅ No temporary variables  
✅ No side effects  
✅ Easy to parallelize

***

## Example 5: Finding Data (Short‑Circuiting)

### Scenario

Find the first high‑value order.

```java
Optional<Order> order =
        orders.stream()
              .filter(o -> o.getAmount() > 10_000)
              .findFirst();
```

✅ Stops processing once found  
✅ Efficient and expressive

***

## Example 6: Grouping Data (Reporting Use Case)

### Scenario

Group orders by status.

```java
Map<OrderStatus, List<Order>> ordersByStatus =
        orders.stream()
              .collect(Collectors.groupingBy(Order::getStatus));
```

Used in:

*   Dashboards
*   Reports
*   Analytics

***

## Example 7: Removing Duplicates

### Scenario

Get unique customer emails.

```java
List<String> uniqueEmails =
        users.stream()
             .map(User::getEmail)
             .distinct()
             .collect(Collectors.toList());
```

✅ No extra logic  
✅ No Sets needed

***

## Example 8: Sorting Data Using Streams

### Scenario

Sort users by name.

```java
List<User> sortedUsers =
        users.stream()
             .sorted(Comparator.comparing(User::getName))
             .collect(Collectors.toList());
```

✅ Very clean  
✅ Easy to read

***

## Example 9: Parallel Streams (Use Carefully)

### Scenario

Process large dataset in parallel.

```java
orders.parallelStream()
      .filter(Order::isCompleted)
      .forEach(order -> process(order));
```

✅ Uses multiple cores  
❌ Avoid shared mutable state

***

## When NOT to Use Streams

❌ Small simple loops with obvious intent  
❌ Heavy side‑effects  
❌ Complex debugging requirements

Streams are for:
✅ Declarative transformations  
✅ Readable pipelines  
✅ Functional style logic

***

## Best Practices for Streams

✅ Keep stream pipelines simple  
✅ Avoid `forEach` for core logic  
✅ Prefer `map`, `filter`, `collect`  
✅ Write readable pipelines (not clever ones)

***

## How Streams Are Used in Real Projects

Streams appear heavily in:

*   Spring Boot services
*   Repository result handling
*   Reporting logic
*   Batch processing
*   Microservices

If you work on modern Java code, **Streams are everywhere**.

***

## Key Takeaways

✅ Streams replace loops with intent  
✅ Streams make data processing readable  
✅ Streams avoid mutable state  
✅ Streams enable easy parallelism

> **If you understand Streams well,
> you understand modern Java.**

***

## What’s Next

Streams often return data that **may or may not exist**.

To handle this safely, Java 8 introduced:

➡️ **Optional – real usage examples**
