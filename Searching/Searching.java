package Searching;

import Sorting.Sorting;

public class Searching {
    // Linear search
    public static int linearSearch(int[] array, int item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                return i;
            }
        }

        return -1;
    }

    public static <T extends Comparable<T>> int linearSearch(T[] array, T item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].compareTo(item) == 0) {
                return i;
            }
        }

        return -1;
    }

    // Binary search (requires already-sorted array)
    public static int binarySearch(int[] array, int item) {
        int start = 0, mid = 0, end = array.length - 1;

        while (start <= end) {
            mid = start + (end - start) / 2;

            if (array[mid] == item) {
                return mid;
            } else if (array[mid] < item) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(T[] array, T item) {
        int start = 0, mid = 0, end = array.length - 1;

        while (start <= end) {
            mid = start + (end - start) / 2;
            int comparisonResult = array[mid].compareTo(item);

            if (comparisonResult == 0) {
                return mid;
            } else if (comparisonResult < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] intArray = {
                3, 9, 1, 2, 8, 7, 6
        };

        String[] stringArray = {
                "Hello",
                "World",
                "testing",
                "Testing",
                "a",
                "A",
                "Z",
                "z",
                "Not in alphabetical order!"
        };

        // Linear Search
        int intIdx = linearSearch(intArray, 2);

        if (intIdx == -1) {
            System.out.println("Item not found!");
        } else {
            System.out.println("Item found at index " + intIdx + ".");
        }

        int stringIdx = linearSearch(stringArray, "a");

        if (stringIdx == -1) {
            System.out.println("Item not found!");
        } else {
            System.out.println("Item found at index " + stringIdx + ".");
        }

        // Binary Search
        Sorting.mergeSort(intArray);

        intIdx = binarySearch(intArray, 3);

        if (intIdx == -1) {
            System.out.println("Item not found!");
        } else {
            System.out.println("Item found at index " + intIdx + ".");
        }

        Sorting.mergeSort(stringArray);

        stringIdx = binarySearch(stringArray, "a");

        if (stringIdx == -1) {
            System.out.println("Item not found!");
        } else {
            System.out.println("Item found at index " + stringIdx + ".");
        }
    }
}
