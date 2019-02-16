package DailyCodingProblems;

import java.util.Arrays;

public class DecodingMessage {
	static int numWays(char[] array, int n, int[] memo) {
		if (n == 0) 
			return 1;
		int index = array.length-n;
		if (array[index] == '0')
			return 0;
		if (memo[n] != -1)
			return memo[n];
		int result = numWays(array, n-1, memo);
		if (n >= 2 && (Integer.parseInt(""+array[index]+array[index+1]) <= 26)) {
			result += numWays(array, n-2, memo);
		}
		memo[n] = result;
		return result;
	}
	public static void main(String[] args) {
		String message = "111111";
		char[] array = message.toCharArray();
		int[] memo = new int[message.length()+1]; // in case message was empty
		Arrays.fill(memo, -1);
		System.out.println(numWays(array,message.length(),memo));
	}
}
