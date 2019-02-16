package DailyCodingProblems;

public class MaxNonContiguousSum {
	static int maxSum(int[] arr) {
		int result = maxSum_util(arr, -2, 0, 0);
		return result;
	}
	
	static boolean isAdjacent(int i, int j) {
		if (i+1 == j || i-1 == j)
			return true;
		return false;
	}
	
	static int maxSum_util(int[] arr, int last_picked, int current_index, int max) {
		if (arr.length == 1)
			return arr[0];
		if (arr.length == 2)
			return Math.max(arr[0], arr[1]);
		if (last_picked >= arr.length || current_index >= arr.length)
			return max;
		if (isAdjacent(last_picked, current_index))
			return max = maxSum_util(arr, last_picked, current_index+1, max);
		
		return Math.max(maxSum_util(arr, current_index, current_index+1,max+arr[current_index]), maxSum_util(arr, last_picked, current_index+1, max));
	}
	
	static int efficientMaxSum(int[] arr) {
		int inclusive = arr[0];
		int exclusive = 0;
		for (int i = 1; i < arr.length; i++) {
			int temp = inclusive;
			inclusive = Math.max(exclusive+arr[i], inclusive);
			exclusive = temp;
		}
		return inclusive;
	}
	public static void main(String[] args) {
		int[] array = {-100,-1,-99};
		System.out.println(maxSum(array));
		System.out.println(efficientMaxSum(array));
	}

}
