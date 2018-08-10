package ArraysAndStrings;

public class StockProfit {
	static void maxProfit(int arr[]) {
		int i = 0;
		int maxProfit = 0;
		int firstIndex = 0;
		while (i < arr.length-1) {
			if (arr[i] < arr[i+1]) {
				maxProfit += arr[i+1] - arr[i];
			}
			else {
				if (maxProfit > 0)
					System.out.println("Buy on day " + firstIndex + " Sell on day " + i + " With Profit " + maxProfit);
				maxProfit = 0;
				firstIndex = i+1;
			}
			i++;
		}
		if (maxProfit > 0)
			System.out.println("Buy on day " + firstIndex + " Sell on day " + i + " With Profit " + maxProfit);
	}
	public static void main(String[] args) {
		int arr[] = {1,1,1,22,20,32};
		int arr2[] = {100, 180, 260, 310, 40, 535, 695};
		maxProfit(arr);
	}
}
