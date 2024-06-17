package oy.tol.tra;

import java.util.Comparator;
import java.util.function.Predicate;

public class Algorithms {

    // Sorts an array of comparable elements using bubble sort
    public static <T extends Comparable<T>> void sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    // Reverses an array
    public static <T> void reverse(T[] array) {
        int n = array.length;
        for (int i = 0; i < n / 2; i++) {
            swap(array, i, n - i - 1);
        }
    }

    // Swaps two elements in an array
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Performs binary search on a sorted array of comparable elements
    public static <T extends Comparable<T>> int binarySearch(T key, T[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = key.compareTo(array[mid]);
            if (cmp == 0) {
                return mid; // key found
            } else if (cmp < 0) {
                high = mid - 1; // key in left half
            } else {
                low = mid + 1; // key in right half
            }
        }
        return -1; // key not found
    }

    // Partitions an array based on a given predicate
    public static <T> int partitionByRule(T[] array, Predicate<T> rule) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            while (left <= right && rule.test(array[left])) {
                left++;
            }
            while (left <= right && !rule.test(array[right])) {
                right--;
            }
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    // Sorts an array using quicksort with a comparator
    public static <T> void sortWithComparator(T[] array, Comparator<T> comparator) {
        quickSort(array, comparator, 0, array.length - 1);
    }

    // Quicksort algorithm with a comparator
    private static <T> void quickSort(T[] array, Comparator<T> comparator, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionWithComparator(array, comparator, left, right);
            quickSort(array, comparator, left, pivotIndex - 1);
            quickSort(array, comparator, pivotIndex + 1, right);
        }
    }

    // Partitions an array with a comparator
    private static <T> int partitionWithComparator(T[] array, Comparator<T> comparator, int left, int right) {
        T pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }
}
