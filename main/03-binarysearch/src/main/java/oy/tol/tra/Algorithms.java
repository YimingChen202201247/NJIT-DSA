package oy.tol.tra;

/**
 * A simple array of student grades to be used in testing
 * misbehaving algorithm for reversing the array.
 */

public class Algorithms {

	public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
		while (fromIndex <= toIndex) {
			int mid = fromIndex + (toIndex - fromIndex) / 2;
			int cmp = aValue.compareTo(fromArray[mid]);
			if (cmp < 0) {
				toIndex = mid - 1;
			} else if (cmp > 0) {
				fromIndex = mid + 1;
			} else {
				return mid; // aValue found at index mid
			}
		}
		return -1; // aValue not found
	}

	public static <E extends Comparable<E>> void fastSort(E[] array) {
		quickSort(array, 0, array.length - 1);
	}

	public static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
		if (begin < end) {
			int partitionIndex = partition(array, begin, end);
			quickSort(array, begin, partitionIndex - 1);
			quickSort(array, partitionIndex + 1, end);
		}
	}

	private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
		E pivot = array[end];
		int i = begin - 1;
		for (int j = begin; j < end; j++) {
			if (array[j].compareTo(pivot) <= 0) {
				i++;
				// Swap array[i] and array[j]
				E swapTemp = array[i];
				array[i] = array[j];
				array[j] = swapTemp;
			}
		}
		// Swap array[i + 1] and array[end] (or pivot)
		E swapTemp = array[i + 1];
		array[i + 1] = array[end];
		array[end] = swapTemp;
		return i + 1;
	}
}