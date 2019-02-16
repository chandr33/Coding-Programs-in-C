package SalesForce;

public class HouseRobber {
	static int max_robbery(int arr[]) {
		int dp[][] = new int[arr.length][2];
		
		for (int i = 0; i < dp.length; i++) {
			if (i == 0) {
				dp[i][0] = arr[i];
				dp[i][1] = 0;
			}
			else {
				if (i != dp.length-1) {
					dp[i][0] = dp[i-1][1] + arr[i];
				}
				else {
					if (dp.length % 2 == 0) {
						dp[i][0] = dp[i-1][1] + arr[i];
					}
					else {
						dp[i][0] = dp[i-1][1] + arr[i] - arr[0];
					}
				}
				dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
			}
		}
		//printTable(dp);
		return Math.max(dp[arr.length-1][0], dp[arr.length-1][1]);
	}
	static void printTable(int dp[][]) {
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		int[] money = {1,1,1,2,4,5,5,5,6};
		System.out.println(max_robbery(money));
	}
}
