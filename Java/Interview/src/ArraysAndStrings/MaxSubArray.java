package ArraysAndStrings;

public class MaxSubArray {
	static int sum(int arr[]) {
		int maxSoFar = 0, max = 0;
		for (int i = 0; i < arr.length; i++) {
			maxSoFar = Math.max(arr[i], maxSoFar+arr[i]);
			max = Math.max(max,maxSoFar);
		}
		return max;
	}
	public static void main(String[] args) {
		int arr[] = {-1,2,100,-1,5};
		System.out.println(sum(arr));
	}

}
