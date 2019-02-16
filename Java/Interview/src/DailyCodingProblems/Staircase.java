package DailyCodingProblems;

import java.util.Arrays;

public class Staircase {
	public static int count_ways(int n, int[] arr, int[] memo) {
		int total = 0;
		if (n <= 1)
			return total = 1;
		if (memo[n] != -1)
			return memo[n];
		for (int i = 0; i < arr.length; i++) {
			if (n-arr[i] >= 0) {
				total += count_ways(n-arr[i], arr, memo);
			}
		}
		memo[n] = total;
		return memo[n];
	}

	public static void main(String[] args) {
		int num_stairs = 6;
		int[] arr = {1,2};
		int[] memo = new int[num_stairs+1];
		Arrays.fill(memo, -1);
		System.out.println(count_ways(num_stairs, arr, memo));
	}
}
