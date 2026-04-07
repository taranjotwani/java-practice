# Java Lambda & Functional Interface Practice Problems

## Basic Lambdas

**1.** Write a lambda that takes two integers and returns their product. Assign it to a `BinaryOperator<Integer>`.

**2.** Write a lambda that checks if a string is a palindrome. Assign it to a `Predicate<String>`.

**3.** Write a lambda that converts a string to uppercase. Assign it to a `Function<String, String>`.

**4.** Write a lambda that prints "Hello, <name>!" given a name string. Assign it to a `Consumer<String>`.

**5.** Write a `Supplier<List<String>>` that returns a new empty `ArrayList` each time it's called.

---

## Chaining & Composition

**6.** Using `Function.andThen()`, chain two functions: one that trims a string, and another that converts it to lowercase.

**7.** Using `Predicate.and()`, combine two predicates: one that checks if a number is positive, and another that checks if it's even. Test it on a list of integers.

**8.** Using `Predicate.negate()`, filter out all strings longer than 5 characters from a list.

**9.** Using `Consumer.andThen()`, chain two consumers: one that prints a number, and one that prints its square.

---

## Streams with Lambdas

**10.** Given a `List<String>`, use `stream().filter().map().collect()` to return a list of strings that start with "A", converted to uppercase.

**11.** Given a `List<Integer>`, use streams to find the sum of all even numbers using `reduce()`.

**12.** Given a `List<String>`, sort the list by string length (ascending) using a lambda comparator.

**13.** Given a `List<Employee>` (with fields `name` and `salary`), use streams to find the employee with the highest salary using `max()`.

**14.** Given a `List<Integer>`, use `Collectors.groupingBy()` to group numbers into two buckets: even and odd.

---

## Custom Functional Interfaces

**15.** Define a custom functional interface `TriFunction<A, B, C, R>` that takes three inputs and returns a result. Implement it with a lambda that adds three integers.

**16.** Define a functional interface `StringTransformer` with method `String transform(String input)`. Write two lambda implementations: one that reverses the string, and one that removes all vowels.

**17.** Define a functional interface `Validator<T>` with method `boolean validate(T value)`. Use it to validate that a password string is at least 8 characters and contains a digit.

---

## Advanced

**18.** Write a method that accepts a `Function<Integer, Integer>` and applies it `n` times to an initial value (i.e., function composition via a loop). Test it by doubling a number 4 times.

**19.** Given a `List<List<Integer>>`, use `flatMap()` to produce a single flattened list of all integers, then filter values greater than 10.

**20.** Implement a simple pipeline using `Function.andThen()` that: parses a string to integer → multiplies by 3 → converts back to string → appends " units". Chain all steps as lambdas.

---

## Tips
- Use `java.util.function.*` for built-in functional interfaces
- Remember: `Predicate<T>`, `Function<T,R>`, `Consumer<T>`, `Supplier<T>`, `BiFunction<T,U,R>`
- Test edge cases (nulls, empty lists, negatives)