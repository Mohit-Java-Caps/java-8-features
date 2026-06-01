package com.mohit.java8.streams;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * StreamsDeepDive — Every Java 8 Stream operation you need to know.
 *
 * WHAT STREAMS ARE:
 * ─────────────────────────────────────────────────────────────────────
 *  A Stream is a pipeline of operations on a sequence of elements.
 *  It is NOT a data structure — it doesn't store elements.
 *  It processes elements lazily — intermediate ops run only when
 *  a terminal operation is called.
 *
 *  Source → Intermediate Ops (lazy) → Terminal Op (triggers execution)
 *
 * LAZY EVALUATION EXAMPLE:
 *  stream.filter(x -> x > 5)    ← nothing runs yet
 *        .map(x -> x * 2)       ← nothing runs yet
 *        .findFirst()            ← NOW it runs, but only as far as needed
 *
 * Author: Mohit Kumar — github.com/Mohit-Java-Caps
 */
public class StreamsDeepDive {

    record Employee(String name, String dept, double salary, int yearsExp) {}

    static List<Employee> sampleEmployees() {
        return List.of(
            new Employee("Alice",   "Engineering", 120000, 8),
            new Employee("Bob",     "Engineering", 95000,  3),
            new Employee("Charlie", "Marketing",   75000,  5),
            new Employee("Diana",   "Engineering", 140000, 12),
            new Employee("Eve",     "Marketing",   82000,  7),
            new Employee("Frank",   "HR",          65000,  2),
            new Employee("Grace",   "HR",          70000,  4),
            new Employee("Henry",   "Engineering", 110000, 6)
        );
    }

    public static void main(String[] args) {
        List<Employee> employees = sampleEmployees();

        System.out.println("=== Java 8 Streams — Complete Demo ===\n");

        // ── 1: filter + map + collect ──────────────────────────────────
        System.out.println("1. Senior Engineers (exp >= 6), sorted by salary:");
        List<String> seniorEngineers = employees.stream()
            .filter(e -> "Engineering".equals(e.dept()))
            .filter(e -> e.yearsExp() >= 6)
            .sorted(Comparator.comparingDouble(Employee::salary).reversed())
            .map(e -> e.name() + " ($" + (int)e.salary() + ")")
            .collect(Collectors.toList());
        seniorEngineers.forEach(e -> System.out.println("   " + e));

        // ── 2: groupingBy ─────────────────────────────────────────────
        System.out.println("\n2. Employees grouped by department:");
        Map<String, List<String>> byDept = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::dept,
                Collectors.mapping(Employee::name, Collectors.toList())
            ));
        byDept.forEach((dept, names) ->
            System.out.println("   " + dept + ": " + names));

        // ── 3: Average salary per department ──────────────────────────
        System.out.println("\n3. Average salary per department:");
        Map<String, Double> avgSalary = employees.stream()
            .collect(Collectors.groupingBy(
                Employee::dept,
                Collectors.averagingDouble(Employee::salary)
            ));
        avgSalary.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEach(e -> System.out.printf("   %-15s $%.0f%n", e.getKey(), e.getValue()));

        // ── 4: flatMap ────────────────────────────────────────────────
        System.out.println("\n4. flatMap — flatten nested lists:");
        List<List<String>> skills = List.of(
            List.of("Java", "Spring", "Kafka"),
            List.of("Python", "Django"),
            List.of("Java", "Docker", "Kubernetes")
        );
        List<String> uniqueSkills = skills.stream()
            .flatMap(Collection::stream)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println("   Unique skills: " + uniqueSkills);

        // ── 5: reduce ─────────────────────────────────────────────────
        System.out.println("\n5. reduce — total salary bill:");
        double totalSalary = employees.stream()
            .mapToDouble(Employee::salary)
            .reduce(0, Double::sum);
        System.out.printf("   Total: $%.0f%n", totalSalary);

        // ── 6: Statistics ─────────────────────────────────────────────
        System.out.println("\n6. DoubleSummaryStatistics on salaries:");
        DoubleSummaryStatistics stats = employees.stream()
            .mapToDouble(Employee::salary)
            .summaryStatistics();
        System.out.printf("   Count: %d | Min: $%.0f | Max: $%.0f | Avg: $%.0f%n",
            stats.getCount(), stats.getMin(), stats.getMax(), stats.getAverage());

        // ── 7: partitioningBy ─────────────────────────────────────────
        System.out.println("\n7. partitioningBy — above/below $100k:");
        Map<Boolean, List<String>> partition = employees.stream()
            .collect(Collectors.partitioningBy(
                e -> e.salary() >= 100_000,
                Collectors.mapping(Employee::name, Collectors.toList())
            ));
        System.out.println("   Above $100k: " + partition.get(true));
        System.out.println("   Below $100k: " + partition.get(false));

        // ── 8: Lazy evaluation proof ───────────────────────────────────
        System.out.println("\n8. Lazy evaluation — stops as soon as result found:");
        Optional<Employee> firstHigh = employees.stream()
            .peek(e -> System.out.println("   Evaluating: " + e.name()))
            .filter(e -> e.salary() > 130_000)
            .findFirst(); // stops after finding the first match
        System.out.println("   Found: " + firstHigh.map(Employee::name).orElse("none"));

        // ── 9: Collectors.joining ─────────────────────────────────────
        System.out.println("\n9. Collectors.joining:");
        String names = employees.stream()
            .map(Employee::name)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println("   " + names);

        // ── 10: Top N per group (interview favourite) ──────────────────
        System.out.println("\n10. Top earner per department:");
        employees.stream()
            .collect(Collectors.groupingBy(
                Employee::dept,
                Collectors.maxBy(Comparator.comparingDouble(Employee::salary))
            ))
            .forEach((dept, emp) ->
                emp.ifPresent(e -> System.out.printf("   %-15s → %s ($%.0f)%n",
                    dept, e.name(), e.salary())));

        System.out.println("\n=== Key Interview Points ===");
        System.out.println("  Streams are lazy — intermediate ops don't run until terminal op");
        System.out.println("  Streams are single-use — can't reuse after terminal op");
        System.out.println("  Use parallelStream() carefully — not always faster (has overhead)");
        System.out.println("  Collectors.groupingBy is the most versatile collector to master");
    }
}
