package ArraysAndStrings;

public class FirstMissingInteger {
	
	/*
	 * Solution 1 - Segregate the array by putting the non-positive values to the left and positive values to the right
	 * Find the first missing positive number from the segregated array number by keeping track of the index
	 */
		
	public static void printArray(int[] arr) {
		for (int i : arr)
			System.out.print(i+" ");
		System.out.println();
	}
	public static int Segregate(int[] arr) {
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= 0) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
		}
		return j;
	}
	
	public static int FindFirstPositive(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				int x = arr[i];
				if (x <= arr.length)
					arr[x-1] = -1; //Mark the index to be visited ,i.e. -1
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0)
				return i+1;
		}
		return arr.length+1;
	}
	
	public static int FindMissingPositive(int[] arr) {
		int shift_index = Segregate(arr);
		int[] positive_arr = new int[arr.length-shift_index];
		int j = 0;
		//Copy the positive part of the array to the new array and find the first missing positive integer
		for (int i = shift_index; i < arr.length; i++)
			positive_arr[j++] = arr[i];
		return FindFirstPositive(positive_arr);
	}
	
	/*
	 * Solution 2 - Create a new array with the size of the max element in the original array and with 
	 * all elements initialized to 0. Then mark the indices of positive elements as 1.
	 * Return the index of the first 0 element in the array
	 */
	
	public static int FindMissingPositive2(int[] arr) {
		if (arr.length < 1) {
			return 1;
		}
		int max = Integer.MIN_VALUE;
		for (int i : arr) {
			if (i > max)
				max = i;
		}
		if (max < 1)
			return 1;
		int[] positive_array = new int[max];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > 0) {
				if (positive_array[arr[i]-1] != 1)
					positive_array[arr[i]-1] = 1; //Mark the index as visited ,i.e. 1
			}
		}
		for (int i = 0; i < positive_array.length; i++)
			if (arr[i] == 0)
				return i+1;
		return positive_array.length+1;
	}

	public static void main(String[] args) {
		int[] arr = {0,10,2,-10,-20};
		System.out.println(FindMissingPositive(arr));
		System.out.println(FindMissingPositive2(arr));
		//printArray(arr);
	}
}
