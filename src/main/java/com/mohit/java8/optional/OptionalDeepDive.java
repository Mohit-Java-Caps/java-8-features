package com.mohit.java8.optional;

import java.util.Optional;
import java.util.List;

/**
 * OptionalDeepDive — Correct usage, anti-patterns, and interview answers.
 *
 * WHAT OPTIONAL IS FOR:
 * ─────────────────────────────────────────────────────────────────────
 *  Optional is a RETURN TYPE for methods that may legitimately return nothing.
 *  It makes the "might be absent" case explicit in the method signature.
 *  It forces the caller to handle the absent case — eliminating NPEs.
 *
 * WHAT OPTIONAL IS NOT FOR:
 * ─────────────────────────────────────────────────────────────────────
 *  ❌ Method parameters   (use @Nullable or overloading instead)
 *  ❌ Fields in a class   (makes serialization harder, bad for JPA)
 *  ❌ Collections         (empty list is better than Optional<List>)
 *  ❌ Calling .get() directly without isPresent() check
 *
 * Author: Mohit Kumar — github.com/Mohit-Java-Caps
 */
public class OptionalDeepDive {

    record User(String id, String name, String email, Address address) {}
    record Address(String city, String country, String postCode) {}

    // ── Simulated repository ──────────────────────────────────────────────
    static Optional<User> findUserById(String id) {
        return switch (id) {
            case "u1" -> Optional.of(new User("u1", "Alice", "alice@example.com",
                new Address("London", "UK", "EC1A 1BB")));
            case "u2" -> Optional.of(new User("u2", "Bob", null,
                new Address("New York", "US", null)));
            default   -> Optional.empty();
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Optional — Correct Usage & Anti-Patterns ===\n");

        // ── 1: Basic usage ────────────────────────────────────────────
        System.out.println("1. orElse vs orElseGet:");
        String name1 = findUserById("u1").map(User::name).orElse("Unknown");
        String name2 = findUserById("u99").map(User::name).orElse("Unknown");
        System.out.println("   u1 name: " + name1);
        System.out.println("   u99 name: " + name2);

        // orElse ALWAYS evaluates the default, even when value present
        // orElseGet is lazy — only calls supplier when value absent
        System.out.println("\n   orElse (eager)   — always calls default expression");
        System.out.println("   orElseGet (lazy) — only calls supplier if empty");

        String name3 = findUserById("u1")
            .map(User::name)
            .orElseGet(() -> "Computed default: " + System.currentTimeMillis());
        System.out.println("   u1 via orElseGet: " + name3);

        // ── 2: Deep chaining with map / flatMap ───────────────────────
        System.out.println("\n2. Chaining — no null checks, no NPE:");
        String postCode1 = findUserById("u1")
            .map(User::address)
            .map(Address::postCode)
            .orElse("No postcode");

        String postCode2 = findUserById("u2")
            .map(User::address)
            .map(Address::postCode)     // postCode is null → returns Optional.empty()
            .orElse("No postcode");

        System.out.println("   u1 postcode: " + postCode1);
        System.out.println("   u2 postcode: " + postCode2 + "  (null postcode handled cleanly)");

        // ── 3: orElseThrow ─────────────────────────────────────────────
        System.out.println("\n3. orElseThrow — throw domain exception if absent:");
        try {
            User user = findUserById("u99")
                .orElseThrow(() -> new RuntimeException("User not found: u99"));
        } catch (RuntimeException e) {
            System.out.println("   Caught: " + e.getMessage());
        }

        // ── 4: ifPresent / ifPresentOrElse ────────────────────────────
        System.out.println("\n4. ifPresent / ifPresentOrElse:");
        findUserById("u1").ifPresent(u ->
            System.out.println("   Found user: " + u.name() + " in " + u.address().city())
        );
        findUserById("u99").ifPresentOrElse(
            u -> System.out.println("   Found: " + u.name()),
            () -> System.out.println("   No user found — executing fallback")
        );

        // ── 5: filter ─────────────────────────────────────────────────
        System.out.println("\n5. filter — conditional processing:");
        findUserById("u1")
            .filter(u -> u.email() != null)
            .ifPresent(u -> System.out.println("   Sending email to: " + u.email()));

        findUserById("u2")
            .filter(u -> u.email() != null) // email is null → filtered out
            .ifPresentOrElse(
                u -> System.out.println("   Sending email to: " + u.email()),
                () -> System.out.println("   No email available for u2 — skipped")
            );

        // ── 6: Anti-patterns ──────────────────────────────────────────
        System.out.println("\n6. Anti-patterns to avoid in interviews:");
        System.out.println("""
               ❌ if (optional.isPresent()) { optional.get() }
                  → defeats the purpose. Use map/orElse/ifPresent instead.
            
               ❌ Optional as method parameter
                  → void sendEmail(Optional<String> email) — BAD
                  → void sendEmail(String email) — GOOD (let caller decide)
            
               ❌ Optional field in JPA entity
                  → @Column String email; — then Optional.ofNullable(entity.getEmail())
                  → at the service layer, not the entity layer
            
               ❌ Optional.get() without isPresent()
                  → throws NoSuchElementException — defeats the point
            
               ✅ Use Optional only as a return type
               ✅ Chain with map/flatMap/filter/orElse
               ✅ Never call .get() directly
            """);
    }
}
