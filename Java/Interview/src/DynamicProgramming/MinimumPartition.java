package DynamicProgramming;
import java.util.*;
public class MinimumPartition {
	public static int minPartition(int arr[]) {
		int total = 0;
		int visitedSetTotal = 0;
		int index = arr.length - 1;
		for (int i = 0; i < arr.length; i++)
			total += arr[i];
		HashMap<String,Integer> table = new HashMap<>();
		int difference = minPartition_util(arr, total, visitedSetTotal, index, table);
		return difference;
	}
	
	public static int minPartition_util(int arr[], int total, int visitedSetTotal, int index, HashMap<String, Integer> table) {
		String key = Integer.toString(visitedSetTotal) + ":" + Integer.toString(index);
		
		if (table.containsKey(key))
			return table.get(key);
		
		if (index < 0)
			return Math.abs((total - visitedSetTotal) - visitedSetTotal);	
		
		table.put(key, Math.min(minPartition_util(arr, total, visitedSetTotal + arr[index], index - 1, table), 
				minPartition_util(arr, total, visitedSetTotal, index - 1, table)));
		return table.get(key);
	}
	
	public static int minDifference(int arr[]) {
		int n = arr.length;
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += arr[i];
		boolean sumPossible[][] = new boolean[n+1][sum+1];
		for (int i = 0; i < sumPossible.length; i++) {
			for (int j = 0; j < sumPossible[i].length; j++) {
				if (j == 0)
					sumPossible[i][j] = true;//Its possible to get a sum of 0 with using arr[i] (By excluding arr[i])
				else if (i == 0)
					sumPossible[i][j] = false;//Its impossible to get sum j without using any element from array
				else if (arr[i - 1] > j)
					sumPossible[i][j] = sumPossible[i - 1][j];//Is it possible to get sum j without using arr[i] because arr[i] > j
				else 
					sumPossible[i][j] = sumPossible[i - 1][j] | sumPossible[i - 1][j - arr[i - 1]];//Is it possible to get sum j with or without including arr[i - 1]
			}
		}
		int minDifference = Integer.MAX_VALUE;
		for (int i = (sum/2); i >= 0; i--) {
			if (sumPossible[arr.length][i]) {//Check the last row (as the last row indicates that all elements are included in either set)
				minDifference = sum - (2*i);
				break;//Stop at the first index starting from sum/2 which indicates that the particular sum (i) can be formed using all the elements from the array
			}
		}
		return minDifference;
	}

	
	public static void main(String[] args) {
		int arr[] = new int[]{2,3,4353,24,4,23,433,3,3,65,46,4,6,34,65,8,67,45,25,7,345,65,25,5,745,34,56,768,6565,354,3545,566,333,53,345,65,6767,33,2,1,1,1,1,1,1,1};
		System.out.println(minPartition(arr));
		//System.out.println(minDifference(arr));
	}
}
