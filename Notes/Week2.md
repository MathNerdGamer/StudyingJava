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