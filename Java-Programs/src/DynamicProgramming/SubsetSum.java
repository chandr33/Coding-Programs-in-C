package DynamicProgramming;
import java.util.*;
public class SubsetSum {
	
	static boolean isSubset(int arr[], int total) {
		int index = arr.length - 1;
		int currentTotal = 0;
		HashMap<String,Boolean> table = new HashMap<>();
		boolean subset = isSubset_util(arr, index, currentTotal, total, table);
		return subset;
	}
	
	static boolean isSubset_util(int arr[], int index, int currentTotal, int total, HashMap<String,Boolean> table) {
		String key = Integer.toString(index) + ":" + Integer.toString(currentTotal);
		
		if (table.containsKey(key))
			return table.get(key);
		
		if (currentTotal == total)
			return true;
		
		if (index < 0)
			return false;
		
		table.put(key, isSubset_util(arr, index - 1, currentTotal + arr[index], total, table) | isSubset_util(arr, index - 1, currentTotal, total, table));
		
		return table.get(key);
	}
	
	static boolean isSubset_table(int arr[], int total) {
		boolean table[][] = new boolean[arr.length + 1][total+1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (j == 0)
					table[i][j] = true;
				else if (i == 0)
					table[i][j] = false;
				else if(j < arr[i - 1])
					table[i][j] = table[i-1][j];//Is there a subset that can form the sum j without using the current element from the array
				else {
					table[i][j] = table[i-1][j] | table[i-1][j-arr[i-1]];//Is there a subset that form the sum j with or without using the current element from the array
				}
			}
		}
		return table[arr.length][total];
	}
	public static void main(String[] args) {
		int set[] = {3,34,4,12,6,1};
		int total = 18;
		System.out.println(isSubset_table(set, total));
	}
}
