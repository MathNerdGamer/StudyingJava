// Selection sort.
package Sorting;

public class Sorting {
    // Selection Sort
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            T temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    // Merge Sort
    public static void mergeSort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort(int[] array, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;

            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);

            merge(array, start, mid, end);
        }
    }

    private static void merge(int[] array, int leftStart, int rightStart, int end) {
        int leftSize = rightStart - leftStart + 1;
        int rightSize = end - rightStart;

        int[] leftHalf = new int[leftSize];
        int[] rightHalf = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftHalf[i] = array[leftStart + i];
        }

        for (int i = 0; i < rightSize; i++) {
            rightHalf[i] = array[rightStart + i + 1];
        }

        int leftIdx = 0;
        int rightIdx = 0;
        int arrayIdx = leftStart;

        // Merge left/right halves in sorted order.
        while (leftIdx < leftSize && rightIdx < rightSize) {
            if (leftHalf[leftIdx] <= rightHalf[rightIdx]) {
                array[arrayIdx++] = leftHalf[leftIdx++];
            } else {
                array[arrayIdx++] = rightHalf[rightIdx++];
            }
        }

        // Pick up any leftover left/right half elements.
        while (leftIdx < leftSize) {
            array[arrayIdx++] = leftHalf[leftIdx++];
        }

        while (rightIdx < rightSize) {
            array[arrayIdx++] = rightHalf[rightIdx++];
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;

            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);

            merge(array, start, mid, end);
        }
    }

    private static <T extends Comparable<T>> void merge(T[] array, int leftStart, int mid, int end) {
        int leftSize = mid - leftStart + 1;
        int rightSize = end - mid;

        // Suppressing warning for unchecked cast from Comparable[] to T[].
        @SuppressWarnings("unchecked")
        T[] leftArray = (T[]) new Comparable[leftSize];
        @SuppressWarnings("unchecked")
        T[] rightArray = (T[]) new Comparable[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[leftStart + i];
        }
        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = array[mid + 1 + i];
        }

        int leftIdx = 0;
        int rightIdx = 0;
        int currentIdx = leftStart;

        // Merge left/right halves in sorted order.
        while (leftIdx < leftSize && rightIdx < rightSize) {
            if (leftArray[leftIdx].compareTo(rightArray[rightIdx]) <= 0) {
                array[currentIdx++] = leftArray[leftIdx++];
            } else {
                array[currentIdx++] = rightArray[rightIdx++];
            }
        }

        // Pick up any leftover left/right half elements.
        while (leftIdx < leftSize) {
            array[currentIdx++] = leftArray[leftIdx++];
        }

        while (rightIdx < rightSize) {
            array[currentIdx++] = rightArray[rightIdx++];
        }
    }

    // Insertion sort
    public static void insertionSort(int[] array) {
        for (int unsortedIdx = 1; unsortedIdx < array.length; unsortedIdx++) {
            int nextInsert = array[unsortedIdx];
            int currentIdx = unsortedIdx - 1;

            while (currentIdx >= 0 && array[currentIdx] > nextInsert) {
                array[currentIdx + 1] = array[currentIdx--];
            }

            array[currentIdx + 1] = nextInsert;
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        for (int unsortedIdx = 1; unsortedIdx < array.length; unsortedIdx++) {
            T nextInsert = array[unsortedIdx];
            int currentIdx = unsortedIdx - 1;

            while (currentIdx >= 0 && array[currentIdx].compareTo(nextInsert) > 0) {
                array[currentIdx + 1] = array[currentIdx--];
            }

            array[currentIdx + 1] = nextInsert;
        }
    }

    public static void main(String[] args) {
        System.out.println("Selection Sort!\n");
        // Selection Sort
        int[] array = {
                1, 3, 2, 9, 5, 12, 0, 7, 18, 6
        };

        System.out.println("--- Pre-sort ---");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i + 1 < array.length) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }

        selectionSort(array);

        System.out.println("\n--- Post-sort ---");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);

            if (i + 1 < array.length) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }

        String[] words = {
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

        System.out.println("\n\n--- Pre-sort ---");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

        selectionSort(words);

        System.out.println("\n\n--- Post-sort ---");
        for (int i = 0; i < words.length; i++) {
            System.out.println(words[i]);
        }

        // Merge Sort
        System.out.println("\n\nMerge Sort!\n");

        int[] array2 = {
                1, 3, 2, 9, 5, 12, 0, 7, 18, 6
        };

        System.out.println("--- Pre-sort ---");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i]);

            if (i + 1 < array2.length) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }

        mergeSort(array2);

        System.out.println("\n--- Post-sort ---");
        for (int i = 0; i < array2.length; i++) {
            System.out.print(array2[i]);

            if (i + 1 < array2.length) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }

        String[] words2 = {
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

        System.out.println("\n\n--- Pre-sort ---");
        for (int i = 0; i < words2.length; i++) {
            System.out.println(words2[i]);
        }

        mergeSort(words2);

        System.out.println("\n\n--- Post-sort ---");
        for (int i = 0; i < words2.length; i++) {
            System.out.println(words2[i]);
        }

        // Insertion Sort
        System.out.println("\n\nInsertion Sort!\n");

        int[] array3 = {
                1, 3, 2, 9, 5, 12, 0, 7, 18, 6
        };

        System.out.println("--- Pre-sort ---");
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]);

            if (i + 1 < array3.length) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }

        insertionSort(array3);

        System.out.println("\n--- Post-sort ---");
        for (int i = 0; i < array3.length; i++) {
            System.out.print(array3[i]);

            if (i + 1 < array3.length) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }

        String[] words3 = {
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

        System.out.println("\n\n--- Pre-sort ---");
        for (int i = 0; i < words3.length; i++) {
            System.out.println(words3[i]);
        }

        insertionSort(words3);

        System.out.println("\n\n--- Post-sort ---");
        for (int i = 0; i < words3.length; i++) {
            System.out.println(words3[i]);
        }
    }
}
