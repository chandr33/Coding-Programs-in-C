package DynamicProgramming;

public class SherlockAndCost {
	static int calc(int[] arr) {
		int dp[][] = new int[2][2];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				dp[i][j] = 0;
			}
		}
		for (int i = 1; i < arr.length; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + Math.abs(arr[i - 1] - 1));
			dp[i][1] = Math.max(dp[i - 1][0] + Math. abs(arr[i] - 1), dp[i - 1][1]);
		}
		printTable(dp);
		return Math.max(dp[arr.length - 1][0], dp[arr.length - 1][1]);
	}
	
	static void printTable(int arr[][]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int arr[] = {1,2};
		System.out.println(calc(arr));
	}
}
