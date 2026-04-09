
# Method References – Real Java 8 Examples

Method references are a **cleaner, more expressive form of lambda expressions**.

They are used when a lambda’s only job is to:
- Call an existing method
- Pass arguments through

This document shows **real‑world method reference usage** as seen in production systems.

---

## Example 1: Printing Values (Most Common Case)

### Using Lambda
```java
users.forEach(user -> System.out.println(user));
````

***

### Using Method Reference

```java
users.forEach(System.out::println);
```

✅ Less noise  
✅ Clear intent  
✅ Very common in real code

***

## Example 2: Replacing Simple Mapping Lambdas

### Scenario

Extract user emails.

***

### Using Lambda

```java
List<String> emails =
        users.stream()
             .map(user -> user.getEmail())
             .collect(Collectors.toList());
```

***

### Using Method Reference

```java
List<String> emails =
        users.stream()
             .map(User::getEmail)
             .collect(Collectors.toList());
```

✅ Cleaner  
✅ Reads naturally  
✅ Preferred in modern Java code

***

## Example 3: Method Reference with forEach (Service Calls)

### Scenario

Send email to users.

***

### Using Lambda

```java
users.forEach(user -> emailService.send(user));
```

***

### Using Method Reference

```java
users.forEach(emailService::send);
```

✅ Common in service layers  
✅ Improves readability

***

## Example 4: Static Method Reference

### Scenario

Validate numbers.

```java
numbers.stream()
       .map(Math::abs)
       .forEach(System.out::println);
```

Equivalent lambda:

```java
number -> Math.abs(number);
```

✅ Used in:

*   Utility logic
*   Validation
*   Calculations

***

## Example 5: Instance Method of Arbitrary Object

### Scenario

Convert strings to uppercase.

```java
names.stream()
     .map(String::toUpperCase)
     .collect(Collectors.toList());
```

Equivalent lambda:

```java
name -> name.toUpperCase();
```

✅ Very common in stream pipelines

***

## Example 6: Constructor Method Reference

### Scenario

Create objects in streams.

```java
List<User> users =
        names.stream()
             .map(User::new)
             .collect(Collectors.toList());
```

Equivalent lambda:

```java
name -> new User(name);
```

✅ Clean object creation  
✅ Useful for DTO mapping

***

## Example 7: Comparator Using Method Reference

### Scenario

Sort users by name.

```java
users.sort(Comparator.comparing(User::getName));
```

Without method reference:

```java
users.sort((u1, u2) ->
        u1.getName().compareTo(u2.getName()));
```

✅ Preferred style  
✅ Used extensively in real code

***

## When Method References Improve Code

✅ Lambda only calls a method  
✅ No extra logic inside lambda  
✅ Readability improves

***

## When NOT to Use Method References

❌ When side effects are hidden  
❌ When logic becomes unclear  
❌ When debugging readability suffers

Bad example:

```java
orders.forEach(orderService::processOrder); // unclear behavior
```

Sometimes lambdas are better.

***

## Method References in Frameworks

You will see method references everywhere in:

*   Spring Boot services
*   Stream pipelines
*   Java Collections
*   Repository logic
*   Event handling

Understanding them is **mandatory for reading modern Java code**.

***

## Key Takeaways

✅ Method references reduce lambda noise  
✅ Improve readability  
✅ Keep stream pipelines clean  
✅ Preferred in professional Java code

> **If a lambda only calls a method,
> a method reference is usually better.**
