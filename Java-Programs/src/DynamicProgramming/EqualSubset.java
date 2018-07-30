package DynamicProgramming;
import java.util.*;
public class EqualSubset {
	static boolean EqualSubset_table(int arr[]) {
		int sum = 0;
		for (int i : arr)
			sum += i;
		if (sum % 2 != 0)
			return false;
		boolean table[][] = new boolean[arr.length+1][sum+1];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (j == 0)
					table[i][j] = true;
				else if (i == 0)
					table[i][j] = false;
				else if (arr[i-1] > j) {
					table[i][j] = table[i-1][j];
				}
				else {
					table[i][j] = table[i-1][j] | table[i-1][j-arr[i-1]];
				}
			}
		}
		return table[arr.length-1][(sum/2)];
	}
	
	static boolean EqualSubset_Recursive(int arr[]) {
		int sum = 0;
		for (int i : arr)
			sum += i;
		int index = arr.length-1;
		int currentTotal = 0;
		HashMap<String,Boolean> table = new HashMap<String,Boolean>();
		return EqualSubset_util(arr, index, currentTotal, sum, table);
	}
	
	static boolean EqualSubset_util(int arr[], int index, int currentTotal, int total, HashMap<String,Boolean> table) {
		String key = Integer.toString(index) + ":" + Integer.toString(currentTotal);
		
		if (table.containsKey(key))
			return table.get(key);
		
		if (index < 0)
			return false;
		
		if ((currentTotal - (total - currentTotal)) == 0)
			return true;
			
		table.put(key,  EqualSubset_util(arr, index-1, currentTotal, total, table) | EqualSubset_util(arr, index-1, currentTotal + arr[index], total, table));
		
		return table.get(key);
	}

	public static void main(String[] args) {
		int arr[] = {1,5,3,9};
		System.out.println(EqualSubset_table(arr));
		System.out.println(EqualSubset_Recursive(arr));
	}
}
