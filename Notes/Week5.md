# Week 5

* The [`List<E>` interface](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/List.html) is a parameterized type with the methods necessary to store a list of things.

* One implementation of the `List` interface is the [`ArrayList` class](https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/util/ArrayList.html), which stores data internally as a contiguous array of type `Object`. Without a type parameter, any objects can be added to the array.
    ```java
    ArrayList aList1 = new ArrayList(); // No type parameter or initial capacity provided.
    ArrayList aList2 = new ArrayList(initialCapacity); // No type parameter provided.
    ArrayList<elementType> aList3 = new ArrayList<>(); // No initial capacity provided.
    ArrayList<elementType> aList4 = new ArrayList<>(initialCapacity); //
    ```

* The `remove()` method is a *hard* removal, meaning that an item that is removed is no longer stored in any form by the `ArrayList` when the method is called.

* When a primitive type is provided to `ArrayList`, the appropriate wrapper type is used instead, a process known as *autoboxing*. For example, inserting an `int` into an `ArrayList` will, internally, instantiate an `Integer` object.

* One weakness of the `ArrayList` is that increasing the size beyond its initial capacity requires allocating a completely new internal array and copying items over. Also, decreasing the memory footprint of an `ArrayList` requires the same: allocate a new array and copy items over. Finally, if we wish to add an item in the middle of an `ArrayList`, we have to spend time copying items over to make a spot available.

* To mitigate these, we may implement a different type of `List`, called a linked list.

* A linked list is a class that operates on nodes, which are themselves classes that store data and a reference to the node that follows them. An instance of a linked list will have a reference to a special node called the *head*, where is where linked list operations begin.

* Traversing within a `LinkedList` is performed by starting from the `head` node and, in a loop, going from the current node to the next node, until either we reach a `null` node or the appropriate stopping condition occurs. For example, to determine if an element in an instance of `LinkedList`, we define the `contains()` method as follows:
    ```java
    public boolean contains(T data) {
        Node<T> currentNode = head;

        while (currentNode != null) {
            if (data.equals(currentNode.data)) {
                return true;
            }

            currentNode = currentNode.next;
        }

        return false;
    }
    ```

* One thing that an `ArrayList` does better than a `LinkedList` is accessing elements in the middle of the list. For an `ArrayList`, this is a quick random access in the internal array. On the other hand, a `get()` method for `LinkedList` would look something like this:
    ```java
    public T get(int index) {
        if (index > size) {
            throw new IllegalArgumentException("Index out of bounds.");
        }

        Node<T> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.data;
    }
    ```
    This can require traversing multiple elements before reaching the correct item.

* *Recursion* occurs when a method calls itself. This allows for certain operations to be repeated, much like how loops work.

* When a recursive method doesn't have an adequate stopping condition, the recursive process leads to a `StackOverflowError`.

* A basic example of a recursive method is the `factorial()` function:
    ```java
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
    ```

* An example of recursion in use can also be seen in the `inOrder()` method in my (basic) [BinarySearchTree](../BinarySearchTree/BinarySearchTree.java) implementation.