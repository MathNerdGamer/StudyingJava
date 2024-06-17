// Basic (Generic) Linked List implementation.
package LinkedList;

public class LinkedList<T> {
    private class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this(data, null);
        }

        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    LinkedList(T data) {
        addToFront(data);
        size = 1;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void addToFront(T data) {
        head = new Node<T>(data, head);

        if (tail == null) {
            tail = head;
        }

        size++;
    }

    public void addToRear(T data) {
        Node<T> newNode = new Node<T>(data);
        size++;

        if (isEmpty()) {
            head = newNode;
            tail = head;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    public T removeFromFront() {
        if (isEmpty()) {
            return null;
        }

        if (head == tail) {
            tail = null;
        }

        T data = head.data;
        head = head.next;

        size--;

        return data;
    }

    public T removeFromRear() {
        if (isEmpty()) {
            return null;
        }

        size--;

        if (head == tail) {
            T data = head.data;
            head = null;
            tail = null;

            return data;
        }

        Node<T> currentNode = head;

        while (currentNode.next.next != null) {
            currentNode = currentNode.next;
        }

        T data = currentNode.next.data;
        currentNode.next = null;
        tail = currentNode;

        return data;
    }

    public T getAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index " + index + " is out of bounds.");
        }

        Node<T> currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.data;
    }

    public void addAtIndex(T data, int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index " + index + " is out of bounds.");
        } else if (index == 0) {
            addToFront(data);
            return;
        } else if (index == size - 1) {
            addToRear(data);
            return;
        }

        Node<T> currentNode = head;

        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next;
        }

        Node<T> newNode = new Node<T>(data, currentNode.next);

        if (currentNode == tail) {
            tail = currentNode;
        }

        currentNode.next = newNode;

        size++;
    }

    public T removeAtIndex(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Index " + index + " is out of bounds.");
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromRear();
        }

        Node<T> currentNode = head;

        for (int i = 0; i < index - 1; i++) {
            currentNode = currentNode.next;
        }

        T data = currentNode.next.data;
        currentNode.next = currentNode.next.next;
        size--;

        return data;
    }

    public T remove(T data) {
        if (isEmpty()) {
            return null;
        } else if (data.equals(head.data)) {
            return removeFromFront();
        }

        Node<T> currentNode = head;

        while (currentNode.next != null && !data.equals(currentNode.next.data)) {
            currentNode = currentNode.next;
        }

        if (currentNode.next == null) {
            return null;
        }

        if (data.equals(currentNode.next.data)) {
            size--;
            currentNode.next = currentNode.next.next;
        }

        return data;
    }

    public String toString() {
        Node<T> currentNode = head;
        StringBuilder result = new StringBuilder();

        while (currentNode != null) {
            result.append(currentNode.data).append("\n");
            currentNode = currentNode.next;
        }

        return result.toString();
    }

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

    public void clear() {
        head = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedList<Integer> intList = new LinkedList<Integer>(17);

        intList.addToFront(12);
        intList.addToRear(11);
        intList.addAtIndex(9, 1);
        intList.addAtIndex(10, 1);
        intList.addAtIndex(7, 2);

        System.out.println("Current List:\n" + intList);

        System.out.println("Removing item at front: " + intList.removeFromFront());
        System.out.println("Removing item at rear: " + intList.removeFromRear());

        System.out.println("Adding 111 to front and 123 to rear.\n");

        intList.addToFront(111);
        intList.addToRear(123);

        System.out.println("Current List:\n" + intList);

        if (intList.contains(7) && !intList.contains(123456789)) {
            System.out.println("contains() works.\n");
        }

        System.out.println("Item at index 3 is " + intList.getAtIndex(3) + " (indexing starts at 0).\n");

        Integer removed = intList.remove(9);

        if (removed == null) {
            System.out.println("The remove() method does not work.\n");
        } else {
            System.out.println("Explicitly removed " + removed + ".\n");
        }

        if (intList.remove(123456789) == null) {
            System.out.println("remove() correctly returns null when item to remove is not found.\n");
        } else {
            System.out.println("remove() says it removed something that wasn't there.\n");
        }

        System.out.print("Current List:\n" + intList);

        if (intList.size() == 5) {
            System.out.println("The size is correctly tracked: size = " + intList.size() + ".\n");
        } else {
            System.out.println("The size is incorrectly tracked. It should be 5, but it is " + intList.size() + ".");
        }

        if (intList.isEmpty()) {
            System.out.println("isEmpty() reporting empty when not.\n");
        } else {
            System.out.println("isEmpty() correctly indicates that the list is not empty!\n");
        }

        intList.clear();

        if (intList.isEmpty() && intList.size() == 0) {
            System.out.println("clear(), isEmpty(), and size() work!");
        } else {
            System.out.println("Size is " + intList.size() + ".");
        }
    }
}
