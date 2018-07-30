package DynamicProgramming;

import java.util.HashMap;

public class CoinChange {
	static int getNum(int arr[], int total) {
		int index = arr.length - 1;
		HashMap<String,Integer> dict = new HashMap<>();
		int count_sets = 0;
		count_sets = getNum_util(arr, index, total, dict);
		return count_sets;
	}
	/*Counts the number of sets whose members(repetition is allowed) add up to the given total
	 * Uses a HashMap to store the key (CurrentIndex + CurrentTotal) and value (Count of Sets) pairs
	 */
	static int getNum_util(int arr[], int index, int total, HashMap<String,Integer> dict) {
		String key = Integer.toString(index) + ":"  + Integer.toString(total);
		if (dict.containsKey(key))
			return dict.get(key);
		if (total == 0)
			return 1;
		else if (total < 0)
			return 0;
		else if (index < 0)
			return 0;
		dict.put(key, (getNum_util(arr, index - 1, total, dict) + getNum_util(arr, index, total - arr[index], dict)));
		return dict.get(key);
	}
	//Bottom Up Approach
	static int getNumTable (int arr[], int total) {
		int table[][] = new int[arr.length][total+1];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (j == 0)
					table[i][j] = 1;
				else {
					int count1 = (j - arr[i] < 0) ? 0 : table[i][j - arr[i]];
					int count2 = (i - 1 < 0) ? 0 : table[i - 1][j];
					table[i][j] = count1 + count2;
				}
			}
		}
		return table[arr.length - 1][total];
	}
	
	public static void main(String[] args) {
		int[] arr = new int[]{2,5,3,6};
		System.out.println(getNumTable(arr,10));
	}
}
