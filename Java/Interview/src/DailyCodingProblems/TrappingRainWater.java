package DailyCodingProblems;

public class TrappingRainWater {
	private static int calcCapacity(int[] arr) {
		int total = 0;
		int left = 0;
		int right = arr.length-1;
		int left_max = arr[left];
		int right_max = arr[right];
		while (left < right) {
			if (arr[left] < arr[right]) {
				left_max = Math.max(left_max, arr[left]);
				total += Math.min(left_max, arr[right]) - arr[left];
				left++;
			}
			else {
				right_max = Math.max(right_max, arr[right]);
				total += Math.min(right_max, arr[left]) - arr[right];
				right--;
			}
		}
		return total;
	}
	public static void main(String[] args) {
		int[] arr2 = {0,1,0,2,1,0,1,3,2,1,2,1};//6
		System.out.println(calcCapacity(arr2));
	}
}
