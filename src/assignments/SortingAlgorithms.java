package assignments;

import java.util.Arrays;
import java.util.Random;

/**
 * This program randomly generates 100,000 integers into an array with three
 * methods of sorting algorithm - insertion, selection and shell sort. This will
 * evaluate the timing of the execution of each sorting algorithm and report
 * each of them.
 * 
 * @author Maria Contado
 *
 */
public class SortingAlgorithms {

	private static final int RANGE = 100000;

	public static void main(String[] args) {
		System.out.println("Sorting 100,000 random numbers...");
		
		// Selection Sort
		 int[] selectionSortNumbers = generateRandomNumbers();
		 long timeStartedSelectionSort = System.currentTimeMillis();
		 selectionSort(selectionSortNumbers);
		 System.out.println("Selection Sort last for: " +
		 (System.currentTimeMillis() - timeStartedSelectionSort) + " ms");
		
		 // Insertion Sort
		 int[] insertionSortNumbers = generateRandomNumbers();
		 long timeStartedInsertionSort = System.currentTimeMillis();
		 insertionSort(insertionSortNumbers);
		 System.out.println("Insert Sort last for: " +
		 (System.currentTimeMillis() - timeStartedInsertionSort) + " ms");
		
		
		 // Shell Sort
		 int[] shellSortNumbers = generateRandomNumbers();
		 long timeStartedShellSort = System.currentTimeMillis();
		 shellSort(shellSortNumbers);
		 System.out.println("Shell Sort last for: " +
		 (System.currentTimeMillis() - timeStartedShellSort) + " ms");
		
		 // Merge Sort
		 int[] mergeSortNumbers = generateRandomNumbers();
		 long timeStartedMergeSort = System.currentTimeMillis();
		 mergeSort(mergeSortNumbers);
		 System.out.println("Merge Sort last for: " +
		 (System.currentTimeMillis() - timeStartedMergeSort) + " ms");
		 
		 // Quick Sort
		 int[] quickSortNumbers = generateRandomNumbers();
		 long timeStartedSQuickSort = System.currentTimeMillis();
		 quickSort(quickSortNumbers, 0, quickSortNumbers.length - 1);
		 System.out.println("Quick Sort last for: " +
		 (System.currentTimeMillis() - timeStartedSQuickSort) + " ms");
		
	}

	/**
	 * Generates random numbers from 1 - 100,000
	 * 
	 * @return the integer array list.
	 */
	public static int[] generateRandomNumbers() {
		int[] numbers = new int[RANGE];
		for (int i = 0; i < numbers.length; i++) {
			Random rand = new Random();
			numbers[i] = rand.nextInt(RANGE) + 1;
		}
		return numbers;
	}

	/**
	 * Selection Sort algorithm The selection sort algorithm sorts an array by
	 * repeatedly finding the minimum element (considering ascending order) from
	 * unsorted part and putting it at the beginning.
	 * 
	 * @param arr
	 *            - the int array to be sorted
	 */
	public static void selectionSort(int arr[]) {
		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n - 1; i++) {
			// Find the minimum element in unsorted array
			int minimumIndex = i;
			for (int j = i + 1; j < n; j++)
				if (arr[j] < arr[minimumIndex])
					minimumIndex = j;

			// Swap the found minimum element with the first
			// element
			int temp = arr[minimumIndex];
			arr[minimumIndex] = arr[i];
			arr[i] = temp;
		}
	}

	/**
	 * This is an in-place comparison-based sorting algorithm. Here, a sub-list
	 * is maintained which is always sorted. For example, the lower part of an
	 * array is maintained to be sorted. An element which is to be 'insert'ed in
	 * this sorted sub-list, has to find its appropriate place and then it has
	 * to be inserted there. Hence the name, insertion sort.
	 * 
	 * @param arr
	 *            - the array of integers to be sorted.
	 */
	public static void insertionSort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			/*
			 * Move elements of arr[0..i-1], that are greater than key, to one
			 * position ahead of their current position
			 */
			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	/**
	 * ShellSort is mainly a variation of Insertion Sort. In insertion sort, we
	 * move elements only one position ahead. When an element has to be moved
	 * far ahead, many movements are involved. The idea of shellSort is to allow
	 * exchange of far items. In shellSort, we make the array h-sorted for a
	 * large value of h. We keep reducing the value of h until it becomes 1. An
	 * array is said to be h-sorted if all sublists of every h’th element is
	 * sorted.
	 * 
	 * @param arr
	 *            - the array of integers to be sorted.
	 */
	public static void shellSort(int[] arr) {
		int n = arr.length;

		// Start with a big gap, then reduce the gap
		for (int gap = n / 2; gap > 0; gap /= 2) {
			// Do a gapped insertion sort for this gap size.
			// The first gap elements a[0..gap-1] are already
			// in gapped order keep adding one more element
			// until the entire array is gap sorted
			for (int i = gap; i < n; i += 1) {
				// add a[i] to the elements that have been gap
				// sorted save a[i] in temp and make a hole at
				// position i
				int temp = arr[i];

				// shift earlier gap-sorted elements up until
				// the correct location for a[i] is found
				int j;
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
					arr[j] = arr[j - gap];

				// put temp (the original a[i]) in its correct
				// location
				arr[j] = temp;
			}
		}
	}

	/**
	 * Merge Sort is a Divide and Conquer algorithm. It divides input array in
	 * two halves, calls itself for the two halves and then merges the two
	 * sorted halves.
	 * 
	 * @param arr
	 *            - the input array to be sorted.
	 */
	public static void mergeSort(int arr[]) {
		int arraySize = arr.length;

		// Base case
		if (arraySize < 2)
			return; // the array is sorted

		int mid = arraySize / 2;

		int[] left = new int[mid];
		int[] right = new int[arraySize - mid];

		// copy the first half into left sub array
		for (int i = 0; i < mid; i++) {
			left[i] = arr[i];
		}

		// copy the second half into right sub array
		for (int i = mid; i < arraySize; i++) {
			right[i - mid] = arr[i];
		}

		// recursive calls
		mergeSort(left);
		mergeSort(right);

		// Merge the two sorted subArrays
		merge(arr, left, right);
	}

	/**
	 * Helper method to merge the sorted halves
	 * 
	 * @param arr
	 * @param left
	 * @param right
	 */
	public static void merge(int[] arr, int[] left, int[] right) {
		int sizeLeft = left.length;
		int sizeRight = right.length;
		int leftIndex = 0, rightIndex = 0, arrayIndex = 0;

		while (leftIndex < sizeLeft && rightIndex < sizeRight) {
			if (left[leftIndex] <= right[rightIndex]) {
				arr[arrayIndex] = left[leftIndex];
				leftIndex++;
			} else {
				arr[arrayIndex] = right[rightIndex];
				rightIndex++;
			}
			arrayIndex++;
		}

		/* Copy remaining elements of leftSubArray[] if any */
		while (leftIndex < sizeLeft) {
			arr[arrayIndex] = left[leftIndex];
			leftIndex++;
			arrayIndex++;
		}

		/* Copy remaining elements of rightSubArray[] if any */
		while (rightIndex < sizeRight) {
			arr[arrayIndex] = right[rightIndex];
			rightIndex++;
			rightIndex++;
		}

	}

	/**
	 * QuickSort is a Divide and Conquer algorithm. 
	 * It picks an element as pivot and partitions the given array around the picked pivot. 
	 * @param arr
	 * @param low
	 * @param high
	 */
	public static void quickSort(int[] arr, int low, int high) {
		if (low < high) {
			int partitionIndex = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			quickSort(arr, low, partitionIndex - 1);
			quickSort(arr, partitionIndex + 1, high);
		}
	}

	/**
	 * This function takes last element as pivot, places the pivot element at
	 * its correct position in sorted array, and places all smaller (smaller
	 * than pivot) to left of pivot and all greater elements to right of pivot
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}
}
