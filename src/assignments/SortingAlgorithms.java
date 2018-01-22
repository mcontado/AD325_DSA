package assignments;

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
		// Selection Sort
		int[] selectionSortNumbers = generateRandomNumbers();
		long timeStartedSelectionSort = System.currentTimeMillis();
		selectionSort(selectionSortNumbers);
		System.out.println("Selection Sort last for: " + (System.currentTimeMillis() - timeStartedSelectionSort) + " ms"); 

		// Insertion Sort
		int[] insertionSortNumbers = generateRandomNumbers();
		long timeStartedInsertionSort = System.currentTimeMillis();
		insertionSort(insertionSortNumbers);
		System.out.println("Insert Sort last for: " + (System.currentTimeMillis() - timeStartedInsertionSort) + " ms"); 


		// Shell Sort
		int[] shellSortNumbers = generateRandomNumbers();
		long timeStartedShellSort = System.currentTimeMillis();
		shellSort(shellSortNumbers);
		System.out.println("Shell Sort last for: " + (System.currentTimeMillis() - timeStartedShellSort) + " ms"); 
		
		// Merge Sort
		int[] mergeSortNumbers = generateRandomNumbers();
		long timeStartedMergeSort = System.currentTimeMillis();
		mergeSort(mergeSortNumbers, 0, mergeSortNumbers.length-1);
		System.out.println("Merge Sort last for: " + (System.currentTimeMillis() - timeStartedMergeSort) + " ms");

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
	 * @param arr - the int array to be sorted
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
	 * ShellSort is mainly a variation of Insertion Sort. 
	 * In insertion sort, we move elements only one position ahead. 
	 * When an element has to be moved far ahead, many movements are involved. 
	 * The idea of shellSort is to allow exchange of far items. 
	 * In shellSort, we make the array h-sorted for a large value of h. 
	 * We keep reducing the value of h until it becomes 1. 
	 * An array is said to be h-sorted if all sublists of every h’th element is sorted.
	 * @param arr - the array of integers to be sorted.
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
	 * Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, 
	 * calls itself for the two halves and then merges the two sorted halves. 
	 * The merge() function is used for merging two halves. 
	 * The merge(arr, l, m, r) is key process that assumes that arr[l..m] and arr[m+1..r] 
	 * are sorted and merges the two sorted sub-arrays into one.
	 * @param arr
	 * @param left
	 * @param mid
	 * @param right
	 */
	// Merges two subarrays of arr[].
    // First subarray is arr[left..mid]
    // Second subarray is arr[m+1..right]
    public static void merge(int arr[], int left, int mid, int right)
    {
        // Find sizes of two subarrays to be merged
        int sizeLeft = mid - left + 1;
        int sizeRight = right - mid;
 
        /* Create temp arrays */
        int leftSubArray[] = new int [sizeLeft];
        int rightSubArray[] = new int [sizeRight];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<sizeLeft; ++i)
            leftSubArray[i] = arr[left + i];
        for (int j=0; j<sizeRight; ++j)
            rightSubArray[j] = arr[mid + 1 + j];
 
 
        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = left;
        while (i < sizeLeft && j < sizeRight)
        {
            if (leftSubArray[i] <= rightSubArray[j])
            {
                arr[k] = leftSubArray[i];
                i++;
            }
            else
            {
                arr[k] = rightSubArray[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of leftSubArray[] if any */
        while (i < sizeLeft)
        {
            arr[k] = leftSubArray[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of rightSubArray[] if any */
        while (j < sizeRight)
        {
            arr[k] = rightSubArray[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[left..right] using
    // merge()
    public static void mergeSort(int arr[], int left, int right)
    {
        if (left < right)
        {
            // Find the middle point
            int mid = (left+right)/2;
 
            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr , mid+1, right);
 
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }
}
