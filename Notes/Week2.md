# Week 2

* An array in Java is declared as `Type[] identifier;` or as `Type identifier[];`

* The variable `identifier` above is a reference variable that points to the array on the heap.

* To create the array, we write `new Type[size]`, where `size` is a non-negative integer. This allocates an array of `size` `Type`s on the heap.

* The length of the array is fixed when allocated, and indexing starts at `0`.

* When an array is allocated, the elements are initialized to a default value based on its type:
    * `int` (and similar) types initialize to `0`.
    * `float`/`double` types initialize to `0.0`.
    * `boolean` types initialize to `false`.
    * Reference variables initialize to a `null` reference.

* Instead of using `new`, an array may be initialized using a literal array. For example, `double[] probabilities = { 0.1, 0.2, 0.3, 0.4 };` initializes an array of size `4` with the specified values. This can also be done on multiple lines:
    ```java
    double[] probabilities;
    probabilities = { 0.1, 0.2, 0.3, 0.4 };
    ```

* Arrays come with a `length` property that holds the number of elements in the array.

* There is a `for-each` loop in Java that allows for looping on items in a container without having to index. The loop variables are aliases, however, so any modifications are non-permanent. For example:
    ```java
    String[] words = { "these", "are", "some", "words" };

    for (String word : words) {
        word = word.toUppercase();
    }
    System.out.println(words[0]);
    // This prints "these" instead of "THESE"
    ```

* 2D arrays in Java are actually arrays of reference variables that each point to an array. This is in contrast to C or FORTRAN, where 2D arrays are flattened to 1D arrays in [row-/column-major order](https://en.wikipedia.org/wiki/Row-_and_column-major_order) (respectively).

* 2D arrays may be declared by `double[][] distributions = new double[5][4];`

* 2D arrays may also be initialized with a literal array:
    ```java
    double[][] distributions = {{0.10, 0.20, 0.30, 0.40},
                                {0.25, 0.25, 0.25, 0.25}
                                {0.00, 0.00, 1.00, 0.00},
                                {0.20, 0.30, 0.50, 0.00},
                                {0.50, 0.50, 0.00, 0.00}};
    ```

* Java also allows 2D arrays where not every row has the same number of elements, so-called "ragged" or "jagged" arrays. These can be declared in either of the following two ways:
    ```java
    double[][] myDoubles = new double[2][];
    myDoubles[0] = new double[3];
    myDoubles[1] = new double[2];
    // and then initialize

    // OR, via literal arrays:
    double[][] myDoubles = {{0.1, 1.1, 2.1},
                            {9.1, 3.1}};
    ```

* Similar things can be said and done with arrays of higher dimensions.

* Methods can't be given default parameters.

* A `public static` method can be directly called from other programs, as long as either the class file for that method is in the same directory, or the package containing it has been imported.

* Methods may be overloaded, as long as the function signatures are different.

* Class variables are individually given `public` or `private` modifiers, rather than using a `public:` or `private:` label like in C++.

* **Java has no operator overloading.** This is annoying to me, as I like the syntactic sugar it can provide when working with mathematical objects. As such, I probably would not find myself daily driving Java.

* The `static` modifier on a member variable within a class allows for it to only take up as much memory as is necessary for one instance.

* Constructors may be overloaded and can be chained using `this()` so that code doesn't get repeated (DRY - Don't Repeat Yourself). When `this()` is used, it must be the first line.

* As an object-oriented programming language, Java supports inheritance, which allows for the transferal of attributes and behaviors from one class, the *superclass* or *parent class*, to another, the *subclass* or *child class*.

* The `Object` class is the parent of any class that isn't the child of another class.

* Another modifier for members of a class is `protected`, which can be thought of as between `private` and `public`. A `protected` member is inaccessible outside of a package, like a `private` member. However, `protected` members can be inherited, while `private` members cannot.

* The following table shows where members can be accessed with each visibility modifier:

| Modifier     | Class        | Package      | Subclass     | World        |
| :----------: | :----------: | :----------: | :----------: | :----------: |
| `public`     | ✓           | ✓            | ✓            | ✓           |
| `protected`  | ✓           | ✓            | ✓            | ✗           |
| none/default | ✓           | ✓            | ✗            | ✗           |
| `private`    | ✓           | ✗            | ✗            | ✗           |

* To declare a subclass, the `extends` clause must follow the `class` name. For example, if we have a class `Canine` from which we want to derive a subclass `Dog`, we would write:
```java
class Dog extends Canine {
    // Code here.
}
```

* When constructing a child class, `super()` called the constructor of the parent class. If a call to `super()` isn't the first line of the method, Java automatically attempts to call a parameterless constructor for the parent class.

* The `final` modifier on a method prevents it from being overridden.

* The `final` modifier on a class prevents and further subclasses of that class.

* The `abstract` modifier on a method allows for the declaration of that method without definition. Whenever such a method is in a class, the class itself must be declared `abstract`.

* The convention for modifiers like `final` and `abstract` is for them to be placed after visibility modifiers.

* Including an `abstract` method makes sense when there is no real default behavior for that method, but it should, nonetheless, be included in any subclasses that aren't `abstract`.

* The `Object` class has many methods that can be overridden, which can be read about in [the docs](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/lang/Object.html). When overriding these methods, use `instanceof` to ensure that the object is actually of the correct type. For an example, see [the Matrix implementation](../Matrix/Matrix.java).