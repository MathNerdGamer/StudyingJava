# Week 3

* An `interface` allows for a specification of a certain sets of behaviors, similar to how `abstract` class. Any methods defined in an `interface` *must* be defined by concrete classes that implement it.

* One difference is that a class can implement multiple `interface`s, but can only extend one `class`.

* All methods in an `interface` are implicitly `abstract`, and all constants are implicitly `public static final`.

* A class `D` that extends a class `A` and implements interfaces `B` and `C` is defined as follows:
    ```java
    public class D extends A implements B, C {
        // Definition of D here.
    }
    ```

* The `Comparable` interface allows for a generic way to define a sorting method only once for any class that implements `Comparable`.

* The `compareTo()` method from the `Comparable` interface returns a negative number if the calling object is less than the parameter, a positive number if greater, and 0 if the calling object is equal to the parameter.

* Consider a class `A` that implements `Comparable` where we use a member `int value` to order. One way to implement the `compareTo()` is as follows:
    ```java
    public class A implements Comparable {
        private int value;

        // ...

        public int compareTo(Object o) {
            return value - ((A)o).getValue();
        }

        // ...
    }
    ```

* An issue with the above code is that it doesn't have any way to give compile-time errors when there's a type mismatch. For example, given `A someVar = new A();`, we could call `someVar.compareTo("Hello!");`. This won't give an error when compiled, but will when run, which can cause issues with finding the problem if that line isn't called often.

* Java has generic types similar to templates from C++, with a similar syntax. In particular, the interface `Comparable` is actually `Comparable<T>` with a type parameter `T`. This helps turn the runtime error above into a compile-time error, since we can enforce our `compareTo()` method to take only objects that can cast to `A`. Using the following code, `someVar.compareTo("Hello!");` will give a compile-time error:

    ```java
    public class A implements Comparable {
        private int value;

        // ...

        public int compareTo(A a) {
            return value - a.getValue();
        }

        // ...
    }
    ```

* We can import `java.utils.Arrays` to use Java's implementation of Timsort using `Arrays.sort()`.

* We can also use [our own implementations of sorting algorithms](../Sorting/Sorting.java).

* When comparing algorithms, one typically uses some metric like runtime and space, and measures them in the worst case. These metrics are typically simplified to counting some operation(s), such as comparisons when using comparison-based sorting algorithms.

* Empirical results show that merge Sort is often much faster than selection sort, and this can also be seen by analyzing them theoretically: Selection sort uses a number of comparisons that grows roughly *quadratically* with the input size, while merge sort uses a *quasilinear* number of comparisons.

* One can get a very quick estimate for running times from code by, essentially, counting how many nested loops there are.